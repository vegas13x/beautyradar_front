package com.nick_sib.beauty_radar.ui.logout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentLogoutBinding
import com.nick_sib.beauty_radar.ui.authScreen.AuthFragment
import com.nick_sib.beauty_radar.ui.profileScreen.ProfileFragment
import com.nick_sib.beauty_radar.ui.utils.USER_SIGNOUT
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alex Volkov(Volkos)
 *
 * Фрагмент выхода из уч.записи
 */
class LogoutFragment : Fragment(R.layout.fragment_logout) {

    var uid: String = "uvx86v1jCrPaRi0P3EUvrs4JX1P2"

    companion object {
        fun newInstance() = LogoutFragment()
    }

    private val viewModel: LogoutViewModel by viewModel()
    private lateinit var binding: FragmentLogoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogoutBinding.bind(view)
        viewModel.subscribeLiveData(viewLifecycleOwner).observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding.logoutFragmentBtnLogout.setOnClickListener {
            viewModel.exitInProfile()
        }

        binding.profileFragmentBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_container,
                    ProfileFragment.newInstance(uid)
                )
                .addToBackStack("Profile").commit()
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    USER_SIGNOUT -> {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.main_activity_container, AuthFragment.newInstance())
                            .addToBackStack("USER_SIGNOUT").commit()
                    }
                }
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {

            }
            is AppState.SystemMessage -> {

            }
        }
    }

}