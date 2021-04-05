package com.nick_sib.beauty_radar.di


import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.provider.AuthProviderImpl
import com.nick_sib.beauty_radar.provider.IAuthProvider
import com.nick_sib.beauty_radar.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single<IAuthProvider> { AuthProviderImpl(get()) }
}
val authModule = module {
    viewModel { LoginViewModel(get()) }
}