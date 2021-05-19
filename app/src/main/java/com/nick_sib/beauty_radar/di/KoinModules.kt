package com.nick_sib.beauty_radar.di

import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.auth.AuthProviderImpl
import com.nick_sib.beauty_radar.model.provider.auth.IAuthProvider
import com.nick_sib.beauty_radar.model.provider.calendar.IRemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.calendar.RemoteDBProviderCalendar
import com.nick_sib.beauty_radar.model.provider.provider_db.IProviderRemoteDB
import com.nick_sib.beauty_radar.model.provider.provider_db.ProviderRemoteDBImpl
import com.nick_sib.beauty_radar.model.provider.retrofit.RetrofitImplementation
import com.nick_sib.beauty_radar.model.provider.service.IRemoteDBProviderService
import com.nick_sib.beauty_radar.model.provider.service.RemoteDBProviderService
import com.nick_sib.beauty_radar.model.repository.core.RemoteRepository
import com.nick_sib.beauty_radar.model.repository.impl.RemoteRepositoryImpl
import com.nick_sib.beauty_radar.view_model.*
import com.nick_sib.beauty_radar.view_model.interactor.core.BottomSheetInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.EnterCodeInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor
import com.nick_sib.beauty_radar.view_model.interactor.core.SignInInteractor
import com.nick_sib.beauty_radar.view_model.interactor.impl.BottomSheetInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.EnterCodeInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.MasterClientInteractorImpl
import com.nick_sib.beauty_radar.view_model.interactor.impl.SignInInteractorImpl
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

    //Эмуляция данных с сервера
    single<IRemoteDBProviderCalendar> { RemoteDBProviderCalendar() }
    single<IRemoteDBProviderService> { RemoteDBProviderService() }

    single<IProviderRemoteDB> { ProviderRemoteDBImpl(get()) }
    factory<RemoteRepository<AppState>> { RemoteRepositoryImpl(get(), get(),get()) }

}

val welcomeFragmenModule = module {
    viewModel { WelcomeViewModel() }
}

val authFragmentModule = module {
    viewModel { SignViewModel(get()) }
}

val enterCodeFragmentModule = module {
    factory<EnterCodeInteractor<AppState>> { EnterCodeInteractorImpl(get()) }
    viewModel { EnterCodeViewModel(get(), get()) }
}

val signInModule = module {
    factory<SignInInteractor<AppState>> {SignInInteractorImpl(get())}
    viewModel { SignInViewModel( get()) }
}

val bottomSheetFragmentModule = module {
    factory<BottomSheetInteractor<AppState>> { BottomSheetInteractorImpl(get()) }
    viewModel { BottomSheetViewModel(get()) }
}

val masterClientFragmentModule = module {
    factory<MasterClientInteractor<AppState>> { MasterClientInteractorImpl(get()) }
    viewModel { MasterClientViewModel(get()) }
}

val masterClientInnerFragmentModule = module {
    viewModel { MasterAndClientInnerViewModel(get()) }
}

val settingModule = module {
    viewModel { SettingViewModel() }
}

val calendarModule = module {
    viewModel { CalendarViewModel() }
}

val profileInfoModule = module {
    viewModel { ProfileInfoViewModel() }
}

val profileInfoEditModule = module {
    viewModel { ProfileInfoEditViewModel() }
}

val profileInfoInnerModel = module {
    viewModel { ProfileInfoInnerViewModel() }
}
