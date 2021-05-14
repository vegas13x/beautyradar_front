package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.adapter.ClientAdapter
import com.nick_sib.beauty_radar.view_model.MasterClientViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterClientFragment : Fragment(R.layout.fragment_master_client) {

    private val viewModel: MasterClientViewModel by viewModel()
    private lateinit var binding: FragmentMasterClientBinding
    private var adapter: ClientAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMasterClientBinding.bind(view)

        viewModel.getListClients()
        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

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
                else -> false
            }
        }

        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter

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

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 1
        override fun getItem(position: Int): Fragment = MasterAndClientInnerFragment().newInstance()
    }

}