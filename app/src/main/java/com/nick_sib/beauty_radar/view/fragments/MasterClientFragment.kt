package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.adapter.ClientAdapter
import com.nick_sib.beauty_radar.view_model.MasterClientViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MasterClientFragment : Fragment(R.layout.fragment_master_client) {

    fun newInstance(): MasterClientFragment {
        return MasterClientFragment()
    }

    private val viewModel: MasterClientViewModel by viewModel()
    private lateinit var binding: FragmentMasterClientBinding
    private var adapter: ClientAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMasterClientBinding.bind(view)
        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        navBarInit()
        btnInit()
        bottomSheetInit()
//        pagerInit()

    }



//    private fun pagerInit() {
//        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
//        binding.viewPager.adapter = pagerAdapter
//    }

//    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
//        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//        override fun getCount(): Int = 1
//        override fun getItem(position: Int): Fragment = MasterAndClientInnerFragment().newInstance()
//    }

    private fun bottomSheetInit() {
        val bottomFragment = BottomSheetFragment()
        val bottomSheetBehaviour = BottomSheetBehavior.from(binding.containerBottomSheet)

        childFragmentManager.beginTransaction()
            .replace(R.id.containerBottomSheet, bottomFragment)
            .commit()

        binding.root.findViewById<AppCompatTextView>(R.id.fragment_mc_tv_sessions)
            .setOnClickListener {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
    }

    private fun btnInit() {
        binding.root.findViewById<ImageView>(R.id.settings).setOnClickListener {
            findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToSettingsFragment())
        }

        binding.root.findViewById<ImageView>(R.id.back_image01).setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun navBarInit() {
        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {
                    findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToProfileInfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_main -> {
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }

    override fun onPause() {
        super.onPause()
        adapter = null
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    else -> {}
                }
            }
            else -> {}
        }
    }

}