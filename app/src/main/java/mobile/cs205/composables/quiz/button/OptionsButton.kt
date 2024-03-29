package mobile.cs205.composables.quiz.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mobile.cs205.ui.theme.md_theme_dark_errorContainer

/**
 * The OptionsButton composable that is only being used in QuizQuestion screen
 * Renders each possible choice the user can pick for each question
 * It will also set the states of OptionsGroup composable and renders differently when an answer is picked
 * @param chosen : A mutable state value that specifies the choice the user has chosen
 * @param setChosen : A mutable state setter to "inform" the OptionsGroup if the user select the option as the choice
 * @param option : The text to display to the user as one of the option
 * @param correctAns : Holds the correct option for the question
 * @return A Box composable
 * */
@Composable
fun OptionsButton(
    chosen: String?,
    setChosen: (String?) -> Unit,
    option: String,
    correctAns: String,
) {
    // Based on the user's answer, decide the background color to display (green for choosing correct, red for choosing wrong, tertiary color if no response or not chosen)
    val backgroundColor =
        if (chosen == correctAns && option == chosen) Color(0xFF04B900) else
            (if (option == chosen) md_theme_dark_errorContainer else
                MaterialTheme.colorScheme.tertiary)

    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp

    Button(
        onClick = { setChosen(option) },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .padding(8.dp)
            .width(screenWidth - 48.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(option, textAlign = TextAlign.Center)
    }
}