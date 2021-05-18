package com.nick_sib.beauty_radar.view.fragments

import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.ClientRecordFragmentBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.ProfileInfoViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClientRecordFragment : Fragment(R.layout.client_record_fragment) {


    private val viewModel: ProfileInfoViewModel by viewModel()
    private lateinit var binding: ClientRecordFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ClientRecordFragmentBinding.bind(view)


        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        binding.viewPager02.adapter = pagerAdapter

//        binding.viewPager02.orientation = ViewPager2.ORIENTATION_VERTICAL


        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })



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

    private fun padding() {

    }


    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> ->
                when (appState.data) {

                }
        }
    }


    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 1
        override fun getItem(position: Int): Fragment = MasterAndClientInnerFragment().newInstance()
    }


}







