package mobile.cs205

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressControlButtons() {
    var progress by remember { mutableFloatStateOf(0f) }
    var progressJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Fill the max size of the parent
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center the contents vertically
        ) {
            // Start Progress Button
            ProgressButton(
                text = "Start Progress",
                enabled = progressJob == null,
                onClick = {
                    progressJob = coroutineScope.launch {
                        while (progress < 1f) {
                            delay(100)
                            progress += 0.01f
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Stop Progress Button
            ProgressButton(
                text = "Stop Progress",
                enabled = progressJob != null,
                onClick = {
                    progressJob?.cancel()
                    progressJob = null
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Reset Progress Button
            ProgressButton(
                text = "Reset Progress",
                enabled = true,
                onClick = {
                    progressJob?.cancel()
                    progress = 0f
                    progressJob = null
                }
            )
        }
    }
}

@Composable
fun ProgressButton(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { if (enabled) onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color(0xFF4CAF50) else Color(0xFF9E9E9E)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(text, style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressControlButtonsPreview() {
    ProgressControlButtons()
}
