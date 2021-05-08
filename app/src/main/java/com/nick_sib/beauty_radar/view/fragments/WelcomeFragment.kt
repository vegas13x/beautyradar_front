package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentWelcomeBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.view_model.WelcomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    private val viewModel: WelcomeViewModel by viewModel()
    private var binding: FragmentWelcomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)

         binding?.run {
             fragmentWelcomeButtonSingUp.setOnClickListener {
                 findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToAuthFragment())
             }
             fragmentWelcomeButtonSingIn.setOnClickListener {
                 findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToAuthFragment())
             }
         }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }



}