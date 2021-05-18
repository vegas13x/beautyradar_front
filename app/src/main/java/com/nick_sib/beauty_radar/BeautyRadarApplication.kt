package com.nick_sib.beauty_radar

import android.app.Application
import com.nick_sib.beauty_radar.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Alex Volkov(Volkos)
 *
 * Created 08.04.2021
 */
class BeautyRadarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    appModule,
                    authFragmentModule,
                    calendarModule,
                    welcomeFragmenModule,
                    enterCodeFragmentModule,
                    masterClientFragmentModule,
                    masterClientInnerFragmentModule,
                    settingModule,
                    enterCodeFragmentModule,
                    signUpModule,
                    signUpSecondModule,
                    profileInfoEditModule,
                    profileInfoModule
                )
            )
        }
    }
}