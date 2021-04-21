package com.nick_sib.beauty_radar.ui.sign_up_second

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpSecondBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpSecondFragment :
    Fragment(R.layout.fragment_sign_up_second) {

    private val args: SignUpSecondFragmentArgs by navArgs()

    private var client : Boolean = false
    private var master : Boolean = false

    private val secondViewModel: SignUpSecondViewModel by viewModel()
    private lateinit var binding: FragmentSignUpSecondBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpSecondBinding.bind(view)
        secondViewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.btnClient.setOnClickListener{
            client = true
        }

        binding.btnMaster.setOnClickListener {
            master = true
        }

        binding.btnContinue.setOnClickListener {
            secondViewModel.createNewUser(args.uid, args.name, args.secondName, client, master)
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
            is AppState.Empty -> {
            }
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