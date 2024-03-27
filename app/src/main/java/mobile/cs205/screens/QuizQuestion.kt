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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mobile.cs205.composables.common.data.Question
import mobile.cs205.composables.common.data.topics
import mobile.cs205.ui.theme.md_theme_dark_errorContainer
import mobile.cs205.TimerViewModel

@Composable
fun QuizQuestionScreen(navController: NavController, timerViewModel: TimerViewModel = viewModel()) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var currentQuestionNumber by remember { mutableIntStateOf(1) }
    var correctAnswerNumber by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var showCorrectAnswer by remember { mutableStateOf(false) }

    val timeLeft by timerViewModel.sharedTime.collectAsState()
    val isTimerRunning by timerViewModel.isTimerRunning.collectAsState()
    val progress by timerViewModel.progress.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val quizId : Int? = arguments?.getString("quizId")?.toInt()
    var size by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = isTimerRunning, key2 = timeLeft) {
        if (!isTimerRunning && timeLeft <= 0) {

            showCorrectAnswer = true
            delay(2000) // Give some time to show the correct answer
            showCorrectAnswer = false

            // Check if there are more questions
            if (currentQuestionIndex < size - 1) {
                // Move to next question
                currentQuestionIndex++
                currentQuestionNumber++
                timerViewModel.startTimer() // Start the timer for the next question
            } else {
                // Handle the end of the quiz
                showDialog = true
            }
        }
    }

    LaunchedEffect(currentQuestionIndex) {
        timerViewModel.startTimer()
    }

    LaunchedEffect(quizId) {
        if (quizId != null) {
            size = topics.getOrNull(quizId)?.questions?.size ?: 0
        }
    }

    LaunchedEffect(quizId, currentQuestionNumber) {
        if (quizId != null && currentQuestionNumber > size) {
            showDialog = true
            timerViewModel.resetTimer()
            timerViewModel.stopTimer()
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
                    Timer(timeLeft = timeLeft)
                    ProgressBar(progress = progress)
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
                        },
                        showCorrectAnswer = showCorrectAnswer,
                        timerViewModel = timerViewModel
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
//@OptIn(DelicateCoroutinesApi::class)
//@Composable
//fun OptionsCard (
//    option: String,
//    correctAns:String,
//    onIncrementIndex: () -> Unit,
//    onIncrementCorrectNumber: () -> Unit,
//    timerViewModel: TimerViewModel // Add this parameter
//) {
//    var correct by remember { mutableStateOf(false) }
//    var wrong by remember { mutableStateOf(false) }
//
//
//    val colors = ButtonDefaults.buttonColors(
//        containerColor = if (correct) {
//            Color.Green
//        } else if (wrong) {
//            md_theme_dark_errorContainer
//        } else {
//            Color.DarkGray
//        }
//    )
//
//    Button(
//        modifier = Modifier
//            .padding(all = 8.dp)
//            .width(260.dp)
//            .height(45.dp),
//        shape = RoundedCornerShape(12.dp),
//        onClick = {
//            if (option == correctAns) {
//                correct = true
//                timerViewModel.stopTimer()
//
//                GlobalScope.launch {
//                    delay(800) // Adjust the delay duration as needed
//                    correct = false
//                    onIncrementIndex()
//                    onIncrementCorrectNumber()
//                }
//
//            } else {
//                wrong= true
//                timerViewModel.stopTimer()
//
//                GlobalScope.launch {
//                    delay(800) // Adjust the delay duration as needed
//                    wrong = false
//                    onIncrementIndex()
//                }
//
//            }
//        },
//        colors = colors
//    ) {
//        Text(option)
//    }
//}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun OptionsCard(
    option: String,
    correctAns: String,
    showCorrectAnswer: Boolean,
    onIncrementIndex: () -> Unit,
    onIncrementCorrectNumber: () -> Unit,
    timerViewModel: TimerViewModel
) {
    var isSelected by remember { mutableStateOf(false) }
    var correct by remember { mutableStateOf(false) }
    var wrong by remember { mutableStateOf(false) }
    var optionSelected by remember { mutableStateOf(false) } // Track if an option has been selected

    // Determine the button's background color based on state
    val backgroundColor = when {
        correct || (showCorrectAnswer && option == correctAns) -> Color.Green // Highlight correct answer
        wrong -> md_theme_dark_errorContainer // Incorrect option selected
        else -> Color.LightGray // Default color for unselected or incorrect but not selected options
    }

    Button(
        onClick = {
            if (!showCorrectAnswer && !correct && !wrong) { // Allow interaction only if not currently showing correct answer or previously interacted
                isSelected = true
                if (option == correctAns) {
                    correct = true
                    onIncrementCorrectNumber()
                } else {
                    wrong = true
                }
                optionSelected = true // Mark that an option has been selected
                timerViewModel.stopTimer() // Stop the timer regardless of whether the answer was correct or wrong

                GlobalScope.launch {
                    delay(800) // Delay for 0.8 seconds after option selected
                    isSelected = false // Reset selection state
                    correct = false // Reset correct state
                    wrong = false // Reset wrong state
                    if (optionSelected) { // Only trigger question switch if an option has been selected
                        onIncrementIndex() // Move to the next question after the delay
                    }
                    optionSelected = false // Reset optionSelected flag
                }
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .padding(8.dp)
            .width(260.dp)
            .height(45.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(option)
    }
}

//@Composable
//fun OptionList(
//    question: Question,
//    onIncrementIndex: () -> Unit,
//    onIncrementCorrectNumber: () -> Unit,
//    timerViewModel: TimerViewModel // Accept TimerViewModel here
//) {
//    Column (
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = question.question,
//            style = TextStyle(
//                textAlign = TextAlign.Center,
//            ),
//            modifier = Modifier
//                .fillMaxHeight(0.3f)
//                .padding(top = 10.dp),
//        )
//        Column {
////            for (i in question.answerOptions) {
////                OptionsCard(option = i, correctAns = qnAnswer, onIncrementIndex, onIncrementCorrectNumber)
////            }
//            question.answerOptions.forEach { option ->
//                OptionsCard(
//                    option = option,
//                    correctAns = question.correctAnswer,
//                    onIncrementIndex = onIncrementIndex,
//                    onIncrementCorrectNumber = onIncrementCorrectNumber,
//                    timerViewModel = timerViewModel // Pass it down here
//                )
//            }
//        }
//    }
//}

@Composable
fun OptionList(
    question: Question,
    showCorrectAnswer: Boolean, // Include this flag
    onIncrementIndex: () -> Unit,
    onIncrementCorrectNumber: () -> Unit,
    timerViewModel: TimerViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.question,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .padding(top = 10.dp),
        )
        // Iterate through each answer option and display it
        question.answerOptions.forEach { option ->
            OptionsCard(
                option = option,
                correctAns = question.correctAnswer,
                showCorrectAnswer = showCorrectAnswer, // Pass the flag to the OptionsCard
                onIncrementIndex = onIncrementIndex,
                onIncrementCorrectNumber = onIncrementCorrectNumber,
                timerViewModel = timerViewModel
            )
        }
    }
}


@Composable
fun Timer(timeLeft: Int) {
    Text("$timeLeft s")
}

@Composable
fun ProgressBar(progress: Float) {
    LinearProgressIndicator(
        progress = {
            progress
        },
        modifier = Modifier.padding(top = 50.dp),
    )
}