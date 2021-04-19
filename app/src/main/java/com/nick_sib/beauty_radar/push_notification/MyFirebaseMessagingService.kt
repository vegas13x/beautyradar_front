package com.nick_sib.beauty_radar.push_notification

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nick_sib.beauty_radar.ui.activity.MainActivity


class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        getFireBaseMessage(p0.notification?.title.toString(), p0.notification?.body.toString())
    }

    private fun getFireBaseMessage(contentTitle: String, contentText: String) {

//        val icon = BitmapFactory.decodeResource(
//            application.resources,
//            R.drawable.ic_menu_add
//        )

        val builder = NotificationCompat.Builder(this,"myChannel")
            .setSmallIcon(R.drawable.ic_menu_add)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setAutoCancel(true)
//            .setLargeIcon(icon)

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("message", contentText)

        builder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())

    }

}