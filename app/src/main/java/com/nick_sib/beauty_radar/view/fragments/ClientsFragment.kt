package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentClientsBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.phone_clients.RemoteDBProviderPhone
import com.nick_sib.beauty_radar.view.adapter.PhoneClientsAdapter
import com.nick_sib.beauty_radar.view_model.ClientsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientsFragment : Fragment(R.layout.fragment_clients) {

    private val viewModel: ClientsViewModel by viewModel()
    private lateinit var binding: FragmentClientsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClientsBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        navBarInit()
        initRV(view)
    }

    private fun initRV(view: View) {
        val rv: RecyclerView = view.findViewById(R.id.phoneRecyclerView)
        rv.layoutManager = LinearLayoutManager(activity)
        val recyclerAdapter = PhoneClientsAdapter(RemoteDBProviderPhone().getPhoneClients())
        rv.adapter = recyclerAdapter
    }

    private fun navBarInit() {
        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {
                    findNavController().navigate(ClientsFragmentDirections.actionClientsFragmentSelf())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    findNavController().navigate(ClientsFragmentDirections.actionClientsFragmentToProfileInfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_main -> {
                    findNavController().navigate(ClientsFragmentDirections.actionClientsFragmentToMasterClientsFragment())
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