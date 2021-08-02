package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
//        pagerInit()
        bottomSheetInit()


    }

    private fun bottomSheetInit() {
        val bottomFragment = BottomSheetFragment()
        val bottomSheetBehaviour = BottomSheetBehavior.from(binding.containerBottomSheet)

        childFragmentManager.beginTransaction()
            .replace(R.id.containerBottomSheet, bottomFragment)
            .commit()

        binding.root.findViewById<AppCompatTextView>(R.id.fragment_specialization).setOnClickListener {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.root.findViewById<AppCompatTextView>(R.id.fragment_mc_tv_sessions).setOnClickListener {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.root.findViewById<AppCompatTextView>(R.id.viezd_na_dom).setOnClickListener {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.root.findViewById<AppCompatTextView>(R.id.notifications).setOnClickListener {
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
        }

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

        binding.root.findViewById<Button>(R.id.fragment_mc_graphic_raboty).setOnClickListener {
            findNavController()
                .navigate(
                    ProfileInfoFragmentDirections.actionProfileInfoFragmentToWorkingDaysFragment()
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

//    private fun pagerInit() {
//        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
//        binding.viewPager.adapter = pagerAdapter
//    }


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