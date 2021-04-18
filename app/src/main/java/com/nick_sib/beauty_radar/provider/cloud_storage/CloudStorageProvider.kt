package com.nick_sib.beauty_radar.provider.cloud_storage

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException


class CloudStorageProvider : ICloudStorageProvider {

    private lateinit var storageReference: StorageReference

    override fun uploadAvatar(uid: String, imgUrl: String) {
        var ref = storageReference.storage.getReference("USER_AVATAR").child(uid)
    }

    override fun downloadAvatar(riverRef: StorageReference,imageView: ImageView) {
        val localFile = File.createTempFile("images", "jpg")
        riverRef.getFile(localFile)
            .addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                imageView.setImageBitmap(bitmap)
            }
            .addOnFailureListener { }
    }

}