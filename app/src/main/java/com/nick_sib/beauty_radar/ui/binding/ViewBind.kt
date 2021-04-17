package com.nick_sib.beauty_radar.ui.binding

import android.app.Activity
import android.view.View
import android.view.ViewParent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.extension.getActivity
import com.nick_sib.beauty_radar.extension.phoneToDigit

private fun getParent(v: View): ViewParent? {
    var pp: ViewParent? = v.parent ?: null
    while (pp !is ConstraintLayout) {
        if (pp == null) break
        pp = pp.parent
    }
    return pp
}

fun getPhone(view: View): String =
    getParent(view)?.let {
        (it as View).findViewById<EditText>(R.id.auth_phone)?.text.toString().phoneToDigit()
    } ?: ""

@BindingAdapter("bind:onSingIn")
fun View.onSingIn(function: Function1<Pair<String, Activity?>, Unit>?){
    when (this) {
        is Button -> setOnClickListener {
           function?.let{ it(getPhone(this) to this.getActivity()) }
        }
        is TextInputEditText -> setOnEditorActionListener{ _, actionId, _ ->
            ((actionId == EditorInfo.IME_ACTION_DONE) && (function != null)).also {
                function?.let{ it(getPhone(this) to this.getActivity()) }
            }
        }
    }
}

@BindingAdapter("bind:resendSMS")
fun View.resendSMS(function: Function1<Activity?, Unit>?){
    when (this) {
        is Button -> setOnClickListener {
            function?.let{ it(this.getActivity()) }
        }
    }
}