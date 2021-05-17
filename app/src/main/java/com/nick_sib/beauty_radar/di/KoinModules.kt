package com.nick_sib.beauty_radar.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.AuthProviderImpl
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.calendar.RemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider.provider_db.ProviderRemoteDBImpl
import com.nick_sib.beauty_radar.model.provider.retrofit.RetrofitImplementation
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.model.repository.impl.RemoteRepositoryImpl
import com.nick_sib.beauty_radar.view.utils.SECURE_PREFS_FILE_KEY
import com.nick_sib.beauty_radar.view_model.*
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import com.nick_sib.beauty_radar.view_model.interactor.impl.EnterCodeInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.MasterClientInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.SignUpInteractorImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @author Alex Volkov(Volkoks)
 *
 * Created 08.04.2021
 */

val appModule = module {

    single { FirebaseAuth.getInstance() }
    single<IAuthProvider> { AuthProviderImpl(get()) }
    single { RetrofitImplementation().createRetrofit() }

    //Старый
    single<IRemoteDBProviderCalendar> { RemoteDBProviderCalendar() }

    single<IProviderRemoteDB> { ProviderRemoteDBImpl(get()) }
    factory<RemoteRepository<AppState>> { RemoteRepositoryImpl(get(), get()) }

}

val authFragmentModule = module {
    viewModel { SignViewModel(get()) }
}

val enterCodeFragmentModule = module {
    factory<EnterCodeInteractor<AppState>> { EnterCodeInteractorImpl(get()) }
    viewModel { EnterCodeViewModel(get(), get()) }
}

val signUpModule = module {
    factory<SignUpInteractor<AppState>> { SignUpInteractorImpl(get()) }
    viewModel { SignUpViewModel() }
}

val signUpSecondModule = module {
    viewModel { SignUpSecondViewModel(get()) }
}

val masterClientFragmentModule = module {
    factory<MasterClientInteractor<AppState>> { MasterClientInteractorImpl(get()) }
    viewModel { MasterClientViewModel(get()) }
}
val masterClientInnerFragmentModule = module {
    viewModel { MasterAndClientInnerViewModel() }
}

val settingModule = module {
    viewModel { SettingViewModel() }
}

val calendarModule = module {
    viewModel { CalendarViewModel() }
}

val welcomeFragmenModule = module {
    viewModel { WelcomeViewModel() }
}

val profileInfoEditModule = module {
    viewModel { ProfileInfoEditViewModel() }
}

val profileInfoModule = module {
    viewModel { ProfileInfoViewModel() }
}

//val preferencesModule = module {
//    single(named("securePrefs")) { provideSecurePreferences(androidApplication()) }
//}
//
//private fun provideSecurePreferences(app: Application): SharedPreferences =
//    app.getSharedPreferences(SECURE_PREFS_FILE_KEY, Context.MODE_PRIVATE)