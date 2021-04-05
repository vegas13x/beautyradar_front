package com.nick_sib.beauty_radar.provider

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.entites.UserMaster


interface IAuthProvider {
    fun getCurrentUser(): LiveData<UserMaster>
    fun singUpEmailAndPasswordUser(email: String, password: String)
}