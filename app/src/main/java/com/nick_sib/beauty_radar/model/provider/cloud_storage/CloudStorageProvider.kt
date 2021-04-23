package com.nick_sib.beauty_radar.model.provider.cloud_storage

import com.google.firebase.storage.StorageReference

class CloudStorageProvider : ICloudStorageProvider {

    private lateinit var storageReference: StorageReference

    override fun uploadAvatar(uid: String, imgUrl: String) {
        storageReference.storage.getReference("USER_AVATAR").child(uid)
    }

    override fun downloadAvatar(uid: String) {
        storageReference.storage.getReference("USER_AVATAR").child(uid)
    }


}