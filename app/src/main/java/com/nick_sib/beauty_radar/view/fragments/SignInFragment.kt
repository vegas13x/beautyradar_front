package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentSignInBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.view_model.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var job: String? = null
    private val signInViewModel: SignInViewModel by viewModel()
    private lateinit var binding: FragmentSignInBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        signInViewModel.subscribe().observe(viewLifecycleOwner) {}

        checkUser()
    }

    private fun btnInit() {
        binding.fragmentSusBtnNext.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToProfileInfoEditFragment())
        }
    }

    private fun checkUser() {
        binding.fragmentSusBtnMasterFull.setOnClickListener {
            it.isSelected = true
            binding.fragmentSusBtnClientFull.isSelected = false
            job = "master"
            btnInit()
        }

        binding.fragmentSusBtnClientFull.setOnClickListener {
            it.isSelected = true
            binding.fragmentSusBtnMasterFull.isSelected = false
            job = "client"
            btnInit()
        }
    }

}