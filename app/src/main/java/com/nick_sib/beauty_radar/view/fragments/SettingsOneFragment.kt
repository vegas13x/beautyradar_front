package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.databinding.FragmentSettingsOneBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.ProfileInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsOneFragment : Fragment(R.layout.fragment_settings_one) {

    private val viewModel: ProfileInfoViewModel by viewModel()
    private lateinit var binding: FragmentSettingsOneBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsOneBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })


        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    val uid: String? = SingletonUID.getUID()
                    uid?.let {

                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_main -> {
                    findNavController().navigate(SettingsOneFragmentDirections.actionSettingsOneFragment2ToMasterClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> ->
                when (appState.data) {

                }
        }
    }




}