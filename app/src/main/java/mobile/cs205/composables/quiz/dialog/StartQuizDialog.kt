package mobile.cs205.composables.quiz.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import mobile.cs205.composables.common.data.Topic
import mobile.cs205.composables.common.data.topicNames
import mobile.cs205.services.NotificationService

@Composable
fun StartQuizDialog(
    openAlertDialog: MutableState<Boolean>,
    rootNavController: NavHostController,
    selectedItem: String,
    topic: Topic
) {
    val notificationService = NotificationService(LocalContext.current)

    AlertDialog(
        icon = { Icon(Icons.Default.Quiz, contentDescription = "Quiz Icon") },
        title = { Text(text = topic.topicName) },
        text = {
            Text(
                text = "${topic.description}\n\n" +
                        "No. of Questions: ${topic.questions.size}\n" +
                        "Duration of each question: 10 seconds"
            )
        },
        onDismissRequest = { closeDialog(openAlertDialog) },
        confirmButton = {
            TextButton(
                onClick = {
                    closeDialog(openAlertDialog)
                    notificationService.showNotification(
                        contentTitle = "Quiz has started!",
                        contentText = "You have started $selectedItem quiz!"
                    )
                    rootNavController.navigate("quiz_question/${topicNames.indexOf(selectedItem)}") {
                        popUpTo("quiz_listing") { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            ) {
                Text("Start Quiz!")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { closeDialog(openAlertDialog) }
            ) {
                Text("Cancel")
            }
        }
    )
}

fun closeDialog(openAlertDialog: MutableState<Boolean>) {
    openAlertDialog.value = false
}