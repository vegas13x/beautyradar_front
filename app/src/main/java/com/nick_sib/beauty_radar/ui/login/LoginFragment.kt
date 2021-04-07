package com.nick_sib.beauty_radar.ui.login


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.LogingFragmentBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.register.CheckInFragment
import com.nick_sib.beauty_radar.ui.utils.EMAIL_AND_PASSWORD_SUCCESS_GO_TO_LOGOUT
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alex Volkov(Volkos)
 *
 * Фрагмент авторизации пользователя
 * На данном фрагменте пользователь либо входит в раннее созданую уч.запись или регистрирует новую
 */
class LoginFragment : Fragment(R.layout.loging_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModel()
    private var binding: LogingFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LogingFragmentBinding.bind(view)
        viewModel.subscribeLiveData(viewLifecycleOwner)
            .observe(viewLifecycleOwner, { renderData(it) })

        binding?.loginFragmentBtnRegister?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_activity_container, CheckInFragment.newInstance())
                ?.addToBackStack("CheckIn")?.commit()
        }
        binding?.loginFragmentBtnLogin?.setOnClickListener {
            viewModel.login(
                binding?.loginFragmentTietLogin?.text.toString(),
                binding?.loginFragmentTietPassword?.text.toString()
            )
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    EMAIL_AND_PASSWORD_SUCCESS_GO_TO_LOGOUT -> {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(
                                R.id.main_activity_container,
                                LogoutFragment.newInstance()
                            )
                            ?.addToBackStack("Logout")?.commit()
                    }
                }
            }
            is AppState.Loading -> {
                when (appState.progress) {
                    1 -> {
                    }
                    2 -> {
                    }
                }
            }
            is AppState.Error -> Toast.makeText(
                activity,
                appState.error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}