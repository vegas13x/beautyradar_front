package com.nick_sib.beauty_radar.di


import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.provider.auth_.AuthProviderImpl
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.ui.initial_profile_setup.InitialProfileSetupViewModel
import com.nick_sib.beauty_radar.ui.authScreen.AuthViewModel
import com.nick_sib.beauty_radar.ui.enter_code.EnterCodeViewModel
import com.nick_sib.beauty_radar.ui.logout.LogoutViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Alex Volkov(Volkos)
 *
 * Класс модулей для Koin
 */
val appModule = module {
    single { FirebaseAuth.getInstance() }
    single<IAuthProvider> { AuthProviderImpl(get()) }
}
val authFragmentModule = module {
    viewModel { AuthViewModel(get()) }
}
val logoutModule = module {
    viewModel { LogoutViewModel(get()) }
}
val initialProfileModule = module {
    viewModel { InitialProfileSetupViewModel(get()) }
}

val enterCodeFragmentModule = module {
    viewModel { EnterCodeViewModel(get()) }
}