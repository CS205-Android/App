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

@Composable
fun QuizQuestionScreen(navController: NavController, timerViewModel: TimerViewModel = viewModel()) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var correctAnswerNumber by remember { mutableIntStateOf(0) }
    var (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var showCorrectAnswer by remember { mutableStateOf(false) }

    val timeLeft by timerViewModel.sharedTime.collectAsState()
    val isTimerRunning by timerViewModel.isTimerRunning.collectAsState()
    val progress by timerViewModel.progress.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments
    val quizId: Int? = arguments?.getString("quizId")?.toInt()
    var size by remember { mutableIntStateOf(0) }

    KeepScreenOn()

    LaunchedEffect(key1 = isTimerRunning, key2 = timeLeft) {
        if (!isTimerRunning && timeLeft <= 0) {
            showCorrectAnswer = true
            delay(2000) // Give some time to show the correct answer
            showCorrectAnswer = false

            if (currentQuestionIndex < size - 1) {
                currentQuestionIndex++
                timerViewModel.startTimer()
            } else {
                setShowDialog(true)
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

    LaunchedEffect(quizId, currentQuestionIndex) {
        if (quizId != null && size > 0 && currentQuestionIndex >= size) {
            setShowDialog(true)
            timerViewModel.resetTimer()
            timerViewModel.stopTimer()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (showDialog) {
            CongratulationsDialog(
                showDialog = setShowDialog,
                navController = navController,
                correctAnswerNumber = correctAnswerNumber
            )
        }
        Column {
            //TOP PORTION
            Timer(timeLeft = timeLeft, progress = progress)
            //MIDDLE PORTION
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
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
            //END PORTION
            Footer(currentQuestionIndex = currentQuestionIndex, size = size)
        }
    }
}