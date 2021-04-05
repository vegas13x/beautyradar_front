package com.nick_sib.beauty_radar.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.nick_sib.beauty_radar.data.entites.UserMaster


class AuthProviderImpl(private val authUser: FirebaseAuth) : IAuthProvider {


    private val currentUser
        get() = authUser.currentUser

    override fun getCurrentUser(): LiveData<UserMaster> =
        MutableLiveData<UserMaster>().apply {
            value = currentUser?.let {
                UserMaster(
                    it.displayName ?: "",
                    it.email ?: "",
                    it.phoneNumber ?: ""
                )

            }
        }

    override fun singUpEmailAndPasswordUser(email: String, password: String) {
        authUser.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.w("ADD-FIREBASE", "Success add user in firebase")
                } else {
                    Log.w("ADD-FIREBASE", "ERROR add user in firebase")
                }
            }
    }


}