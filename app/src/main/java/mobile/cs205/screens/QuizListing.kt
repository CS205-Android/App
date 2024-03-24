package mobile.cs205.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mobile.cs205.composables.common.data.topics
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun QuizListingScreen() {

    //State to keep track of which person is opened and when its opened. Will change with actual quiz
    val openAlertDialog = remember { mutableStateOf(false) }
    val (selectedItem, setSelectedItem) = remember { mutableStateOf("") }

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
                modifier = Modifier.fillMaxWidth().padding(8.dp)
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
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    println("Confirmation registered for $selectedItem")
                },
                dialogTitle = "Alert dialog example",
                dialogText = "You selected: $selectedItem",
                icon = Icons.Default.Info
            )

        }
    }
}

@Preview
@Composable
fun QuizListingScreenPreview() {
    CS205Theme {
        QuizListingScreen()
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}