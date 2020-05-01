package com.example.mobiledevelopersummary.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.example.mobiledevelopersummary.R
import com.example.mobiledevelopersummary.content_detail.ContentDetail


private const val NOTIFICATION_ID = 0
const val CONTENT_ID = "content_id"

fun sendNotification(
    messageBody: String?,
    messageTitle: String?,
    applicationContext: Context,
    contentId: String?
) {

    val notificationManager = ContextCompat.getSystemService(
        applicationContext,
        NotificationManager::class.java
    ) as NotificationManager

    val channelId = applicationContext.getString(R.string.mds_notification_channel_id)
    val channelName = applicationContext.getString(R.string.mds_notification_channel_name)
    val channelDescription = applicationContext.getString(R.string.mds_description_channel)

    val contentIntent = Intent(applicationContext, ContentDetail::class.java).apply {
        putExtra(CONTENT_ID, contentId)
    }

    val contentPendingIntent = TaskStackBuilder.create(applicationContext).run {
        addNextIntentWithParentStack(contentIntent)
        getPendingIntent(NOTIFICATION_ID, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    val notificationImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.img_notification
    )

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(notificationImage)
        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(
        applicationContext,
        channelId
    )
        .setSmallIcon(R.drawable.img_notification)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setStyle(bigPicStyle)
        .setLargeIcon(notificationImage)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
                description = channelDescription
                setShowBadge(false)
                enableLights(true)
                lightColor = Color.YELLOW
                enableVibration(true)
            }
        notificationManager.createNotificationChannel(notificationChannel)
    }

    notificationManager.notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.setChannel(applicationContext: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val channelId = applicationContext.getString(R.string.mds_notification_channel_id)
        val channelName = applicationContext.getString(R.string.mds_notification_channel_name)
        val channelDescription = applicationContext.getString(R.string.mds_description_channel)

        val notificationChannel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH).apply {
                description = channelDescription
                setShowBadge(false)
                enableLights(true)
                lightColor = Color.YELLOW
                enableVibration(true)
            }
        createNotificationChannel(notificationChannel)
    }
}

