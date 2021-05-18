package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientSecondBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.adapter.ClientAdapter
import com.nick_sib.beauty_radar.view.adapter.ServiceAdapter
import com.nick_sib.beauty_radar.view.utils.ServiceItem
import com.nick_sib.beauty_radar.view_model.MasterClientViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterClientFragment : Fragment(R.layout.fragment_master_client_second) {

    private val viewModel: MasterClientViewModel by viewModel()
    private lateinit var binding: FragmentMasterClientSecondBinding
    private var adapter: ClientAdapter? = null
    var b: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMasterClientSecondBinding.bind(view)

        viewModel.getListClients()
        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })



//        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_btm_nav_btn_clients -> {
//                    findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToClientsFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.menu_btm_nav_btn_profile -> {
//                    findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToProfileInfoFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                else -> false
//            }
//        }

//        binding.root.findViewById<ImageView>(R.id.setting_btn).setOnClickListener {
//            findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToSettingsFragment())
//        }
//
//        binding.root.findViewById<ImageView>(R.id.back_btn).setOnClickListener {
//            findNavController().popBackStack()
//        }

//        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
//        binding.viewPager.adapter = pagerAdapter


        val bottomFragment = BottomSheetFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.containerBottomSheet, bottomFragment)
            .commit()


        var bottomSheetBehaviour = BottomSheetBehavior.from(binding.containerBottomSheet);

        binding.button.setOnClickListener {
            if (b) {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                b = false
            } else {
                bottomSheetBehaviour.state = BottomSheetBehavior.STATE_HIDDEN
                b = true
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

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 1
        override fun getItem(position: Int): Fragment = MasterAndClientInnerFragment().newInstance()
    }


}