package com.nick_sib.beauty_radar.provider.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nick_sib.beauty_radar.data.state.AppState

class ProfileProviderImpl(private val firebaseDatabase: FirebaseDatabase) : IProfileProvider {

    private val livedataProfileProvider: MutableLiveData<AppState> = MutableLiveData()

    override fun checkUIDUser(idToken: String) {
        Log.d("CreateNewUIDUser", idToken)

        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("users").child("uid")

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null) {
                    var list = snapshot.value as MutableList<String>
                    if (!list.contains(idToken)) {
                        list.add(idToken)
                        myRef.setValue(list)
                    }
                } else {
                    var list = mutableListOf<String>()
                    list.add(idToken)
                    myRef.setValue(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun getLiveDataProfileProvider(): LiveData<AppState> {
        return livedataProfileProvider
    }

}