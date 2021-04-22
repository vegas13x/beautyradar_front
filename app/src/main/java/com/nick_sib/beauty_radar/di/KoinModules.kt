package com.nick_sib.beauty_radar.di

import androidx.room.Room
import com.nick_sib.beauty_radar.room.HistoryDataBase
import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.provider.auth_.AuthProviderImpl
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.provider.calendar.RemoteDBProviderCalendar
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.provider.profile.RemoteDBProviderProfile
import com.nick_sib.beauty_radar.room.IRoomSource
import com.nick_sib.beauty_radar.room.RoomDataBaseImplementation
import com.nick_sib.beauty_radar.ui.initial_profile_setup.InitialProfileSetupViewModel
import com.nick_sib.beauty_radar.ui.authScreen.AuthViewModel
import com.nick_sib.beauty_radar.ui.enter_code.EnterCodeViewModel
import com.nick_sib.beauty_radar.ui.logout.LogoutViewModel
import com.nick_sib.beauty_radar.ui.profileScreen.ProfileViewModel
import com.nick_sib.beauty_radar.ui.sign_up.SignUpViewModel
import com.nick_sib.beauty_radar.ui.sign_up_second.SignUpSecondViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Alex Volkov(Volkos)
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

val signUpModule = module {
    viewModel { SignUpViewModel() }
}

val signUpSecondModule = module {
    viewModel { SignUpSecondViewModel(get()) }
}