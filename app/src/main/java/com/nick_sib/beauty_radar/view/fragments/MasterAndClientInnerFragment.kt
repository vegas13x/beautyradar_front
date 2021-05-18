package com.nick_sib.beauty_radar.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentMasterAndClientInnerBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.adapter.ClientAdapter
import com.nick_sib.beauty_radar.view.utils.ListOfClients
import com.nick_sib.beauty_radar.view.utils.TRANSITION_TO_CALENDAR
import com.nick_sib.beauty_radar.view_model.MasterAndClientInnerViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterAndClientInnerFragment : Fragment() {

    private val viewModel: MasterAndClientInnerViewModel by viewModel()
    private lateinit var binding: FragmentMasterAndClientInnerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View? = inflater.inflate(R.layout.fragment_master_and_client_inner, container, false)
        val rvBooks : RecyclerView = view!!.findViewById(R.id.clientRecycler)
        rvBooks.layoutManager = LinearLayoutManager(activity)
        val recyclerAdapter = ClientAdapter(ListOfClients().getClients())
        rvBooks.adapter = recyclerAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMasterAndClientInnerBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        viewModel.takePictureFromStorage()

        btnInit()

    }

    private fun btnInit() {
        binding.fragmentMcBtnSingUp.setOnClickListener {
            findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToClientRecordFragment())
        }

        binding.fragmentMcBtnCalendar.setOnClickListener {
            findNavController().navigate(MasterClientFragmentDirections.actionMasterClientsFragmentToCalendarFragment())
        }
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    is Bitmap ->
                        Glide.with(requireContext())
                            .load(appState.data)
                            .circleCrop()
                            .into(binding.imgAvatar)

                    else -> {}
                }
            }
            else -> {}
        }
    }


}