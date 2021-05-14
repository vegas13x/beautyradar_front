package com.nick_sib.beauty_radar.view_model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.LiveData
import com.google.firebase.storage.FirebaseStorage
import com.nick_sib.beauty_radar.SingletonImgUrl
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.TRANSITION_TO_CALENDAR
import com.nick_sib.beauty_radar.view.utils.USE_DEFAULT_IMG
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import java.io.File
import java.io.IOException

class MasterAndClientInnerViewModel: BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun transitionToCalendar() {
        liveDataViewmodel.value = AppState.Success(TRANSITION_TO_CALENDAR)
    }

    fun takePictureFromStorage() {
        if (SingletonImgUrl.getImgUrl() != null) {
            val storage = FirebaseStorage.getInstance()
            val riversRef = storage.getReferenceFromUrl(SingletonImgUrl.getImgUrl().toString())
            val localFile = File.createTempFile("images", "jpg")
            riversRef.getFile(localFile)
                .addOnSuccessListener {
                    var bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    if (bitmap != null) {
                        bitmap = getBitmap(localFile, bitmap)
                        liveDataViewmodel.value = AppState.Success(bitmap)
                    }
                }.addOnFailureListener {}}
        else {
            liveDataViewmodel.value = AppState.Success(USE_DEFAULT_IMG)
        }
    }

    @Throws(IOException::class)
    private fun getBitmap(localFile: File, bitmap1: Bitmap): Bitmap? {
        var bitmap = bitmap1
        val exif = ExifInterface(localFile.absoluteFile)
        val rotation =
            exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val rotationInDegrees: Int = exifToDegrees(rotation)
        val matrix = Matrix()
        if (rotation.toFloat() != 0f) {
            matrix.preRotate(rotationInDegrees.toFloat())
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        return bitmap
    }

    private fun exifToDegrees(exifOrientation: Int): Int {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270
        }
        return 0
    }

    override fun errorReturned(t: Throwable) {}
}