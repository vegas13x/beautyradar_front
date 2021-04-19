package com.nick_sib.beauty_radar.ui.profileScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentProfileBinding
import com.nick_sib.beauty_radar.ui.sign_up.SignUpFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment(uid: String) : Fragment(R.layout.fragment_profile) {

    private val uid = uid

    companion object {
        fun newInstance(uid: String) = ProfileFragment(uid)
    }

    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding.profileJob.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, SignUpFragment.newInstance(uid)).commitNow()
        }

    }

    private fun renderData(appState: AppState?) {
        when (appState){
            is AppState.Empty -> {}
            is AppState.Success<*> -> {

            }
            is AppState.Loading -> {
                viewModel.getUserProfileFromDb(uid)
            }
            is AppState.Error -> {
            }
            is AppState.SystemMessage -> {
            }
        }
    }

}