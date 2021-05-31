package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentCalendarBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.view_model.CalendarViewModel
import org.koin.android.ext.android.bind

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModel()

    private lateinit var binding: FragmentCalendarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCalendarBinding.bind(view)

        binding.fragmentCalendarBtnBackTo.setOnClickListener {
            findNavController().navigate(CalendarFragmentDirections.actionCalendarFragmentToMasterClientsFragment2())
        }
    }
}