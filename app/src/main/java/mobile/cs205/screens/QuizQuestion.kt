package mobile.cs205.screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mobile.cs205.composables.common.data.Question
import mobile.cs205.composables.common.data.topics
import mobile.cs205.ui.theme.md_theme_dark_errorContainer

@Composable
fun QuizQuestionScreen(navController: NavController) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var currentQuestionNumber by remember { mutableIntStateOf(1) }
    var correctAnswerNumber by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val quizId : Int? = arguments?.getString("quizId")?.toInt()
    var size by remember { mutableIntStateOf(0) }
    LaunchedEffect(quizId) {
        if (quizId != null) {
            size = topics.getOrNull(quizId)?.questions?.size ?: 0
        }
    }

    LaunchedEffect(quizId, currentQuestionNumber) {
        if (quizId != null && currentQuestionNumber > size) {
                showDialog = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Congratulations!") },
                text = {
                    Text(
                        text = "You have answered $correctAnswerNumber correctly",
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = {
                    Button(onClick = {navController.navigateUp()}) {
                        Text("Exit")
                    }
                },
            )
        }
        Column {
            //TOP PORTION
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.12f)
                    .border( // Add border
                        BorderStroke(
                            2.dp,
                            Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Timer(time = "20")
                    ProgressBar(percent = 10f)
                }
            }
            //MIDDLE PORTION
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
//                    .border( // Add border
//                        BorderStroke(
//                            2.dp,
//                            Color.Black
//                        ), // Border stroke with color and width
//                        shape = RoundedCornerShape(8.dp) // Optional: apply rounded corners
//                    ),

                ) {
                val question = if (quizId != null && quizId in topics.indices && currentQuestionIndex in topics[quizId].questions.indices) {
                    topics[quizId].questions[currentQuestionIndex]
                } else {
                    null
                }
                question?.let {
                    OptionList(
                        question = it,
                        onIncrementIndex = {
                            currentQuestionIndex++
                            currentQuestionNumber++
                        },
                        onIncrementCorrectNumber = {
                            correctAnswerNumber++
                        }
                    )
                }
            }
            //END PORTION
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text =
                    if (currentQuestionNumber <= size) "QUESTION $currentQuestionNumber/$size" else "")

                }
            }

        }
    }
}

@Composable
fun OptionsCard (
    option: String,
    correctAns:String,
    onIncrementIndex: () -> Unit,
    onIncrementCorrectNumber: () -> Unit,
) {
    var correct by remember { mutableStateOf(false) }
    var wrong by remember { mutableStateOf(false) }


    val colors = ButtonDefaults.buttonColors(
        containerColor = if (correct) {
            Color.Green
        } else if (wrong) {
            md_theme_dark_errorContainer
        } else {
            Color.DarkGray
        }
    )

    Button(
        modifier = Modifier
            .padding(all = 8.dp)
            .width(260.dp)
            .height(45.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            if (option == correctAns) {
                correct = true

                GlobalScope.launch {
                    delay(800) // Adjust the delay duration as needed
                    correct = false
                    onIncrementIndex()
                    onIncrementCorrectNumber()
                }

            } else {
                wrong= true

                GlobalScope.launch {
                    delay(800) // Adjust the delay duration as needed
                    wrong = false
                    onIncrementIndex()
                }

            }
        },
        colors = colors
    ) {
        Text(option)
    }
}

@Composable
fun OptionList(
    question: Question,
    onIncrementIndex: () -> Unit,
    onIncrementCorrectNumber: () -> Unit) {
    val qnAnswer = question.correctAnswer

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.question,
            style = TextStyle(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .padding(top = 10.dp),
        )
        Column {
            for (i in question.answerOptions) {
                OptionsCard(option = i, correctAns = qnAnswer, onIncrementIndex, onIncrementCorrectNumber)
            }
        }
    }
}

@Composable
fun Timer(
    time : String
) {
    Text("$time s")
}

@Composable
fun ProgressBar(
    percent: Float
) {
    val progress = remember { mutableFloatStateOf(percent.coerceIn(0f, 100f)) }

    LinearProgressIndicator(
        modifier = Modifier.padding(top = 50.dp),
        progress = { progress.floatValue / 100f },
    )
}
