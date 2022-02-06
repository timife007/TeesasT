package com.timife.teesast.auth.presentation

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.timife.teesast.databinding.FragmentLoginBinding
import com.timife.teesast.main.presentation.MainActivity
import com.timife.teesast.utils.Messages
import com.timife.teesast.utils.enable
import com.timife.teesast.utils.startNewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
private lateinit var loginBinding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.loginButton.enable(false)
        val email = loginBinding.email.text.toString().trim()
        val password = loginBinding.password.text.toString().trim()
        if(email.isNotEmpty() && password.isNotEmpty()){
            loginBinding.loginButton.enable(true)
        }

        loginBinding.loginText.setOnClickListener {
            validateAndLogin(email, password)
        }
        loginBinding.createAccount.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }
    }

    private fun validateAndLogin(
        email: String,
        password: String
    ) {
        if (email.isEmpty()) {
            loginBinding.email.error = Messages.BLANK_FIELDS_IN_FORM
            loginBinding.email.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginBinding.email.error = "Please provide valid email!"
            loginBinding.email.requestFocus()
        } else if (password.isEmpty()) {
            loginBinding.password.error = Messages.BLANK_FIELDS_IN_FORM
            loginBinding.password.requestFocus()
        } else if (password.length < 6) {
            loginBinding.password.error = Messages.SHORT_PASSWORD
            loginBinding.password.requestFocus()
        }else{
            requireActivity().startNewActivity(MainActivity::class.java)
        }
    }

}