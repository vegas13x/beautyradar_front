package com.nick_sib.beauty_radar.model.provider.phone_clients

class RemoteDBProviderPhone : IRemoteDBProviderPhone {
    override fun getPhoneClients(): List<PhoneClient> {
        return listOf(
            PhoneClient("Vera","+9998568823","null"),
            PhoneClient("Roma","+9998863823","null"),
            PhoneClient("Bob","+9998568811","null"),
            PhoneClient("Misha","+9998568823","null"),
            PhoneClient("Katya","+9998565528","null"),
            PhoneClient("Anivia","+9998569613","null")
        )
    }
}