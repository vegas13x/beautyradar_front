package com.nick_sib.beauty_radar.di

import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth_.AuthProviderImpl
import com.nick_sib.beauty_radar.model.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.calendar.RemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.model.provider.profile.RemoteDBProviderProfile
import com.nick_sib.beauty_radar.model.provider_new.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider_new.provider_db.ProviderRemoteDBImpl
import com.nick_sib.beauty_radar.model.provider_new.retrofit.RetrofitImplementation
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.model.repository.impl.RemoteRepositoryImpl
import com.nick_sib.beauty_radar.model.room.HistoryDataBase
import com.nick_sib.beauty_radar.model.room.IRoomSource
import com.nick_sib.beauty_radar.model.room.RoomDataBaseImplementation
import com.nick_sib.beauty_radar.view_model.*
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.ProfileInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.SignUpInteractor
import com.nick_sib.beauty_radar.view_model.interactor.impl.EnterCodeInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.MasterClientInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.ProfileInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.SignUpInteractorImpl
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

    //Старый провайдер Firebase *******************

    single<IRemoteDBProviderProfile> { RemoteDBProviderProfile() }
    single<IRemoteDBProviderCalendar> { RemoteDBProviderCalendar() }

    //*********************************************

    //Новый провайдер для БД бэка******************

    single<IProviderRemoteDB> { ProviderRemoteDBImpl(get()) }
    factory<RemoteRepository<AppState>> { RemoteRepositoryImpl(get(), get()) }

    //**********************************************

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<IRoomSource> { RoomDataBaseImplementation(get()) }

}

val authFragmentModule = module {
    viewModel { AuthViewModel(get()) }
}

val enterCodeFragmentModule = module {
    factory<EnterCodeInteractor<AppState>> { EnterCodeInteractorImpl(get()) }
    viewModel { EnterCodeViewModel(get(), get(), get()) }
}

val signUpModule = module {
    factory<SignUpInteractor<AppState>> { SignUpInteractorImpl(get()) }
    viewModel { SignUpViewModel() }
}

val signUpSecondModule = module {
    viewModel { SignUpSecondViewModel(get(), get()) }
}

val masterClientFragmentModule = module {
    factory<MasterClientInteractor<AppState>> { MasterClientInteractorImpl(get()) }
    viewModel { MasterClientViewModel(get()) }
}

val profileModule = module {
    factory<ProfileInteractor<AppState>> { ProfileInteractorImpl(get()) }
    viewModel { ProfileViewModel(get(), get()) }
}

val logoutModule = module {
    viewModel { LogoutViewModel(get()) }
}

val initialProfileModule = module {
    viewModel { InitialProfileSetupViewModel(get()) }
}

