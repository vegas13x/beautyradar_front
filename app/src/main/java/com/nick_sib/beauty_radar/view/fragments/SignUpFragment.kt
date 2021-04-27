package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.view_model.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var uid: String

    private val viewModel: SignUpViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        uid = SingletonUID.getInstance()?.getUID().toString()

        viewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignUpFragmentSecond
                (uid,binding.nameText.text.toString(),binding.secondName.text.toString()))
        }

        binding.backArrow.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToAuthFragment())
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