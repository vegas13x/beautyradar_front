package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentLogoutBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.view_model.LogoutViewModel
import com.nick_sib.beauty_radar.view.utils.USER_SIGNOUT
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

        uid = SingletonUID.getInstance()?.getUID().toString()

        viewModel.subscribeLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding.logoutFragmentBtnLogout.setOnClickListener {
            viewModel.exitInProfile()
        }

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    USER_SIGNOUT -> {
                        findNavController().navigate(LogoutFragmentDirections.actionLogoutFragmentToAuthFragment())
                    }
                }
            }
            else -> {}
        }
    }

}