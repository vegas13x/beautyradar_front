package com.nick_sib.beauty_radar.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.profile.IRemoteDBProviderProfile
import com.nick_sib.beauty_radar.model.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.model.provider_new.api.ApiService
import com.nick_sib.beauty_radar.model.provider_new.repository.user.Base
import com.nick_sib.beauty_radar.model.provider_new.retrofit.RetrofitImplementation
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class SignUpSecondViewModel(private val remoteDBProviderProfile: IRemoteDBProviderProfile) :
    BaseViewModel<AppState>() {

    var newUserProfile: ApiService? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataViewmodel
    }

    fun createNewUser(
        uid: String,
        name: String,
        secondName: String,
        master: String?,
        client: String?
    ) {
        var user = UserProfile(
            uid, name, secondName, null, null, master, client,
            null, null, null, null, null, null, null
        )
        remoteDBProviderProfile.createUserInDb(user)

        val retrofit = RetrofitImplementation().createRetrofit()
        newUserProfile = retrofit.create(ApiService::class.java)

        val call: Call<Base> = newUserProfile!!.getUserByUID("2q1MuU88hAgf4zlYc22DxXvRS3R2")
        call.enqueue(object: Callback, retrofit2.Callback<Base> {
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                Log.d("TAG9111", "onSucces: " + response.body() +" "+ response.code() + " "+response.message())
            }

            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("TAG9111", "onFailure: " + t + " " + call)
            }
        })

    }




    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}