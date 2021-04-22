package com.nick_sib.beauty_radar.ui.masterclient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import com.nick_sib.beauty_radar.provider.profile.entities.CalendareProfile
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.profileScreen.ProfileFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterClientFragment : Fragment(R.layout.fragment_master_client) {

    private val testListData = listOf(
        CalendareProfile("fkglknglksnglknf", "Саша", "1", "массаж"),
        CalendareProfile("fkglknglksngsadsadlknf", "Vasya", "4", "шугаринг"),
        CalendareProfile("fkglknglksngsadaflknf", "Olya", "4", "увеличение чего то"),
        CalendareProfile("fkglknglksnglknf", "Petya", "1", "уменьшение чего то"),
        CalendareProfile("fkglkngldsadlknf", "Ira", "4", "ресницы"),
        CalendareProfile("fkglksadaflknf", "Lesha", "4", "губы")
    )

    companion object {
        fun newInstance() = MasterClientFragment()
    }

    private val viewModel: MasterClientViewModel by viewModel()
    private var binding: FragmentMasterClientBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMasterClientBinding.bind(view)

        binding?.clientRecycler?.adapter = ClientAdapter(testListData)

        binding?.fragmentMcBtnNavBar?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_setting -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.main_activity_container,
                            LogoutFragment.newInstance()
                        )
                        .addToBackStack("Logout").commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.main_activity_container,
                            ProfileFragment.newInstance("kdkjdsvkjnsdvsv")
                        )
                        .addToBackStack("Logout").commit()

                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }


}