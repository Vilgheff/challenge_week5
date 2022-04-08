package com.thesis.android_challenge_w5.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.thesis.android_challenge_w5.R
import com.thesis.android_challenge_w5.databinding.FragmentProfileBinding
import com.thesis.android_challenge_w5.databinding.FragmentUserBinding
import com.thesis.android_challenge_w5.presentation.signin.SignInFragment
import com.thesis.android_challenge_w5.model.User
import com.thesis.android_challenge_w5.presentation.signin.SignInViewModel
import com.thesis.android_challenge_w5.presentation.user.UserFragment


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setupViewModel(inflater, container)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener{
            var controller = findNavController()
            controller.navigate(R.id.action_profileFragment_to_welcomeFragment)
        }
    }
    private fun setupViewModel(inflater: LayoutInflater,container: ViewGroup?){
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = this
        binding.profileViewModel = viewModel
        binding.apply {
            val email = UserFragment().getEmailFromBundle()
            email?.let {
                viewModel.setupUserProfile(email)
            }
        }
    }
}