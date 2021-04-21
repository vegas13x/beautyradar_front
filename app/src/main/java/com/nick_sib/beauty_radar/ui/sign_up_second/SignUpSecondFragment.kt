package com.nick_sib.beauty_radar.ui.sign_up_second

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.R.color.hot_pink
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpSecondBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.sign_up.SignUpFragmentDirections
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpSecondFragment :
    Fragment(R.layout.fragment_sign_up_second) {

    private val args: SignUpSecondFragmentArgs by navArgs()

    private var client: Boolean = false
    private var master: Boolean = false

    private val secondViewModel: SignUpSecondViewModel by viewModel()
    private lateinit var binding: FragmentSignUpSecondBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpSecondBinding.bind(view)
        secondViewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.btnMasterFull.setOnClickListener {
            if (!master) {
                master = true
                client = false
                it.isSelected = true
                binding.btnClientFull.isSelected = false
            } else {
                master = false
                it.isSelected = false
            }

            Log.d("TAG111", "onViewCreated: " + master.toString() + client.toString())
        }

        binding.btnClientFull.setOnClickListener {
            if (!client) {
                master = false
                client = true
                it.isSelected = true
                binding.btnMasterFull.isSelected = false

            } else {
                client = false
                it.isSelected = false
            }
            Log.d("TAG111", "onViewCreated: " + master.toString() + client.toString())
        }

        binding.btnContinue.setOnClickListener {
            secondViewModel.createNewUser(args.uid, args.name, args.secondName, client, master)
            binding.btnContinue.setOnClickListener {
                findNavController().navigate(
                    SignUpSecondFragmentDirections.actionSignUpFragmentSecondToSignUpLogoutFragment()
                )
            }

            binding.backArrow.setOnClickListener {
                findNavController().navigate(
                    SignUpSecondFragmentDirections.actionSignUpFragmentSecondToSignUpFragment()
                )
            }
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