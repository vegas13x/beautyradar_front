package com.nick_sib.beauty_radar.ui.authScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentAuthV2Binding
import com.nick_sib.beauty_radar.extension.digitToPhone
import com.nick_sib.beauty_radar.extension.phoneToDigit
import com.nick_sib.beauty_radar.ui.enter_code.EnterCodeFragment
import com.nick_sib.beauty_radar.ui.utils.CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alex Volkov(Volkos)
 *
 *Фрагмент регистрации через телефон
 */
class AuthFragment : Fragment(R.layout.fragment_auth_v2) {

    companion object {
        fun newInstance() = AuthFragment()
    }

    private val viewModel: AuthViewModel by viewModel()
    private var binding: FragmentAuthV2Binding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthV2Binding.bind(view)
        binding?.viewModel = viewModel

        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, {
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

            }
            is AppState.Loading -> {
                when (appState.progress) {
                    CODE_RECEIVED_VISIBLE_ENTER_CODE_FRAGMENT -> {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.main_activity_container,
                                EnterCodeFragment.newInstance()
                            )
                            .addToBackStack("EnterCode").commit()
                    }
                }
            }
            is AppState.Error -> {
                when (appState.error) {

                    else -> toast(appState.error.message ?: "")
                }
            }
            is AppState.SystemMessage -> {

            }
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