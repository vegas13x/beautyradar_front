package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentProfileInfoSecondBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.FINISH_BUTTON_MASTER_REG
import com.nick_sib.beauty_radar.view_model.ProfileInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileInfoFragment : Fragment(R.layout.fragment_profile_info_second) {


    private val viewModel: ProfileInfoViewModel by viewModel()
    private lateinit var binding: FragmentProfileInfoSecondBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileInfoSecondBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        btnInit()
        navBarInit()
        pagerInit()


    }

    private fun btnInit() {
        binding.root.findViewById<ImageView>(R.id.edit_pen)?.setOnClickListener {
            findNavController()
                .navigate(
                    ProfileInfoFragmentDirections
                        .actionProfileInfoFragmentToProfileInfoEditFragment()
                )
        }


        binding.root.findViewById<ImageView>(R.id.back_image01).setOnClickListener {
            findNavController()
                .navigate(
                    ProfileInfoFragmentDirections
                        .actionProfileInfoFragmentToProfileInfoEditFragment()
                )
        }
    }

    private fun navBarInit() {
        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {
                    findNavController().navigate(ProfileInfoFragmentDirections.actionProfileInfoFragmentToClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_main -> {
                    findNavController().navigate(ProfileInfoFragmentDirections.actionProfileInfoFragmentToMasterClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun pagerInit() {
        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
    }


    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 1
        override fun getItem(position: Int): Fragment = ProfileInfoInnerFragment().newInstance()
    }

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