package com.nick_sib.beauty_radar.ui.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment(uid: String) : Fragment() {

    private var uid = uid

    companion object{
        fun newInstance(uid: String) = SignUpFragment(uid)
    }

    private val viewModel: SignUpViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner) {
            renderData(it)
        }

    }

    private fun renderData(appState: AppState?) {
        when (appState){
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