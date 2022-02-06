package com.timife.teesast.auth.presentation

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timife.teesast.R
import com.timife.teesast.databinding.FragmentSignupBinding
import com.timife.teesast.main.presentation.MainActivity
import com.timife.teesast.utils.Messages
import com.timife.teesast.utils.Result
import com.timife.teesast.utils.enable
import com.timife.teesast.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sign

@AndroidEntryPoint
class SignupFragment: Fragment() {
    private lateinit var signupBinding : FragmentSignupBinding

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupBinding =FragmentSignupBinding.inflate(inflater)
        signupBinding.signupButton.enable(false)
        return signupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = signupBinding.email.text.toString().trim()
        val name = signupBinding.name.text.toString().trim()
        val password = signupBinding.password.text.toString().trim()
        val location = signupBinding.location.text.toString().trim()
        val phoneNumber = signupBinding.mobile.text.toString().trim()
        if(email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && location.isNotEmpty() && phoneNumber.isNotEmpty()){
            signupBinding.signupButton.enable(true)
        }

        signupBinding.signupButtonText.setOnClickListener {
            validateAndCreate(
                email, name, password, location,phoneNumber
            )
        }
        observeViewModels()

        signupBinding.backToLogin.setOnClickListener {
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
        }
    }

    private fun observeViewModels() {
        with(authViewModel){
            auth.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            }

            isLoading.observe(viewLifecycleOwner){state ->
                when(state){
                    true -> {
                        signupBinding.apply {
                            progressSignUp.visibility = View.VISIBLE
                        }
                    }
                    false -> {
                        signupBinding.apply {
                            progressSignUp.visibility = View.GONE
                        }
                    }
                }
            }

            dataFetchState.observe(viewLifecycleOwner){state ->
                when(state){
                    true -> {
                        requireActivity().startNewActivity(MainActivity::class.java)
                    }
                    false -> {

                    }
                }
            }
        }
    }


    private fun validateAndCreate(
        email: String,
        name: String,
        password: String,
        location: String,
        phone:String
    ){
        if (name.isEmpty()) {
            signupBinding.name.error = Messages.BLANK_FIELDS_IN_FORM
            signupBinding.name.requestFocus()
        } else if (email.isEmpty()) {
            signupBinding.email.error = Messages.BLANK_FIELDS_IN_FORM
            signupBinding.email.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupBinding.email.error = "Please provide valid email!"
            signupBinding.email.requestFocus()
        } else if (password.isEmpty()) {
            signupBinding.password.error = Messages.BLANK_FIELDS_IN_FORM
            signupBinding.password.requestFocus()
        } else if (password.length < 6) {
            signupBinding.password.error = Messages.SHORT_PASSWORD
            signupBinding.password.requestFocus()
        }else if(location.isEmpty()){
            signupBinding.location.error = Messages.BLANK_FIELDS_IN_FORM
        } else if (phone.isEmpty()) {
            signupBinding.mobile.error = Messages.BLANK_FIELDS_IN_FORM
        } else {
            signUp(email,name,password,location,phone)
        }
    }

    private fun signUp(email: String, name: String, password: String, location: String, phone:String) {
        authViewModel.registerUser()
    }

}