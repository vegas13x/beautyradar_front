package com.nick_sib.beauty_radar.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientSecondBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.provider.calendar.CalendarProfile
import com.nick_sib.beauty_radar.view.adapter.ClientAdapter
import com.nick_sib.beauty_radar.view.utils.TRANSITION_TO_CALENDAR
import com.nick_sib.beauty_radar.view.utils.USE_DEFAULT_IMG
import com.nick_sib.beauty_radar.view_model.MasterClientViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterClientFragment : Fragment(R.layout.fragment_master_client_second) {

    private val viewModel: MasterClientViewModel by viewModel()
    private var binding: FragmentMasterClientSecondBinding? = null
    private var adapter: ClientAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMasterClientSecondBinding.bind(view)
        binding?.viewModel = viewModel

        viewModel.getListClients()
        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding?.fragmentMcBtnNavBar?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {
                    findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    val uid: String? = SingletonUID.getUID()
                    uid?.let {
                        findNavController().navigate(
                            MasterClientFragmentDirections.actionMasterClientsFragmentToProfileInfoFragment()
                        )
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

//        binding?.fragmentMcBtnSetting?.setOnClickListener {
//            findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToSettingsOneFragment2())
//        }
//
//        viewModel.takePictureFromStorage()

        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        viewModel.takePictureFromStorage()

    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
//                    TRANSITION_TO_CALENDAR -> {
//                        Toast.makeText(
//                            requireContext(),
//                            "Переход на экран календаря",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    is List<*> -> {
//                        if (adapter == null) {
//                            adapter = ClientAdapter(appState.data as List<CalendarProfile>)
//                            binding.clientRecycler.adapter = adapter
//                        }
//                    }
//                    is Bitmap -> {
//                        binding?.imgAvatar?.let {
//                            Glide.with(requireContext())
//                                .load(appState.data)
//                                .circleCrop()
//                                .into(it)
//                        }
//                    }
//
//                    USE_DEFAULT_IMG -> {
//                        binding?.imgAvatar?.let {
//                            Glide.with(requireContext())
//                                .load(R.drawable.img_dog)
//                                .circleCrop()
//                                .into(it)
//                        }
//                    }
                    TRANSITION_TO_CALENDAR->{
                        Toast.makeText(
                            requireContext(),
                            "Переход на экран календаря",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is List<*> -> {
                        if (adapter == null) {
                            adapter = ClientAdapter(appState.data as List<CalendarProfile>)
                            binding?.clientRecycler?.adapter = adapter
                        }
                    }
                    is Bitmap -> {
                        binding?.fragmentMcIvAvatarMaster?.setImageBitmap(appState.data)
                    }
                }
            }
            else -> {}
        }
    }
//    private fun renderData(appState: AppState?) {
//        when (appState) {
//            is AppState.Success<*> -> {
//                when (appState.data) {
//                    TRANSITION_TO_CALENDAR->{
//                        findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToCalendarFragment())
//                    }
//                    is List<*> -> {
//                        if (adapter == null) {
//                            adapter = ClientAdapter(appState.data as List<CalendarProfile>)
//                            binding?.clientRecycler?.adapter = adapter
//                        }
//                    }
//                    is Bitmap -> {
//                        binding?.fragmentMcIvAvatarMaster?.setImageBitmap(appState.data)
//                    }
//                }
//            }
//            else -> {}
//        }
//    }

    override fun onPause() {
        super.onPause()
        adapter = null
    }


    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 3
        override fun getItem(position: Int): Fragment = BooksFragment().newInstance()
        override fun getPageTitle(position: Int): CharSequence? {
            var title  = ""
////            when(position) {
////                0 -> title ="Tech"
////                1 -> title = "Novels"
////                2 -> title = "Motivational"
////            }
            return title
        }
    }


}