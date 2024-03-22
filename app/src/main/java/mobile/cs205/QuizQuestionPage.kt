package mobile.cs205

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressControlButtons() {
    var timeLeft by remember { mutableIntStateOf(10) } // Starting with 10 seconds
    var timerThread: CombinedTimerThread? by remember { mutableStateOf(null) }

    // Update the UI based on the timer thread
    val onUpdate: (Int) -> Unit = { updatedTime ->
        timeLeft = updatedTime
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Time left: $timeLeft seconds", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (timerThread?.isAlive != true) {
                    val sharedTime = SharedTime(10, onUpdate)
                    timerThread = CombinedTimerThread(sharedTime).apply { start() }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Start Progress", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                timerThread?.interrupt()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
        ) {
            Text("Stop Progress", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                timerThread?.interrupt()
                timeLeft = 10 // Reset the time left to initial state
                // Optionally restart the timer immediately
                // val sharedTime = SharedTime(10, onUpdate)
                // timerThread = CombinedTimerThread(sharedTime).apply { start() }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E9E9E))
        ) {
            Text("Reset Progress", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressControlButtonsPreview() {
    ProgressControlButtons()
}
