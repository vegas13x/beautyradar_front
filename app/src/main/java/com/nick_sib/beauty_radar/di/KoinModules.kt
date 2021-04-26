package com.nick_sib.beauty_radar.di

import androidx.room.Room
import com.nick_sib.beauty_radar.model.room.HistoryDataBase
import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth_.AuthProviderImpl
import com.nick_sib.beauty_radar.model.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.calendar.RemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.model.provider.profile.RemoteDBProviderProfile
import com.nick_sib.beauty_radar.model.provider_new.provider_db.ProviderRemoteDbImpl
import com.nick_sib.beauty_radar.model.provider_new.retrofit.RetrofitImplementation
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.model.repository.impl.RemoteRepositoryImpl
import com.nick_sib.beauty_radar.model.room.IRoomSource
import com.nick_sib.beauty_radar.model.room.RoomDataBaseImplementation
import com.nick_sib.beauty_radar.view_model.InitialProfileSetupViewModel
import com.nick_sib.beauty_radar.view_model.AuthViewModel
import com.nick_sib.beauty_radar.view_model.EnterCodeViewModel
import com.nick_sib.beauty_radar.view_model.LogoutViewModel
import com.nick_sib.beauty_radar.view_model.MasterClientViewModel
import com.nick_sib.beauty_radar.view_model.ProfileViewModel
import com.nick_sib.beauty_radar.view_model.SignUpViewModel
import com.nick_sib.beauty_radar.view_model.SignUpSecondViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor
import com.nick_sib.beauty_radar.view_model.interactor.impl.MasterClientInteractorImpl
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
    //Старый провайдер Firebase *******************
    single<IRemoteDBProviderProfile> { RemoteDBProviderProfile() }
    single<IRemoteDBProviderCalendar> { RemoteDBProviderCalendar() }
    //*********************************************

    //Новый провайдер для БД бэка******************
    single { ProviderRemoteDbImpl(get()) }
    //**********************************************
    single { RetrofitImplementation().createRetrofit() }
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<IRoomSource> { RoomDataBaseImplementation(get()) }
    factory<RemoteRepository<AppState>> { RemoteRepositoryImpl(get()) }


}
val authFragmentModule = module {
    viewModel { AuthViewModel(get()) }
}
val logoutModule = module {
    viewModel { LogoutViewModel(get()) }
}
val profileModule = module {
    viewModel { ProfileViewModel(get()) }
}
val initialProfileModule = module {
    viewModel { InitialProfileSetupViewModel(get()) }
}
val enterCodeFragmentModule = module {
    viewModel { EnterCodeViewModel(get(), get()) }
}
val masterClientFragmentModule = module {
    factory<MasterClientInteractor<AppState>> { MasterClientInteractorImpl(get()) }
    viewModel { MasterClientViewModel(get()) }
}

val signUpModule = module {
    viewModel { SignUpViewModel() }
}

val signUpSecondModule = module {
    viewModel { SignUpSecondViewModel(get()) }
}