package com.nick_sib.beauty_radar.ui.logout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nick_sib.beauty_radar.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutFragment : Fragment(R.layout.logout_fragment) {

    companion object {
        fun newInstance() = LogoutFragment()
    }

    private val viewModel: LogoutViewModel by viewModel()


}