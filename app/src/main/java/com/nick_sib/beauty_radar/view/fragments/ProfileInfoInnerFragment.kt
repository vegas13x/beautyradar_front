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

        //btnInit()
        //navBarInit()
    }

    fun newInstance(): ProfileInfoInnerFragment {
        return ProfileInfoInnerFragment()
    }

//    private fun btnInit() {
//        binding.root.findViewById<ImageView>(R.id.edit_pen).setOnClickListener {
//            findNavController()
//                .navigate(
//                    ProfileInfoFragmentDirections
//                        .actionProfileInfoFragmentToProfileInfoEditFragment()
//                )
//        }
//
//        binding.root.findViewById<ImageView>(R.id.img_back).setOnClickListener {
//            findNavController()
//                .navigate(
//                    ProfileInfoFragmentDirections
//                        .actionProfileInfoFragmentToProfileInfoEditFragment()
//                )
//        }
//    }

//    private fun navBarInit() {
//        binding.root.findViewById<BottomNavigationView>(R.id.fragment_mc_btn_nav_bar)
//            .setOnNavigationItemSelectedListener {
//                when (it.itemId) {
//                    R.id.menu_btm_nav_btn_clients -> {
//                        findNavController().navigate(ProfileInfoFragmentDirections.actionProfileInfoFragmentToClientsFragment())
//                        return@setOnNavigationItemSelectedListener true
//                    }
//                    R.id.menu_btm_nav_btn_profile -> {
//
//                        return@setOnNavigationItemSelectedListener true
//                    }
//                    R.id.menu_btm_nav_btn_main -> {
//                        findNavController().navigate(ProfileInfoFragmentDirections.actionProfileInfoFragmentToMasterClientsFragment())
//                        return@setOnNavigationItemSelectedListener true
//                    }
//                    else -> false
//                }
//            }
//    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> ->
                when (appState.data) {
                    FINISH_BUTTON_MASTER_REG -> {
                    }
                }
        }
    }

}