package com.nick_sib.beauty_radar.provider.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.ui.utils.USER_IS_ENABLE_IN_DB

class RemoteDBProvider(private val firebaseDatabase: FirebaseDatabase) : IRemoteDBProvider {

    private val livedataProfileProvider: MutableLiveData<AppState> = MutableLiveData()
    private lateinit var databaseUsers: DatabaseReference
    private lateinit var databaseOther: DatabaseReference

    override fun createUIDUser(user: UserProfile) {
        Log.d("createUIDUser", "createUIDUser: ${user.uid}")

        databaseUsers = firebaseDatabase.getReference("users")

        databaseUsers.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null) {
                    var list = snapshot.value as MutableList<String>
                    if (!list.contains(user.name)) {
                        list.add(user.name)
                        databaseUsers.setValue(list)
                    }
                } else {
                    var list = mutableListOf<String>()
                    list.add(user.name)
                    databaseUsers.setValue(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun getUser(uid: String) {
        databaseUsers = firebaseDatabase.getReference("users")

        var list = mutableListOf<UserProfile>()
        databaseUsers.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "onCancelled: $error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    if (ds.key == uid){
                        livedataProfileProvider.value = AppState.Success(USER_IS_ENABLE_IN_DB)
                    }else{
                        livedataProfileProvider.value = AppState.Success(USER_IS_DISABLE_IN_DB)
                    }
                    Log.d("TAG", "onDataChange: ${ds.key}")
                }
                Log.d("TAG", "onDataChange: $list")

            }

        })
    }


    override fun getListUsers(list: ArrayList<UserProfile>) {
    }

    override fun getCalendarDate(uid: String) {
        TODO("Not yet implemented")
    }

    override fun getLiveDataProfileProvider(): LiveData<AppState> {
        return livedataProfileProvider
    }


}