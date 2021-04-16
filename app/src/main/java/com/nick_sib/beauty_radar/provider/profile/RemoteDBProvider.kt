package com.nick_sib.beauty_radar.provider.profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.provider.profile.entities.CalendareProfile
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import com.nick_sib.beauty_radar.ui.utils.*

class RemoteDBProvider : IRemoteDBProvider {

    private val livedataProfileProvider: MutableLiveData<AppState> = MutableLiveData()

    private lateinit var databaseUsers: DatabaseReference
    private lateinit var databaseСalendar: DatabaseReference
    private lateinit var databaseProfile: DatabaseReference

    override fun checkUserInDdByUID(uid: String) {
        databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(uid)
        databaseUsers.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value == null){
                    livedataProfileProvider.value = AppState.Success(USER_IS_DISABLE_IN_DB)
                }else{
                    livedataProfileProvider.value = AppState.Success(USER_IS_ENABLE_IN_DB)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })


    }

    override fun createUserInDb(user: UserProfile) {
        databaseUsers =
            user.uid?.let { FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(it) }!!
        databaseUsers.setValue(user)
    }

    override fun getUserFromDbByUID(uid: String) {
        var list = mutableListOf<UserProfile>()
        databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE").child(uid)
        databaseUsers.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value == null){
                    livedataProfileProvider.value = AppState.Success(USER_IS_DISABLE_IN_DB)
                }else{
                    for (ds in snapshot.children){
                        var userProfile = ds.getValue(UserProfile::class.java)
                        userProfile?.let { user -> list.add(user) }
                    }
                    livedataProfileProvider.value = AppState.Success(list)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun getUsersFromDb() {
        var list = mutableListOf<UserProfile>()
        databaseUsers = FirebaseDatabase.getInstance().getReference("MASTER_PROFILE")
        databaseUsers.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    var users = ds.getValue(UserProfile::class.java)
                    users?.let { data -> list.add(data) }
                }
                Log.d("getUsersFromDb", "onDataChange: $list")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun createCalendarDateInDb(calendar: CalendareProfile) {
        databaseСalendar =
            calendar.uid?.let { FirebaseDatabase.getInstance().getReference("CALENDAR_PROFILE").child(it) }!!
        databaseСalendar.setValue(calendar)
    }

    override fun getCalendarDateFromDb(uid: String) {
        databaseСalendar = FirebaseDatabase.getInstance().getReference("CALENDAR_PROFILE").child(uid)
        databaseСalendar.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value == null){
                    livedataProfileProvider.value = AppState.Success(CALENDAR_DATE_IS_DISABLE_IN_DB)
                }else{
                    var hashMap = snapshot.value as HashMap<*, *>
                    livedataProfileProvider.value = AppState.Success(hashMap)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun clearLivedata() {
        livedataProfileProvider.value = AppState.Success(CODE_NULL)
    }

    override fun getLiveDataProfileProvider(): LiveData<AppState> {
        return livedataProfileProvider
    }


}