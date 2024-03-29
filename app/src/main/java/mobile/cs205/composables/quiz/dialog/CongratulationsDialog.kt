package mobile.cs205.composables.quiz.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

/**
 * The CongratulationDialog composable that is only being used in QuizQuestion screen
 * Indicates the number of questions the user got right towards the end of the quiz
 * Allows user to navigate back to Home Screen
 * @param showDialog : a function used to facilitate the dismissal of the dialog
 * @param navController : A NavController to route the user back to the Home Screen
 * @param correctAnswerNumber : Holds the count of correct answers the user has managed to score
 * @return An AlertDialog composable
 * */
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