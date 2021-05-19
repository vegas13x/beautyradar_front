package com.nick_sib.beauty_radar.model.provider.service

class RemoteDBProviderService: IRemoteDBProviderService {

    override fun getListServiceProfile(): List<ServiceProfile> {
        return listOf(
            ServiceProfile("Ароматерапия"),
            ServiceProfile("Акупрессура"),
            ServiceProfile("Ботокс"),
            ServiceProfile("Вапоризация"),
            ServiceProfile("Горячие ножницы"),
            ServiceProfile("Депиляция"))
    }
}