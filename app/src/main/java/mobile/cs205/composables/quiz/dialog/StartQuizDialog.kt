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
import mobile.cs205.data.quiz.Topic
import mobile.cs205.data.quiz.topicNames
import mobile.cs205.services.NotificationServiceImpl

/**
 * The TopBar composable that is only being used in Scaffold screen
 * The main purpose is to provide a sense of control for the user to understand where they are currently at out of all the screens
 * In order to prevent the TopBar from moving in scrollable screens, we opt in the use of an experimental pinnedScrollBehaviour to keep it sticky
 * @return A Top Bar composable that can be injected into a Scaffold.topBar
 * @param openAlertDialog : A mutable boolean state that controls whether to show or prune the quiz dialog
 * @param rootNavController : A NavHostController
 * */
@Composable
fun StartQuizDialog(
    openAlertDialog: MutableState<Boolean>,
    rootNavController: NavHostController,
    selectedItem: String,
    topic: Topic
) {
    val notificationService = NotificationServiceImpl(LocalContext.current)

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
                    /**
                     * This showNotification function call will create a notification in the notification logs of the user
                     * This will require the POST_NOTIFICATION permissions to be enabled in order for this to work
                     */
                    notificationService.showNotification(
                        contentTitle = "Quiz has started!",
                        contentText = "You have started $selectedItem quiz!"
                    )
                    /**
                     * The navigation is handled as the following to ensure:
                     * 1) Only one instance of the QuizQuestion screen is rendered at a time to prevent multiple QuizQuestion screen to be maintained in the back stack
                     * 2) State of QuizQuestion screen is never restored if the user quit and kill the application since it will no longer be useful
                     * 3) Pop everything from the back stack before adding QuizQuestion to the backstack which increases the predictability of the back button
                     */
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