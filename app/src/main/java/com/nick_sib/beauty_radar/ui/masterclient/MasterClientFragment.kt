package com.nick_sib.beauty_radar.ui.masterclient

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentMasterClientBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MasterClientFragment : Fragment(R.layout.fragment_master_client) {



    companion object {
        fun newInstance() = MasterClientFragment()
    }

    private val viewModel: MasterClientViewModel by viewModel()
    private var binding: FragmentMasterClientBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMasterClientBinding.bind(view)

        binding!!.clientRecycler.adapter = ClientAdapter()

    }





}