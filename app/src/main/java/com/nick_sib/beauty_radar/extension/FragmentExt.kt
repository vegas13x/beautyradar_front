package com.nick_sib.beauty_radar.extension

import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment

fun Fragment.requestFocus(view: View) {
    if (view.requestFocus()) {
        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        )
    }
}