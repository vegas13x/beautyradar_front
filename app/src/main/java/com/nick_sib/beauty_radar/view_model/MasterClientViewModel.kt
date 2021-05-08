package com.nick_sib.beauty_radar.view_model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Log
import android.widget.ImageView
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.LiveData
import com.google.firebase.storage.FirebaseStorage
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.view.utils.TRANSITION_TO_CALENDAR
import com.nick_sib.beauty_radar.view_model.base.BaseViewModel
import com.nick_sib.beauty_radar.view_model.interactor.core.MasterClientInteractor
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MasterClientViewModel(private val interactor: MasterClientInteractor<AppState>) :
    BaseViewModel<AppState>() {

    private val GSTORAGE = "gs://qwink-8816a.appspot.com"
    private val NAMEOFPICTURE = "80b00a35-6391-4c4f-b042-17de861e151f.jpg"

    fun transitionToCalendar() {
        liveDataViewmodel.value = AppState.Success(TRANSITION_TO_CALENDAR)
    }

    fun subscribe(): LiveData<AppState> = liveDataViewmodel

    fun getListClients() {
        liveDataViewmodel.value =
            interactor.getData()
    }

    override fun errorReturned(t: Throwable) {}


    fun takePictureFromStorage() {
        val storage = FirebaseStorage.getInstance()
        val riversRef = storage.getReferenceFromUrl(GSTORAGE).child(NAMEOFPICTURE)
        val localFile = File.createTempFile("images", "jpg")
        riversRef.getFile(localFile)
            .addOnSuccessListener {
                var bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                if (bitmap != null) {
                    bitmap = getBitmap(localFile, bitmap)
//                    imageView.setImageBitmap(bitmap)
                    Log.d("TAG22222", "takePictureFormStorage: $bitmap")
                    liveDataViewmodel.value = AppState.Success(bitmap)
                }
            }.addOnFailureListener {}
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
}


