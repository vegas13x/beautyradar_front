package com.nick_sib.beauty_radar.ui.authScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentAuthV2Binding
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
    private lateinit var binding: FragmentAuthV2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthV2Binding.bind(view)
        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding.fragmentAuthBtnEnter.setOnClickListener {
            activity?.let { it1 ->
                viewModel.startPhoneNumberVerification(
                    it1,
                    binding.fragmentAuthEtPhoneNumber.text.toString()
                )
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
                toast(appState.error)
            }
            is AppState.SystemMessage -> {

            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}