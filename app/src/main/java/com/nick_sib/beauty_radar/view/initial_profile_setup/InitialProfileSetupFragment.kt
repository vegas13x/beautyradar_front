package com.nick_sib.beauty_radar.view.initial_profile_setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentInitalProfileSetupBinding
import com.nick_sib.beauty_radar.view.utils.EMAIL_ENTRY_OPEN_LOGOUT
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * @author Alex Volkov(Volkos)
 *
 * Фрагмент добавления почты и пароля
 */
class InitialProfileSetupFragment : Fragment(R.layout.fragment_inital_profile_setup) {

    private lateinit var binding: FragmentInitalProfileSetupBinding
    private val viewModel: InitialProfileSetupViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInitalProfileSetupBinding.bind(view)

        viewModel.sucscribeLiveData(viewLifecycleOwner).observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding.initialProfileSetupBtnGoTo.setOnClickListener {
            viewModel.addEmailAndPasswordInProfile(
                binding.initialProfileSetupTietEmail.text.toString(),
                binding.initialProfileSetupTietPassword.text.toString()
            )
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Empty -> {}
            is AppState.Success<*> -> {
                toast(appState.data.toString())
            }
            is AppState.Loading -> {
                when (appState.progress) {
                    EMAIL_ENTRY_OPEN_LOGOUT -> {
//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_activity_container, LogoutFragment()).commit()
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
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}