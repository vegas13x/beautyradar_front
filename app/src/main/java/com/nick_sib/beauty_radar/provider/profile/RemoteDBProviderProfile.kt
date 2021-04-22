package com.nick_sib.beauty_radar.provider.profile

import android.util.Log
import com.google.firebase.database.*
import com.nick_sib.beauty_radar.data.error.ToastError
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.ui.utils.USER_IS_ENABLE_IN_DB
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteDBProviderProfile : IRemoteDBProviderProfile {

    private lateinit var databaseUsers: DatabaseReference

    override fun createUserInDb(user: UserProfile) {
        databaseUsers =
            user.uid?.let {
                FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(it)
            }!!
        databaseUsers.setValue(user)
    }

    override suspend fun checkUserInDdByUID(uid: String): AppState {
        return suspendCoroutine { res ->
            databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(uid)
            databaseUsers.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == null) {
                        res.resume(AppState.Success(USER_IS_DISABLE_IN_DB))
                    } else {
                        res.resume(AppState.Success(USER_IS_ENABLE_IN_DB))
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    res.resume(AppState.Error(ToastError(error.message)))
                }
            })
        }
    }

    override suspend fun getUserFromDbByUID(uid: String): AppState {
        return suspendCoroutine { res ->
            databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(uid)
            databaseUsers.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == null) {
                        res.resume(AppState.Success(USER_IS_DISABLE_IN_DB))
                    } else {
                        var hashMap = snapshot.value as HashMap<String, String>
                        var userProfile = UserProfile(
                            hashMap["uid"], hashMap["name"], hashMap["secondName"], null,
                            null, hashMap["master"].toBoolean(), hashMap["client"].toBoolean(), null, null, null,
                            null, null, null, null
                        )
                        Log.d("TAG", "onDataChange:$userProfile ")
                        res.resume(AppState.Success(userProfile))
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    res.resume(AppState.Error(ToastError(error.message)))
                }
            })
        }
    }

    override suspend fun getUsersFromDb(): AppState {
        return suspendCoroutine { res ->
            var list = mutableListOf<UserProfile>()
            databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE")
            databaseUsers.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        var users = ds.getValue(UserProfile::class.java)
                        users?.let { data -> list.add(data) }
                    }
                    res.resume(AppState.Success(list))
                }
                override fun onCancelled(error: DatabaseError) {
                    res.resume(AppState.Error(ToastError(error.message)))
                }
            })
        }
    }

}