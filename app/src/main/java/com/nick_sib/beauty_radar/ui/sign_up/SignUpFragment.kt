package com.nick_sib.beauty_radar.ui.sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentSignUpBinding
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.sign_up2.SignUpFragment2
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment(uid: String) : Fragment(R.layout.fragment_sign_up) {

    private var uid = uid

    companion object {
        fun newInstance(uid: String) = SignUpFragment(uid)
    }

    private val viewModel: SignUpViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBinding
    lateinit var hashMap: HashMap<String, String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.btnContinue.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_container,
                    SignUpFragment2.newInstance(
                        uid,
                        binding.nameText.text.toString(),
                        binding.secondName.text.toString()
                    )
                ).commitNow()
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