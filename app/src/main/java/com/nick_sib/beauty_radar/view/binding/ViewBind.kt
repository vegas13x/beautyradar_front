package com.nick_sib.beauty_radar.view.binding

import android.app.Activity
import android.view.View
import android.view.ViewParent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

fun getDigit(view: View, res: Int): String =
    view.findViewById<EditText>(res)?.text.toString()

fun getPin(view: View): String =
    getDigit(view, R.id.fragment_enter_code_digit_edittext1)+
    getDigit(view, R.id.fragment_enter_code_digit_edittext2)+
    getDigit(view, R.id.fragment_enter_code_digit_edittext3)+
    getDigit(view, R.id.fragment_enter_code_digit_edittext4)+
    getDigit(view, R.id.fragment_enter_code_digit_edittext5)+
    getDigit(view, R.id.fragment_enter_code_digit_edittext6)

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

@BindingAdapter("bind:onEnterPin")
fun View.onEnterPin(function: Function1<String, Unit>?){
    when (this) {
        is Button -> setOnClickListener {
            function?.let{ it(getPin(getParent(this) as View)) }
        }
        is EditText -> setOnEditorActionListener{ _, actionId, _ ->
            ((actionId == EditorInfo.IME_ACTION_DONE) && (function != null)).also {
                if (it) function?.let{ it(getPin(getParent(this) as View)) }
            }
        }
    }
}

@BindingAdapter("bind:resendSMS")
fun View.resendSMS(function: Function1<Activity?, Unit>?){
    when (this) {
        is TextView -> setOnClickListener {
            function?.let{ it(this.getActivity()) }
        }
    }
}

@BindingAdapter("bind:setDotColor")
fun setDotColor(view: View, value: Boolean){
//    if (value)
//        view.background = view.context.getDrawable(R.drawable.ic_circle_error)
}