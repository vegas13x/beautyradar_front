package com.nick_sib.beauty_radar.provider.profile

import androidx.lifecycle.LiveData
import com.nick_sib.beauty_radar.data.state.AppState

interface IProfileProvider {
    fun getLiveDataProfileProvider(): LiveData<AppState>
    fun checkUIDUser(verify: String)
}