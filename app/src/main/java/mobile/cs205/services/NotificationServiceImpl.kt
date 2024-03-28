package mobile.cs205.services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import mobile.cs205.MainActivity
import mobile.cs205.R

/**
 * Actual implementation of the NotificationService interface
 * @param context : Requires the global information about the app environment to allow the notifications to be sent to the notification centre
 * */
class NotificationServiceImpl(
    private val context: Context,
) : NotificationService {
    companion object {
        const val CHANNEL_ID = "appChannelId"
        const val CHANNEL_NAME = "appChannelName"
    }

    // This will get the notification service from the context of the application
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(
        contentTitle: String,
        contentText: String
    ) {
        // To allow the user to enter the application if they click on the notification
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Builds the notification with our app icon and set its content and text
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_only_invis)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setContentIntent(activityPendingIntent)
            .build()

        // Shows the notification based on the notification object built
        notificationManager.notify(1, notification)
    }
}