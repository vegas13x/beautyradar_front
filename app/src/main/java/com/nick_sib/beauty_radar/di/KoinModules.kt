package com.nick_sib.beauty_radar.di

import android.app.Application
import androidx.room.Room
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
import com.nick_sib.beauty_radar.model.room.HistoryDao
import com.nick_sib.beauty_radar.model.room.HistoryDataBase
import com.nick_sib.beauty_radar.view_model.*
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import com.nick_sib.beauty_radar.view_model.interactor.impl.EnterCodeInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.MasterClientInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.ProfileInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.SignUpInteractorImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single<IRemoteDBProviderCalendar> { RemoteDBProviderCalendar() }

    single<IProviderRemoteDB> { ProviderRemoteDBImpl(get()) }
    factory<RemoteRepository<AppState>> { RemoteRepositoryImpl(get(), get()) }

//    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
//    single { get<HistoryDataBase>().historyDao() }
}


val databaseModule = module {

    fun provideDatabase(application: Application): HistoryDataBase {
        return Room.databaseBuilder(application, HistoryDataBase::class.java, "MAkakaRoom")
            .build()
    }

    fun provideCountriesDao(database: HistoryDataBase): HistoryDao {
        return  database.historyDao()
    }

}

val authFragmentModule = module {
    viewModel { AuthViewModel(get()) }
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
    viewModel { SignUpSecondViewModel( get()) }
}

val masterClientFragmentModule = module {
    factory<MasterClientInteractor<AppState>> { MasterClientInteractorImpl(get()) }
    viewModel { MasterClientViewModel(get()) }
}

val profileModule = module {
    factory<ProfileInteractor<AppState>> { ProfileInteractorImpl(get()) }
    viewModel { ProfileViewModel( get()) }
}

val logoutModule = module {
    viewModel { LogoutViewModel(get()) }
}


val welcomeFragmenModule = module {
    viewModel { WelcomeViewModel() }
}