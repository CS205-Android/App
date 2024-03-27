package mobile.cs205

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import mobile.cs205.navgraph.MainNavGraph
import mobile.cs205.services.NotificationService.Companion.CHANNEL_ID
import mobile.cs205.services.NotificationService.Companion.CHANNEL_NAME
import mobile.cs205.ui.theme.CS205Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        createNotificationChannel()

        setContent {
            CS205Theme {
                val navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
        super.onCreate(savedInstanceState)

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used for the app's notifications"
            // Register the channel with the system.
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

