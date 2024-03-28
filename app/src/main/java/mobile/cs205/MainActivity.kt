package mobile.cs205

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import mobile.cs205.navgraph.MainNavGraph
import mobile.cs205.services.NotificationServiceImpl.Companion.CHANNEL_ID
import mobile.cs205.services.NotificationServiceImpl.Companion.CHANNEL_NAME
import mobile.cs205.ui.theme.CS205Theme

/**
 * The entry point of our whole application
 * Since the project is using Jetpack Compose, there will only be one ComponentActivity
 * Every other screens are routed thanks to the nav controller and nav graph provided by navigation in compose
 *
 * */
class MainActivity : ComponentActivity() {

    /**
     * Starts the activity by drawing the screen with the main navigation graph
     * Also configures the notification channel to display notifications when quiz begins
     *
     * @return A composable which is mainly consisting of the MainNavGraph which will decide which screen to display
     * */
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

    /**
     * A helper function to create a notification channel to enable notifications in our application
     * */
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Used for the app's notifications"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

