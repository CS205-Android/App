package mobile.cs205.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import mobile.cs205.MainActivity
import mobile.cs205.R

class NotificationService (
    private val context: Context,
) {
    companion object {
        const val CHANNEL_ID = "appChannelId"
        const val CHANNEL_NAME = "appChannelName"
    }

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification (
        contentTitle: String,
        contentText: String
    ) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            //Sets the context of the PendingIntent
            context,
            //Sets the requestCode of the PendingIntent
            1,
            //Sets the context of the PendingIntent
            activityIntent,
            //Defines what should be done with the pending intent
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_only_invis)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }
}