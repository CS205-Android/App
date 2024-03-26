package mobile.cs205.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mobile.cs205.composables.common.data.Topic
import mobile.cs205.composables.common.data.topicNames
import mobile.cs205.composables.common.data.topics
import mobile.cs205.services.NotificationService

@Composable
fun QuizListingScreen(rootNavController: NavHostController) {

    //State to keep track of which person is opened and when its opened. Will change with actual quiz
    val openAlertDialog = remember { mutableStateOf(false) }
    val (selectedItem, setSelectedItem) = remember { mutableStateOf("") }

    //Set Context for notification
    val notificationService = NotificationService(LocalContext.current)
    //Lazy Column is used to set a ListView of all topics
    LazyColumn {
        //items loops through all topics stated in Topic.kt
        items(topics) { topic ->
            //Create a Text button for each topic
            TextButton(
                //Define the action of the button when the button is clicked
                onClick = {
                    //Sets state to name of selected item and AlertDialog to true
                    setSelectedItem(topic.topicName)
                    //Sets the openAlertDialog.value to true which will run the when block below
                    openAlertDialog.value = true
                },
                //Define the shape of the TextButton to rectangular instead of default rounded
                shape = RectangleShape,
                //Define the TextButton to fill the max width of Lazy Column - 8.dp
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                //Define the Text to be stored in the TextButton
                Text(
                    //Assign the text to be an annotated string of 2 parts
                    text = buildAnnotatedString {
                        //Part 1: Topic Name to be bold and larger than standard font
                        withStyle(
                            style = SpanStyle(
                                //Sets the topic name to bold
                                fontWeight = FontWeight.Bold,
                                //Sets the font size to 16.sp
                                fontSize = 16.sp
                            )
                        ) {
                            //Set the style to the topic name
                            append(topic.topicName)
                        }
                        //Appends a 1-line space between topic name and description
                        append("\n\n")
                        //Part 2: Description of standard font
                        append(topic.description)
                    },
                    //Aligns the text to the left
                    textAlign = TextAlign.Start,
                    //Sets the textbox to fill the max width of the button
                    modifier = Modifier.fillMaxWidth()
                )
            }
            //Include a Horizontal divider line between each button
            HorizontalDivider()
        }
    }
    //When dialog state = true, set corresponding values based on AlertDialogExample Composable
    when {
        //when openAlertDialog = true
        openAlertDialog.value -> {
            //First, find the topic from the topics list
            val topic = topics[topicNames.indexOf(selectedItem)]
            //Create a new CustomAlertDialog with the following parameters
            CustomAlertDialog(
                //If dismiss request is called, openAlertDialog will be set to false
                onDismissRequest = { openAlertDialog.value = false },
                //If confirm request is called, openAlertDialog will be set to false
                onConfirmation = {
                    //Sets the openAlertDialog to false to close the AlertDialog
                    openAlertDialog.value = false
                    //Sends a notification to the user
                    notificationService.showNotification(
                        contentTitle = "Quiz has started!",
                        contentText = "You have started $selectedItem quiz!"
                    )
                    rootNavController.navigate("quiz_question/${topicNames.indexOf(selectedItem)}") {
                        popUpTo("quiz_listing") { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                    //TODO: Change method here to route to Quiz Screen

                },
                //Sets the icon used in the Dialog to the Quiz Icon
                icon = Icons.Default.Quiz,
                //Sets the topic used in the Dialog to the selected topic
                topic = topic
            )

        }
    }
}

@Composable
//Parameters for the CustomAlertDialog
fun CustomAlertDialog( //Parameters for the CustomAlertDialog
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    icon: ImageVector,
    topic : Topic
) {
    //Creates a AlertDialog object using the parameters
    AlertDialog(
        //Sets the icon of the AlertDialog
        icon = {
            Icon(icon, contentDescription = "Quiz Icon")
        },
        //Sets the title of the AlertDialog
        title = {
            Text(text = topic.topicName)
        },
        //Sets the text of the AlertDialog
        text = {
            Text(text = "${topic.description}\n\n" +
                "No. of Questions: ${topic.questions.size}\n" +
                "Duration of each question: 10 seconds")
        },
        //Sets the onDismissRequest of the AlertDialog
        onDismissRequest = { onDismissRequest() },
        //Sets the confirmButton settings of the AlertDialog
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() }
            ) {
                Text("Start Quiz!")
            }
        },
        //Sets the dismissButton settings of the AlertDialog
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest() }
            ) {
                Text("Cancel")
            }
        }
    )
}

//@Preview
//@Composable
//fun QuizListingScreenPreview() {
//    CS205Theme {
//        QuizListingScreen()
//    }
//}