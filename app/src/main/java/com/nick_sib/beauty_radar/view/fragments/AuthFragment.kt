package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.firebase.iid.FirebaseInstanceId
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentAuthenticationBinding
import com.nick_sib.beauty_radar.extension.digitToPhone
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.extension.phoneToDigit
import com.nick_sib.beauty_radar.view_model.AuthViewModel
import com.nick_sib.beauty_radar.view.utils.CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT
import com.nick_sib.beauty_radar.view.utils.TAG_DEBAG
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alex Volkov(Volkos)
 *
 *Фрагмент регистрации через телефон
 */
class AuthFragment : Fragment(R.layout.fragment_authentication) {

    private val viewModel: AuthViewModel by viewModel()
    private var binding: FragmentAuthenticationBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthenticationBinding.bind(view)
        binding?.viewModel = viewModel

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding?.fragmentAuthTilPhone?.addOnEditTextAttachedListener { textInput ->
            textInput.editText?.doOnTextChanged { charSequence, _, _, _ ->
                val text = charSequence.toString()
                val newText = text.phoneToDigit().digitToPhone(text)
                if (text != newText) {
                    textInput.editText?.setText(newText)
                    textInput.editText?.setSelection(newText.length)
                }
            }


        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                val data: Int? = appState.data as? Int
                if (data == CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT) {
                    val phone = binding?.authPhone?.text.toString()
                    findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToEnterCodeFragment(phone))
                }
                viewModel.codeDone()
            }
            is AppState.Loading -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = false
            }
            is AppState.Error -> {
                when (appState.error) {

                    else -> toast(appState.error.message ?: "")
                }
            }
            else -> {}
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}