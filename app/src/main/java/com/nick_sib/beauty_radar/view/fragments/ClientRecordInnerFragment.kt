package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentClientRecordInnerBinding
import com.nick_sib.beauty_radar.databinding.FragmentProfileInfoInnerBinding
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view_model.MasterAndClientInnerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientRecordInnerFragment : Fragment(R.layout.fragment_client_record_inner) {


    private val viewModel: MasterAndClientInnerViewModel by viewModel()
    private lateinit var binding: FragmentClientRecordInnerBinding

    fun newInstance(): ClientRecordInnerFragment {
        return ClientRecordInnerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_client_record_inner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClientRecordInnerBinding.bind(view)
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