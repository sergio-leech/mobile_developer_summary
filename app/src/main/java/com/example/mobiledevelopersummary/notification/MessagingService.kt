package com.example.mobiledevelopersummary.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Timber.d("Token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("message data : ${remoteMessage.data[CONTENT_ID]}")
        remoteMessage.notification?.let { message ->
            sendNotification(
                message.body,
                message.title,
                applicationContext,
                remoteMessage.data[CONTENT_ID]
            )
        }
    }
}