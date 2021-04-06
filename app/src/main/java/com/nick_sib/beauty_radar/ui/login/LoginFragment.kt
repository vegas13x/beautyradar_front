package com.nick_sib.beauty_radar.ui.login


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.LogingFragmentBinding
import com.nick_sib.beauty_radar.ui.register.CheckInFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.loging_fragment) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModel()
    private var binding: LogingFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LogingFragmentBinding.bind(view)
        viewModel.subscribeLiveData().observe(viewLifecycleOwner, { renderData(it) })

        binding?.loginFragmentBtnRegister?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_activity_container, CheckInFragment.newInstance())
                ?.addToBackStack("CheckIn")?.commit()
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
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