package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentBottomSheetForMcBinding
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.service.ServiceProfile
import com.nick_sib.beauty_radar.view.adapter.ServiceAdapter
import com.nick_sib.beauty_radar.view_model.BottomSheetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : Fragment(R.layout.fragment_bottom_sheet_for_mc) {

    private val viewModel: BottomSheetViewModel by viewModel()
    private var binding: FragmentBottomSheetForMcBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBottomSheetForMcBinding.bind(view)
        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })
        viewModel.getListService()
        initRecyclerView()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    is List<*> -> {
                        binding?.recycle?.adapter =
                            ServiceAdapter(appState.data as List<ServiceProfile>)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding?.recycle?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        val lp: ViewGroup.LayoutParams? = binding?.recycle?.layoutParams
        lp?.width = ViewGroup.LayoutParams.MATCH_PARENT
        lp?.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding?.recycle?.requestLayout()
        binding?.recycle?.layoutManager = layoutManager
    }

}