package com.nick_sib.beauty_radar.ui.register

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.CheckInFragmentBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.utils.AUTH_SECCES_OPEN_NEXT_SCREEN
import com.nick_sib.beauty_radar.ui.utils.CODE_ERROR_GONE_CODE_LAYOUT
import com.nick_sib.beauty_radar.ui.utils.CODE_RECEIVED_VISIBLE_CODE_LAYOUT
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInFragment : Fragment(R.layout.check_in_fragment) {

    companion object {
        fun newInstance() = CheckInFragment()
    }

    private val viewModel: CheckInViewModel by viewModel()
    private var binding: CheckInFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CheckInFragmentBinding.bind(view)
        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding?.checkInFragmentBtnGetCode?.setOnClickListener {
            activity?.let { it1 ->
                viewModel.startPhoneNumberVerification(
                    it1,
                    binding?.checkInFragmentTietPhone?.text.toString()
                )
            }
        }
        binding?.checkInFragmentBtnSendCode?.setOnClickListener {
            viewModel.codeEntered(binding?.checkInFragmentTietCode?.text.toString())
        }

    }

    private fun visibleCodeContainer() {
        binding?.checkInFragmentLlContainerCode?.visibility = ViewGroup.VISIBLE
        binding?.checkInFragmentLlContainerPhone?.visibility = ViewGroup.GONE
    }

    private fun visiblePhoneContainer() {
        binding?.checkInFragmentLlContainerPhone?.visibility = ViewGroup.VISIBLE
        binding?.checkInFragmentLlContainerCode?.visibility = ViewGroup.GONE
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {

            }
            is AppState.Loading -> {
                when (appState.progress) {
                    AUTH_SECCES_OPEN_NEXT_SCREEN -> {
                        Toast.makeText(
                            activity,
                            "ОТКРЫВАЕТСЯ ЭКРАН ЗАПОЛНЕНИЯ ПРОФИЛЯ",
                            Toast.LENGTH_LONG
                        ).show()

                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.main_activity_container, LogoutFragment.newInstance())
                            ?.commitNow()
                    }
                    CODE_RECEIVED_VISIBLE_CODE_LAYOUT -> visibleCodeContainer()
                    CODE_ERROR_GONE_CODE_LAYOUT -> visiblePhoneContainer()
                }
            }
            is AppState.Error -> {

            }
            is AppState.SystemMessage -> {

            }
        }
    }

}