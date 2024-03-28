package mobile.cs205.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import mobile.cs205.data.quiz.topicNames
import mobile.cs205.data.quiz.topics
import mobile.cs205.composables.quiz.dialog.StartQuizDialog

/**
 * The QuizListing composable represents the Quiz Listing screen of the application
 * @return An LazyColumn and AlertDialog composable
 * @param rootNavController : A NavHostController from the root to navigate to the QuizQuestion Screen when the user starts a quiz
 * */
@Composable
fun QuizListingScreen(rootNavController: NavHostController) {
    // State to keep track of the states of the dialog such as open/close and the selected quiz to render the dialog contents
    val openAlertDialog = remember { mutableStateOf(false) }
    val (selectedItem, setSelectedItem) = remember { mutableStateOf("") }

    // Lazy Column is used to set a ListView of all topics
    LazyColumn {
        items(topics) { (topicName, description) ->
            // Surface is a wrapper for each ListItem to make them clickable
            Surface(
                onClick = {
                    setSelectedItem(topicName)
                    openAlertDialog.value = true
                },
            ) {
                ListItem(
                    headlineContent = { Text(text = topicName) },
                    supportingContent = { Text(text = description) },
                    )
            }
            HorizontalDivider()
        }
    }

    /**
     * When the alert dialog is set to true, the dialog will show
     * The contents of the dialog will be rendered based on the selectedItem mutable state
     * */
    when {
        openAlertDialog.value -> {
            val topic = topics[topicNames.indexOf(selectedItem)]
            StartQuizDialog(
                rootNavController = rootNavController,
                openAlertDialog = openAlertDialog,
                selectedItem = selectedItem,
                topic = topic
            )

        }
    }
}

