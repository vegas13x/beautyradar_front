package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.ClientRecordFragmentBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.ProfileInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientRecordFragment : Fragment(R.layout.client_record_fragment) {

    private val viewModel: ProfileInfoViewModel by viewModel()
    private lateinit var binding: ClientRecordFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ClientRecordFragmentBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        pagerInit()
        navBarInit()
    }

    private fun navBarInit() {
        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {
                    findNavController().navigate(ClientRecordFragmentDirections.actionClientRecordFragmentToClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    findNavController().navigate(ClientRecordFragmentDirections.actionClientRecordFragmentToProfileInfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_main -> {
                    findNavController().navigate(ClientRecordFragmentDirections.actionClientRecordFragmentToMasterClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 1
        override fun getItem(position: Int): Fragment = ClientRecordInnerFragment().newInstance()
    }

    private fun pagerInit() {
        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        binding.viewPager02.adapter = pagerAdapter
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> ->
                when (appState.data) {

                }
        }
    }

}







