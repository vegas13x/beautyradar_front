package com.nick_sib.beauty_radar

import android.app.Application
import com.nick_sib.beauty_radar.di.*
import org.koin.core.context.startKoin

/**
 * @author Alex Volkov(Volkos)
 *
 * Класс говорит сам за себя=)
 */
class BeautyRadarApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    appModule,
                    authFragmentModule,
                    logoutModule,
                    initialProfileModule,
                    enterCodeFragmentModule
                )
            )
        }
    }
}