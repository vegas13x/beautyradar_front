package com.nick_sib.beauty_radar.provider.cloud_storage

import android.widget.ImageView
import com.google.firebase.storage.StorageReference

interface ICloudStorageProvider {

    fun uploadAvatar(uid:String, imgUrl: String)
    fun downloadAvatar(ref: StorageReference,imageView: ImageView)
}