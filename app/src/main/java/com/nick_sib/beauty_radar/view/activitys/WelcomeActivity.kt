package com.nick_sib.beauty_radar.view.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.nick_sib.beauty_radar.R

class WelcomeActivity : Activity() {

    private val splashDuration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_welcome)
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }, splashDuration)
    }
}