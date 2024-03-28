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

@Composable
fun OptionsGroup(
    question: Question,
    onIncrementIndex: () -> Unit,
    onIncrementCorrectNumber: () -> Unit,
    timerViewModel: TimerViewModel
) {
    val (chosen, setChosen) = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = chosen) {
        if(chosen != null){
            if(chosen == question.correctAnswer){
                onIncrementCorrectNumber()
            }
            timerViewModel.stopTimer()
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