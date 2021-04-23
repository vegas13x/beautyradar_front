package com.nick_sib.beauty_radar.ui.masterclient

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.provider.calendar.entities.CalendarProfile
import com.nick_sib.beauty_radar.ui.utils.TAG_DEBAG
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterClientFragment : Fragment(R.layout.fragment_master_client) {

    private val viewModel: MasterClientViewModel by viewModel()
    private var binding: FragmentMasterClientBinding? = null
    private var adapter: ClientAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMasterClientBinding.bind(view)

        viewModel.getListClients()
        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })
        binding?.fragmentMcBtnNavBar?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_setting -> {
                    findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToLogoutFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    val uid: String? = SingletonUID.getUID()
                    uid?.let {
                        findNavController().navigate(
                            MasterClientFragmentDirections.actionMasterClientsFragmentToProfileFragment(
                                uid
                            )
                        )
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Empty -> {
            }
            is AppState.Success<*> -> {
                when (appState.data) {
                    is List<*> -> {
                        Log.d(TAG_DEBAG, "renderData: list = ${appState.data}")
                        Log.d(TAG_DEBAG, "renderData: adapter = $adapter")
                        if (adapter == null) {
                            adapter = ClientAdapter(appState.data as List<CalendarProfile>)
                            binding?.clientRecycler?.adapter = adapter
                        }
                    }
                }


            }

            is AppState.Loading -> {

            }
            is AppState.Error -> {

            }
            is AppState.SystemMessage -> {

            }
        }
    }

    override fun onPause() {
        super.onPause()
        adapter = null
    }

}