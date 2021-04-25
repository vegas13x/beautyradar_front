package com.nick_sib.beauty_radar.model.provider.cloud_storage

interface ICloudStorageProvider {

    fun uploadAvatar(uid:String, imgUrl: String)
    fun downloadAvatar(uid: String)
}