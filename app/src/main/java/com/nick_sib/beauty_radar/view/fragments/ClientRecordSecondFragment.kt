package com.nick_sib.beauty_radar.view.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.ClientRecordFragment2Binding
import com.nick_sib.beauty_radar.databinding.FragmentMasterAndClientInnerBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.MasterAndClientInnerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientRecordSecondFragment : Fragment(R.layout.client_record_fragment_2) {

    private val viewModel: MasterAndClientInnerViewModel by viewModel()
    private lateinit var binding: ClientRecordFragment2Binding

    fun newInstance(): ClientRecordSecondFragment {
        return ClientRecordSecondFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.client_record_fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ClientRecordFragment2Binding.bind(view)
        viewModel.takePictureFromStorage()




        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {

                }
            }
            else -> {}
        }
    }


}