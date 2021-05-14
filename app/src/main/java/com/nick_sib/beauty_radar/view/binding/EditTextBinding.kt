package com.nick_sib.beauty_radar.view.binding

import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.KEYCODE_DEL
import android.view.View
import android.view.ViewParent
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.extension.requestFocus

private fun getParent(v: View): ViewParent? {
    var pp: ViewParent? = v.parent ?: null
    while (pp !is ConstraintLayout) {
        if (pp == null) break
        pp = pp.parent
    }
    return pp
}

@BindingAdapter("bind:onFillGoToForwardEdit")
fun onFillGoToForwardEdit(view: EditText, maxLength: Int) {
    view.addTextChangedListener{
        val parent = getParent(view)
        parent?.run{
            if (it?.length == maxLength)
                (parent as View).findViewById<EditText>(view.nextFocusForwardId)?.apply {
                    requestFocus()
                    selectAll()
                }
        }
    }
    view.setOnKeyListener { _, keyCode, event ->
        if (keyCode == KEYCODE_DEL && event.action == ACTION_UP) {
            val parent = getParent(view)
            (parent as View).findViewById<EditText>(view.nextFocusLeftId)?.apply {
                requestFocus()
                selectAll()
            }
        }
        event.action == ACTION_UP
    }
}