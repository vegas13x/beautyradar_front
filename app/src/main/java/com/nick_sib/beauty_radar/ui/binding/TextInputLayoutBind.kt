package com.nick_sib.beauty_radar.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:setError")
fun textInputLayoutSetError(view: View, value: Int){
    (view as TextInputLayout).apply {
        error = if (value == 0) null else view.context.getString(value)
        isErrorEnabled = value != 0
    }
}