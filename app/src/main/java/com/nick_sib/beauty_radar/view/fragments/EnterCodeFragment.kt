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
import com.nick_sib.beauty_radar.extension.requestFocus
import com.nick_sib.beauty_radar.extension.showKeyboard
import com.nick_sib.beauty_radar.model.data.entites.UserMaster
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.EnterCodeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    private val viewModel: EnterCodeViewModel by viewModel()
    private var binding: FragmentEnterCodeBinding? = null

    private lateinit var uid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)

        FirebaseMessaging.getInstance().deleteToken()

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })
        binding?.viewModel = viewModel
        uid = SingletonUID.getUID().toString()

        binding?.run{
            this@EnterCodeFragment.requestFocus(fragmentEnterCodeDigitEdittext1)
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
                        viewModel.checkUserInDB(appState.data.uid)
                    }
                    is Boolean -> {
                        if (appState.data == true) {
                        findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToMasterClientFragment())}
//                        else {findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToSignUpFragment2(uid))}
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