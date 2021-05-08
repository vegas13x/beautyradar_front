package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentSignBinding
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.extension.digitToPhone
import com.nick_sib.beauty_radar.extension.phoneToDigit
import com.nick_sib.beauty_radar.view_model.SignUpViewModel
import com.nick_sib.beauty_radar.view.utils.CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alex Volkov(Volkos)
 *
 *Фрагмент регистрации через телефон
 */
class SignUpFragment : Fragment(R.layout.fragment_sign) {
    private val viewModel: SignUpViewModel by viewModel()
    private var binding: FragmentSignBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignBinding.bind(view)
        binding?.viewModel = viewModel

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding?.fragmentSignTilPhone?.addOnEditTextAttachedListener { textInput ->
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
                binding?.fragmentSignLoadingDialog?.root?.isGone = true
                val data: Int? = appState.data as? Int
                if (data == CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT) {
                    val phone = binding?.authPhone?.text.toString()
//                    findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToEnterCodeFragment(phone))
                }
                viewModel.codeDone()
            }
            is AppState.Loading -> {
                binding?.fragmentSignLoadingDialog?.root?.isGone = false
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