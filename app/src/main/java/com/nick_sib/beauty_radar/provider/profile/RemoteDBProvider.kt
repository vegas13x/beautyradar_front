package com.nick_sib.beauty_radar.provider.profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.utils.TAG_DEBAG
import com.nick_sib.beauty_radar.ui.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.ui.utils.USER_IS_ENABLE_IN_DB

class RemoteDBProvider : IRemoteDBProvider {

    private val livedataProfileProvider: MutableLiveData<AppState> = MutableLiveData()

    private lateinit var databaseUsers: DatabaseReference
    private lateinit var databaseСalendar: DatabaseReference
    private lateinit var databaseProfile: DatabaseReference

    override fun createUIDUser(user: UserProfile) {
        databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(user.uid)
        databaseUsers.setValue(user)
    }

    override fun getUser(uid: String) {
        databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(uid)
        databaseUsers.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value == null){
                    livedataProfileProvider.value = AppState.Success(USER_IS_DISABLE_IN_DB)
                }else{
                    var hashMap = snapshot.value as HashMap<*, *>
                    livedataProfileProvider.value = AppState.Success(USER_IS_ENABLE_IN_DB)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }


    override fun getListUsers(list: ArrayList<UserProfile>) {

        databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child("uid")
        databaseUsers.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var hashMap = snapshot.value as HashMap<*, *>
                if (hashMap.entries != null){
                    Log.d(TAG_DEBAG, "onDataChange: $databaseUsers")
                    livedataProfileProvider.value = AppState.Success(USER_IS_ENABLE_IN_DB)
                }else{
                    livedataProfileProvider.value = AppState.Success(USER_IS_DISABLE_IN_DB)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    override fun getCalendarDate(uid: String) {
        databaseUsers = FirebaseDatabase.getInstance().getReference("CALENDAR_PROFILE").child("uid")
        databaseUsers.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var hashMap = snapshot.value as HashMap<*, *>
                if (hashMap.entries != null){
                    livedataProfileProvider.value = AppState.Success(USER_IS_ENABLE_IN_DB)
                }else{
                    livedataProfileProvider.value = AppState.Success(USER_IS_DISABLE_IN_DB)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    override fun getLiveDataProfileProvider(): LiveData<AppState> {
        return livedataProfileProvider
    }


}