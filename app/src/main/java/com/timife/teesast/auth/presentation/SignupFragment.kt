package com.timife.teesast.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timife.teesast.databinding.FragmentSignupBinding

class SignupFragment: Fragment() {
    private lateinit var signupBinding : FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupBinding =FragmentSignupBinding.inflate(inflater)
        return signupBinding.root
    }
}