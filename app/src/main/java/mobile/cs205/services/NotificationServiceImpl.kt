package mobile.cs205.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import mobile.cs205.MainActivity
import mobile.cs205.R

class NotificationServiceImpl(
    private val context: Context,
) : NotificationService {
    companion object {
        const val CHANNEL_ID = "appChannelId"
        const val CHANNEL_NAME = "appChannelName"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(
        contentTitle: String,
        contentText: String
    ) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
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