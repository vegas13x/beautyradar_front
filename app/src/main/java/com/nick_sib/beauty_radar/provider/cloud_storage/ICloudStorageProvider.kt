package com.nick_sib.beauty_radar.provider.cloud_storage

interface ICloudStorageProvider {

    fun uploadAvatar(uid:String, imgUrl: String)
    fun downloadAvatar(uid: String)
}