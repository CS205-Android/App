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
import mobile.cs205.composables.common.data.topicNames
import mobile.cs205.composables.common.data.topics
import mobile.cs205.composables.quiz.dialog.StartQuizDialog

@Composable
fun QuizListingScreen(rootNavController: NavHostController) {
    // State to keep track of which person is opened and when its opened. Will change with actual quiz
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

    when {
        //when openAlertDialog = true
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

