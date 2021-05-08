package com.nick_sib.beauty_radar

import android.app.Application
import androidx.room.Room
import com.nick_sib.beauty_radar.di.*
import com.nick_sib.beauty_radar.model.room.HistoryDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.coroutines.coroutineContext

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
                    logoutModule,
                    profileModule,
                    enterCodeFragmentModule,
                    masterClientFragmentModule,
                    enterCodeFragmentModule,
                    signUpModule,
                    signUpSecondModule
                    ,databaseModule
                )
            )
        }
    }
}