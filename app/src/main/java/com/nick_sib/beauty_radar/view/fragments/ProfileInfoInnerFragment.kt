package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentProfileInfoInnerBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile
import com.nick_sib.beauty_radar.view.utils.FINISH_BUTTON_MASTER_REG
import com.nick_sib.beauty_radar.view_model.ProfileInfoInnerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileInfoInnerFragment : Fragment(R.layout.fragment_profile_info_inner) {

    private val viewModel: ProfileInfoInnerViewModel by viewModel()
    private lateinit var binding: FragmentProfileInfoInnerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileInfoInnerBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        viewModel.getInfo()

    }

    fun newInstance(): ProfileInfoInnerFragment {
        return ProfileInfoInnerFragment()
    }


    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> ->
                when (appState.data) {
                    is UserMasterProfile -> {
                        var userMasterProfile = appState.data
                        binding.address.text = userMasterProfile.address.toString()
                        binding.phone.text = userMasterProfile.phone.toString()
                        binding.dateBirthday.text = userMasterProfile.dateBirthday.toString()
                        binding.aboutUrself.text = userMasterProfile.aboutUrself.toString()
                        binding.nameInfoInner.text = userMasterProfile.name.toString() + " " + userMasterProfile.surname.toString()
                    }
                }
        }
    }

}