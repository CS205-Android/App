package mobile.cs205.composables.quiz.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mobile.cs205.data.quiz.Question
import mobile.cs205.timer.TimerViewModel

/**
 * The Options composable that is only being used in QuizQuestion screen
 * Renders the possible answers of the current question
 * Also contain some logic to keep track of the chosen answer and synchronization of the timer
 * @param question : Takes in the current question being displayed with its answers
 * @param onIncrementIndex : To mutate the state to move to the next question
 * @param onIncrementCorrectNumber : To mutate the state by increasing it every time the user got the question correct
 * @param timerViewModel : To call functions in the timerViewModel to start, stop and reset the timer
 * @return A Box composable
 * */
@Composable
fun OptionsGroup(
    question: Question,
    onIncrementIndex: () -> Unit,
    onIncrementCorrectNumber: () -> Unit,
    timerViewModel: TimerViewModel
) {
    // Keeps track if a choice is chosen and if so, which. It is null by default to indicate no choice is chosen
    val (chosen, setChosen) = remember { mutableStateOf<String?>(null) }

    // Handles the state mutation and timer management when an answer is chosen
    LaunchedEffect(key1 = chosen) {
        if(chosen != null){
            // Increment correct count when user picked the right answer
            if(chosen == question.correctAnswer){
                onIncrementCorrectNumber()
            }
            timerViewModel.stopTimer()
            // Change question after a bit of delay and reset the chosen state
            timerViewModel.launchCoroutine(800) {
                setChosen(null)
                onIncrementIndex()
            }
        }
    }

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
            OptionsButton(
                option = option,
                correctAns = question.correctAnswer,
                chosen = chosen,
                setChosen = setChosen
            )
        }
    }
}