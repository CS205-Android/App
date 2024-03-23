package mobile.cs205

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import mobile.cs205.ui.theme.CS205Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContent {
            CS205Theme {
                val navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
        super.onCreate(savedInstanceState)

    }
}

