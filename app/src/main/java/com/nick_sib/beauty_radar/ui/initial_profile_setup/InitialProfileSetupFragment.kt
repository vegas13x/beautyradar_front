package com.nick_sib.beauty_radar.ui.initial_profile_setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.InitalProfileSetupFragmentBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.utils.EMAIL_ENTRY_OPEN_LOGOUT
import org.koin.androidx.viewmodel.ext.android.viewModel
/**
 * @author Alex Volkov(Volkos)
 *
 * Фрагмент добавления почты и пароля
 */
class InitialProfileSetupFragment : Fragment(R.layout.inital_profile_setup_fragment) {

    companion object {
        fun newInstance() = InitialProfileSetupFragment()
    }

    private lateinit var binding: InitalProfileSetupFragmentBinding
    private val viewModel: InitialProfileSetupViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = InitalProfileSetupFragmentBinding.bind(view)

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
            is AppState.Success<*> -> {
                toast(appState.data.toString())
            }
            is AppState.Loading -> {
                when (appState.progress) {
                    EMAIL_ENTRY_OPEN_LOGOUT -> {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.main_activity_container, LogoutFragment.newInstance())?.commit()
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
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}