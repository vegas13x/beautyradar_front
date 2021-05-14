package com.nick_sib.beauty_radar.extension

import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.requestFocus(view: View) {
    if (view.requestFocus()) {
        this.activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        )
    }
}
