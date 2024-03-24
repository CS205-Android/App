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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mobile.cs205.ui.theme.CS205Theme

data class Topic(val topicName : String, val description : String)

@Composable
fun QuizListingScreen() {
    val topics = listOf(
        Topic("Singapore History #1",
            "This quiz covers the British colonization portion of Singapore’s History!"),
        Topic("Singapore History #2",
            "This quiz covers the early period of Singapore’s History!"),
        Topic("Singapore History #3",
            "This quiz covers the early modern history of Singapore’s History!"),
        Topic("Singapore History #4",
            "This quiz covers the World War II period  of Singapore’s History!"),
        Topic("Singapore History #5",
            "This quiz covers the independence of Singapore Agreement 1965!"),
        Topic("Singapore Laws",
            "Did you know about some laws that are unique to Singapore?"),
        Topic("Singlish",
            "Lar lor leh meh, do you know all the Singlish slangs? Find out in this quiz!"),
        Topic("Singapore Politics",
            "How much did you know about politics in Singapore? Guess we are about to find out now!")
    )

    //State to keep track of which person is opened and when its opened. Will change with actual quiz
    val openAlertDialog = remember { mutableStateOf(false) }
    val (selectedItem, setSelectedItem) = remember { mutableStateOf("") }

    LazyColumn {
        items(topics) { topic ->
            TextButton(
                onClick = {
                    //Sets state to name of selected item and AlertDialog to true
                    setSelectedItem(topic.topicName)
                    openAlertDialog.value = true
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        ) {
                            append(topic.topicName)
                        }
                        append("\n\n")
                        append(topic.description)
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
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