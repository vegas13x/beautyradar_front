package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

    private val uid: String?
        get() = SingletonUID.getUID()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)
        binding?.viewModel = viewModel

        start()

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding?.run {
            fragmentSignResendSmsTextview.setOnClickListener {
                activity?.run {
                    this@EnterCodeFragment.viewModel.resendSMS(this)
                    start()
                }
            }

        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
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

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                val checkUid: String = uid ?: let { toast("Ошибка null");"" }
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                when (appState.data) {
                    is UserMaster -> {
                        viewModel.checkUserInDB(appState.data.uid)
                    }
                    is Boolean -> {
                        viewModel.getUserByUID(checkUid)
                    }
                    is UserDTO -> {
                        viewModel.setImgInSingleton(appState.data.img)
                        FirebaseMessaging.getInstance().deleteToken()
                        viewModel.updateUserByUserResponse(appState.data)
                        findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToMasterClientFragment())
                    }
                }
            }
            is AppState.Loading -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = false
            }
            is AppState.Error -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                viewModel.codeError()
                when (appState.error) {
                    else -> toast(appState.error.message ?: "")
                }
            }
            else -> {}
        }
    }

    private fun toast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}