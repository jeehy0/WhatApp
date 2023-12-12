package com.example.whatapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Get the message from the intent
        val message = intent.getStringExtra("notificationMessage") ?: "Default notification message"

        // Ensure the notification channel is created (if not created already)
        createNotificationChannel(context)

        // Create a notification
        val notificationBuilder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.whatappicon)
            .setContentTitle("Notification Title")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Create a pending intent to open the app when the notification is clicked
        val contentIntent = PendingIntent.getActivity(
            context, 0,
            Intent(context, resulttab::class.java), PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationBuilder.setContentIntent(contentIntent)

        // Display the notification
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
