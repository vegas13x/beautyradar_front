package com.nick_sib.beauty_radar.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nick_sib.beauty_radar.provider.auth_.AuthProviderImpl
import com.nick_sib.beauty_radar.provider.auth_.IAuthProvider
import com.nick_sib.beauty_radar.provider.profile.IRemoteDBProvider
import com.nick_sib.beauty_radar.provider.profile.RemoteDBProvider
import com.nick_sib.beauty_radar.ui.initial_profile_setup.InitialProfileSetupViewModel
import com.nick_sib.beauty_radar.ui.authScreen.AuthViewModel
import com.nick_sib.beauty_radar.ui.enter_code.EnterCodeViewModel
import com.nick_sib.beauty_radar.ui.logout.LogoutViewModel
import com.nick_sib.beauty_radar.ui.profileScreen.ProfileViewModel
import com.nick_sib.beauty_radar.ui.sign_up.SignUpViewModel
import com.nick_sib.beauty_radar.ui.sign_up2.SignUpViewModel2
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
    single<IRemoteDBProvider> { RemoteDBProvider() }
}
val authFragmentModule = module {
    viewModel { AuthViewModel(get()) }
}
val logoutModule = module {
    viewModel { LogoutViewModel(get(),get()) }
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

val signUpModule = module {
    viewModel { SignUpViewModel(get()) }
}

val signUp2Module  = module {
    viewModel { SignUpViewModel2(get()) }
}