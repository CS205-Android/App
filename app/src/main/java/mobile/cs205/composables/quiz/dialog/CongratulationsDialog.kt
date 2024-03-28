package mobile.cs205.composables.quiz.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun CongratulationsDialog(
    showDialog: (Boolean) -> Unit,
    navController: NavController,
    correctAnswerNumber: Int
) {
    AlertDialog(
        onDismissRequest = { showDialog(false) },
        title = { Text(text = "Congratulations!") },
        text = {
            Text(
                text = "You have answered $correctAnswerNumber correctly",
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            Button(onClick = { navController.navigateUp() }) {
                Text("Exit")
            }
        },
    )
}