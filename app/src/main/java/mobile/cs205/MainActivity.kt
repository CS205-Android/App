package mobile.cs205

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import mobile.cs205.TimerViewModel
import mobile.cs205.ui.theme.CS205Theme
import java.lang.reflect.Modifier
import androidx.compose.material3.LinearProgressIndicator



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS205Theme {
                // Instantiate your ViewModel here
                val timerViewModel: TimerViewModel = viewModel()
                TimerScreen(timerViewModel)
            }
        }
    }
}

@Composable
fun TimerScreen(timerViewModel: TimerViewModel) {
    // This timeLeft is the time in seconds
    val timeLeft by timerViewModel.sharedTime.collectAsState()
    val initialTime = 10 // Assuming the countdown starts from 10 seconds
    val progress = if (timeLeft >= 0) (initialTime - timeLeft) / initialTime.toFloat() else 1f

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Time left: $timeLeft seconds",
        )
        LinearProgressIndicator(
            progress = progress,
        )

        // Add more UI elements as needed
    }
}

