package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentConsumerRecordingBinding
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.OPEN_BOTTOM_SHEET_CHOOSE_SERVICE
import com.nick_sib.beauty_radar.view_model.ConsumerRecordingViewModel

class ConsumerRecordingFragment : Fragment(R.layout.fragment_consumer_recording) {

    private val viewModel: ConsumerRecordingViewModel by viewModels()
    private var binding: FragmentConsumerRecordingBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConsumerRecordingBinding.bind(view)
        binding?.viewModel = viewModel
        viewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    OPEN_BOTTOM_SHEET_CHOOSE_SERVICE ->
                        binding?.fragmentConsumerRecordingBsChooseService?.bottomSheetSsContainer?.visibility =
                            ViewGroup.VISIBLE
                }
            }
        }
    }
}