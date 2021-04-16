package com.nick_sib.beauty_radar.ui.sign_up2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpSecondBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpSecondFragment(uid: String, name: String, secondName: String) :
    Fragment(R.layout.fragment_sign_up_second) {

    private val uid = uid
    private val name = name
    private val secondName = secondName
    private val job = ""

    companion object {
        fun newInstance(uid: String, name: String, secondName: String) =
            SignUpSecondFragment(uid, name, secondName)
    }

    private val secondViewModel: SignUpSecondViewModel by viewModel()
    private lateinit var binding: FragmentSignUpSecondBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpSecondBinding.bind(view)
        secondViewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.btnContinue.setOnClickListener {
            secondViewModel.createNewUser(uid, name, secondName, job)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_container,
                    LogoutFragment.newInstance()
                )
                .addToBackStack("Logout").commit()
        }
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> -> {

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