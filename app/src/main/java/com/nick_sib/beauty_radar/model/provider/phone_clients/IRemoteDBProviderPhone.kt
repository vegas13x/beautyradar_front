package com.nick_sib.beauty_radar.model.provider.phone_clients

interface IRemoteDBProviderPhone {
    fun getPhoneClients(): List<PhoneClient>
}