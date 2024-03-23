package mobile.cs205.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun QuestionScreen() {
    Text(text = "Quiz Screen")
}

@Preview
@Composable
fun QuizScreenPreview() {
    CS205Theme {
        QuestionScreen()
    }
}