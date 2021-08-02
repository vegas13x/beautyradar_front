package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.google.firebase.messaging.FirebaseMessaging
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.databinding.FragmentEnterCodeBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.extension.showKeyboard
import com.nick_sib.beauty_radar.model.data.entites.UserMaster
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.user.UserDTO
import com.nick_sib.beauty_radar.view_model.EnterCodeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    private val viewModel: EnterCodeViewModel by viewModel()
    private var binding: FragmentEnterCodeBinding? = null
    private var uid = SingletonUID.getUID().toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)
        binding?.viewModel = viewModel

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        deleteToken()
        start()
        btnResendSms()
    }

    private fun deleteToken() = FirebaseMessaging.getInstance().deleteToken()

    private fun btnResendSms() {
        binding?.run {
            fragmentSignResendSmsTextview.setOnClickListener {
                activity?.run {
                    this@EnterCodeFragment.viewModel.resendSMS(this)
                    start()
                }
            }
        }
    }

    private fun start() {
        binding?.apply {
            fragmentEnterCodeDigitEdittext1.text.clear()
            fragmentEnterCodeDigitEdittext2.text.clear()
            fragmentEnterCodeDigitEdittext3.text.clear()
            fragmentEnterCodeDigitEdittext4.text.clear()
            fragmentEnterCodeDigitEdittext5.text.clear()
            fragmentEnterCodeDigitEdittext6.text.clear()
            fragmentEnterCodeDigitEdittext1.requestFocus()
            fragmentEnterCodeDigitEdittext1.showKeyboard()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                when (appState.data) {
                    is UserMaster -> {
                        findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToSignInFragment())
                        viewModel.checkUserInDB(appState.data.uid)
                        uid = appState.data.uid!!
                    }
                    is Boolean -> {
                        if (appState.data == true) {
//                            viewModel.getUserByUID(uid)
//                        } else {
                            findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToSignInFragment())
                        }
                    }
                    is UserDTO -> {
                        viewModel.setImgInSingleton(appState.data.img)
                        deleteToken()
                        viewModel.updateUserByUserResponse(appState.data)
                        findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToSignInFragment())
                    }
                }
            }
            is AppState.Loading -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = false
            }
            is AppState.Error -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                viewModel.codeError()
            }
            else -> {
            }
        }
    }
}