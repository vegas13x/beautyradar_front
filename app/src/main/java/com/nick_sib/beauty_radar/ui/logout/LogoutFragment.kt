package com.nick_sib.beauty_radar.ui.logout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentLogoutBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.ui.authScreen.AuthFragment
import com.nick_sib.beauty_radar.ui.profileScreen.ProfileFragment
import com.nick_sib.beauty_radar.ui.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.ui.utils.USER_SIGNOUT
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Alex Volkov(Volkos)
 *
 * Фрагмент выхода из уч.записи
 */
class LogoutFragment : Fragment(R.layout.fragment_logout) {

    private lateinit var uid: String

    private val viewModel: LogoutViewModel by viewModel()
    private lateinit var binding: FragmentLogoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogoutBinding.bind(view)

        uid = SingletonUID.getInstance()!!.getUID().toString()

        viewModel.subscribeLiveData(viewLifecycleOwner).observe(viewLifecycleOwner, {
            Log.d(TAG_DEBAG,  "Logout onViewCreated: $it")
            renderData(it)
        })

        binding.logoutFragmentBtnLogout.setOnClickListener {
            viewModel.exitInProfile()
        }

        binding.profileFragmentBtn.setOnClickListener {
            findNavController().navigate(LogoutFragmentDirections.actionLogoutFragmentToProfileFragment(uid))
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Empty -> {}
            is AppState.Success<*> -> {
                when (appState.data) {
                    USER_SIGNOUT -> {
//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_activity_container, AuthFragment.newInstance())
//                            .addToBackStack("USER_SIGNOUT").commit()
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