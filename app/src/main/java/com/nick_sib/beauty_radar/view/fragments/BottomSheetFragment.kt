package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentBottomSheetForMcBinding
import com.nick_sib.beauty_radar.view.adapter.ServiceAdapter
import com.nick_sib.beauty_radar.view.utils.ServiceItem
import com.nick_sib.beauty_radar.view_model.CalendarViewModel

class BottomSheetFragment: Fragment(R.layout.fragment_bottom_sheet_for_mc) {

    private var binding: FragmentBottomSheetForMcBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = mutableListOf<ServiceItem>()
        val serviceItem = ServiceItem()
        serviceItem.itemServiceName = "Ногтевые ногти"
        list.add(serviceItem)
        list.add(serviceItem)
        list.add(serviceItem)
        list.add(serviceItem)
        list.add(serviceItem)
        list.add(serviceItem)
        list.add(serviceItem)
        list.add(serviceItem)
        Log.d("TAG55555", "onViewCreated: $list")
        binding?.recycle?.adapter = ServiceAdapter(list)
        initRecyclerView()
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