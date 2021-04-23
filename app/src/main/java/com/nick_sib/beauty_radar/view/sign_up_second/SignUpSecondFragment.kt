package com.nick_sib.beauty_radar.view.sign_up_second

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpSecondBinding
import com.nick_sib.beauty_radar.extension.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpSecondFragment :
    Fragment(R.layout.fragment_sign_up_second) {

    private val args: SignUpSecondFragmentArgs by navArgs()

    private var client: String? = null
    private var master: String? = null

    private val secondViewModel: SignUpSecondViewModel by viewModel()
    private lateinit var binding: FragmentSignUpSecondBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpSecondBinding.bind(view)
        secondViewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.btnMasterFull.setOnClickListener {
            if (master != "true") {
                master = "true"
                it.isSelected = true
            } else {
                master = "false"
                it.isSelected = false
            }
        }

        binding.btnClientFull.setOnClickListener {
            if (client != "true") {
                client = "true"
                it.isSelected = true
            } else {
                client = "false"
                it.isSelected = false
            }
        }

        binding.btnContinue.setOnClickListener {
            secondViewModel.createNewUser(args.uid, args.name, args.secondName, client, master)
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


    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Empty -> {
            }
            is AppState.Success<*> -> {
            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
            }
            is AppState.SystemMessage -> {
            }
        }
    }
}