package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentSignUpSecondBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.view_model.SignUpSecondViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpSecondFragment :
    Fragment(R.layout.fragment_sign_up_second) {

    private val args: SignUpSecondFragmentArgs by navArgs()

    private var job: String? = null

    private val secondViewModel: SignUpSecondViewModel by viewModel()
    private lateinit var binding: FragmentSignUpSecondBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpSecondBinding.bind(view)
        secondViewModel.subscribe().observe(viewLifecycleOwner) {}

        checkUser()

        binding.btnContinue.setOnClickListener {
            secondViewModel.createNewUser(args.uid, args.name, job)
            findNavController().navigate(
                SignUpSecondFragmentDirections.actionSignUpFragmentSecondToMasterClientsFragment()
            )
        }

        binding.backArrow.setOnClickListener {
            findNavController().navigate(
                SignUpSecondFragmentDirections.actionSignUpFragmentSecondToSignUpFragment()
            )
        }
    }

    private fun checkUser() {
        binding.btnMasterFull.setOnClickListener {
            it.isSelected
            binding.btnClientFull.isSelected = false
            job = "master"

        }

        binding.btnClientFull.setOnClickListener {
            it.isSelected
            binding.btnMasterFull.isSelected = false
            job = "client"
        }
    }



}