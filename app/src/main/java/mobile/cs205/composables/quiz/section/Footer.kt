package mobile.cs205.composables.quiz.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * The Footer composable that is only being used in QuizQuestion screen
 * Renders the current question the user is seeing and questions remaining
 * @param currentQuestionIndex : Index of the current question (starts from 0)
 * @param size : Size of the quiz topic, indirectly tells the number of questions in the quiz topic
 * @return A Box composable
 * */
@Composable
fun Footer(
    currentQuestionIndex: Int,
    size: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =
                if (currentQuestionIndex < size) "QUESTION ${currentQuestionIndex + 1}/$size" else ""
            )

        }
    }
}