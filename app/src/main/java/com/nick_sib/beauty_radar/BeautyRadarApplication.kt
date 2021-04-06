package com.nick_sib.beauty_radar

import android.app.Application
import com.nick_sib.beauty_radar.di.appModule
import com.nick_sib.beauty_radar.di.authModule
import com.nick_sib.beauty_radar.di.checkInModule
import org.koin.core.context.startKoin


class BeautyRadarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, authModule, checkInModule))
        }
    }
}