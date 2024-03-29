package mobile.cs205.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.delay
import mobile.cs205.composables.quiz.button.OptionsGroup
import mobile.cs205.composables.quiz.dialog.CongratulationsDialog
import mobile.cs205.composables.quiz.section.Footer
import mobile.cs205.composables.quiz.section.Timer
import mobile.cs205.composables.quiz.service.KeepScreenOn
import mobile.cs205.data.quiz.topics
import mobile.cs205.timer.TimerViewModel

/**
 * The QuizListing composable represents the Quiz Listing screen of the application
 * @return An LazyColumn and AlertDialog composable
 * @param navController : A NavHostController from the root to navigate to be passed to CongratulationsDialog so that the user can return back to the Home Screen
 * @param timerViewModel : A global state provided by the TimerViewModel to be able to manage the synchronization of the timer and progress bar
 * */
@Composable
fun QuizQuestionScreen(navController: NavController, timerViewModel: TimerViewModel = viewModel()) {
    // Keeps track of the status of the quiz
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var correctAnswerNumber by remember { mutableIntStateOf(0) }
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var showCorrectAnswer by remember { mutableStateOf(false) }

    // Obtain the states from the timerViewModel
    val timeLeft by timerViewModel.sharedTime.collectAsState()
    val isTimerRunning by timerViewModel.isTimerRunning.collectAsState()
    val progress by timerViewModel.progress.collectAsState()

    // Initializes the quiz questions to be rendered with the help of the following states
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val quizId: Int? = arguments?.getString("quizId")?.toInt()
    var size by remember { mutableIntStateOf(0) }

    // Keeps the screen always active as long as the user is still doing the quiz
    KeepScreenOn()

    // Handler for time limit exceeded, must use a LaunchedEffect to "listen" to state changes instead of immediately running the code when the composable is rendered
    LaunchedEffect(key1 = isTimerRunning, key2 = timeLeft) {
        if (!isTimerRunning && timeLeft <= 0) {
            // Show the user whether if they got the question correct for 2 seconds
            showCorrectAnswer = true
            delay(2000)
            showCorrectAnswer = false

            // If the there are more questions, display the next question and start the timer again, else show the CongratulationsDialog
            if (currentQuestionIndex < size - 1) {
                currentQuestionIndex++
                timerViewModel.startTimer()
            } else {
                setShowDialog(true)
            }
        }
    }

    // Starts the timer if the user has selected an answer instead of timeout
    LaunchedEffect(currentQuestionIndex) {
        timerViewModel.startTimer()
    }

    // Wait for quizId to be passed from path params, obtain the size of the quiz topic when it has been passed
    LaunchedEffect(quizId) {
        if (quizId != null) {
            size = topics.getOrNull(quizId)?.questions?.size ?: 0
        }
    }

    // Handler for quiz ends if the event where user selects an answer
    LaunchedEffect(quizId, currentQuestionIndex) {
        if (quizId != null && size > 0 && currentQuestionIndex >= size) {
            setShowDialog(true)
            timerViewModel.resetTimer()
            timerViewModel.stopTimer()
        }
    }

    /**
     * The UI rendering part begins here
     * */

    Box(modifier = Modifier.fillMaxSize()) {
        // Dialog is displayed upon completion
        if (showDialog) {
            CongratulationsDialog(
                showDialog = setShowDialog,
                navController = navController,
                correctAnswerNumber = correctAnswerNumber
            )
        }
        // When the user has yet to complete the quiz, this portion will run
        Column {
            //TOP PORTION
            Timer(timeLeft = timeLeft, progress = progress)
            //MIDDLE PORTION
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                // Obtain the current question to be rendered on the screen
                val question =
                    if (quizId != null && quizId in topics.indices && currentQuestionIndex in topics[quizId].questions.indices) {
                        topics[quizId].questions[currentQuestionIndex]
                    } else {
                        null
                    }
                question?.let {
                    OptionsGroup(
                        question = it,
                        onIncrementIndex = { currentQuestionIndex++ },
                        onIncrementCorrectNumber = { correctAnswerNumber++ },
                        timerViewModel = timerViewModel
                    )
                }
            }
            // Shows the current question and remaining questions
            Footer(currentQuestionIndex = currentQuestionIndex, size = size)
        }
    }
}