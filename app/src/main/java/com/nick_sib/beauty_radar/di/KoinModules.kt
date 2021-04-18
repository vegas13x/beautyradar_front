package com.nick_sib.beauty_radar.di


import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.provider.auth_.AuthProviderProvider
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.provider.profile.RemoteDBProvider
import com.nick_sib.beauty_radar.ui.initial_profile_setup.InitialProfileSetupViewModel
import com.nick_sib.beauty_radar.ui.authScreen.AuthViewModel
import com.nick_sib.beauty_radar.ui.enter_code.EnterCodeViewModel
import com.nick_sib.beauty_radar.ui.logout.LogoutViewModel
import com.nick_sib.beauty_radar.ui.profileScreen.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Alex Volkov(Volkos)
 *
 * Created 08.04.2021
 */
val appModule = module {
    single { FirebaseAuth.getInstance() }
    single<IAuthProvider> { AuthProviderProvider(get()) }
    single<IRemoteDBProvider> { RemoteDBProvider() }
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
    viewModel { EnterCodeViewModel(get(),get()) }
}