package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentClientRecordInnerBinding
import com.nick_sib.beauty_radar.databinding.FragmentMasterAndClientInnerBinding
import com.nick_sib.beauty_radar.databinding.FragmentProfileInfoInnerBinding
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.FINISH_BUTTON_MASTER_REG
import com.nick_sib.beauty_radar.view_model.ProfileInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileInfoInnerFragment : Fragment(R.layout.fragment_profile_info_inner) {


    fun newInstance(): ProfileInfoInnerFragment {
        return ProfileInfoInnerFragment()
    }


    private val viewModel: ProfileInfoViewModel by viewModel()
    private lateinit var binding: FragmentProfileInfoInnerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileInfoInnerBinding.bind(view)


        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

//        binding.editPen.setOnClickListener {
//            findNavController()
//                    .navigate(ProfileInfoFragmentDirections
//                    .actionProfileInfoFragmentToProfileInfoEditFragment()
//                )
//        }
//
//        binding.imgBack.setOnClickListener {
//            findNavController()
//                    .navigate(ProfileInfoFragmentDirections
//                    .actionProfileInfoFragmentToProfileInfoEditFragment())
//        }

        viewModel.finishButton()



//        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_btm_nav_btn_clients -> {
//                findNavController().navigate(ProfileInfoFragmentDirections.actionProfileInfoFragmentToClientsFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.menu_btm_nav_btn_profile -> {
//
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.menu_btm_nav_btn_main -> {
//                    findNavController().navigate(ProfileInfoFragmentDirections.actionProfileInfoFragmentToMasterClientsFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                else -> false
//            }
//        }

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