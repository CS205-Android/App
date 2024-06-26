package mobile.cs205.composables.quiz.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * The Timer composable that is only being used in QuizQuestion screen
 * Renders the time left for the question using the progress bar and timer as text
 * @param timeLeft: Indicates the amount of time remaining in seconds
 * @param progress : Indicates in % on how much the progress has reach based on the time remaining
 * @return A Box composable
 * */
@Composable
fun Timer(timeLeft: Int, progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("$timeLeft s")
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.padding(top = 50.dp),
            )
        }
    }
}