package com.nick_sib.beauty_radar.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.CheckInFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInFragment : Fragment(R.layout.check_in_fragment) {

    companion object {
        fun newInstance() = CheckInFragment()
    }

    private val viewModel: CheckInViewModel by viewModel()
    private var binding: CheckInFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CheckInFragmentBinding.bind(view)
    }

}