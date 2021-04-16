package com.nick_sib.beauty_radar.ui.sign_up2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUp2Binding
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.sign_up.SignUpFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment2(uid: String, name: String, secondName: String) : Fragment(R.layout.fragment_sign_up2) {

    private val uid = uid
    private val name = name
    private val secondName = secondName

    companion object{
        fun newInstance(uid: String, name: String, secondName: String) = SignUpFragment2(uid,name,secondName)
    }

    private val viewModel: SignUpViewModel2 by viewModel()
    private lateinit var binding: FragmentSignUp2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUp2Binding.bind(view)
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