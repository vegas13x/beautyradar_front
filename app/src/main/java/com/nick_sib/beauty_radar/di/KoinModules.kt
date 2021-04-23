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
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.model.repository.impl.RemoteRepositoryImpl
import com.nick_sib.beauty_radar.model.room.IRoomSource
import com.nick_sib.beauty_radar.model.room.RoomDataBaseImplementation
import com.nick_sib.beauty_radar.view.initial_profile_setup.InitialProfileSetupViewModel
import com.nick_sib.beauty_radar.view.authScreen.AuthViewModel
import com.nick_sib.beauty_radar.view.enter_code.EnterCodeViewModel
import com.nick_sib.beauty_radar.view.logout.LogoutViewModel
import com.nick_sib.beauty_radar.view.masterclient.MasterClientViewModel
import com.nick_sib.beauty_radar.view.profileScreen.ProfileViewModel
import com.nick_sib.beauty_radar.view.sign_up.SignUpViewModel
import com.nick_sib.beauty_radar.view.sign_up_second.SignUpSecondViewModel
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
    single<IRemoteDBProviderProfile> { RemoteDBProviderProfile() }
    single<IRemoteDBProviderCalendar> { RemoteDBProviderCalendar() }
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<IRoomSource> { RoomDataBaseImplementation(get()) }
    factory<RemoteRepository<AppState>> { RemoteRepositoryImpl(get()) }
    factory<MasterClientInteractor<AppState>> { MasterClientInteractorImpl(get()) }

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
    viewModel { MasterClientViewModel(get()) }
}

val signUpModule = module {
    viewModel { SignUpViewModel() }
}

val signUpSecondModule = module {
    viewModel { SignUpSecondViewModel(get()) }
}