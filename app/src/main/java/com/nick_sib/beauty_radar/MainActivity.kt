package com.nick_sib.beauty_radar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nick_sib.beauty_radar.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, LoginFragment.newInstance()).commitNow()

    }
}