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

@Composable
fun OptionsButton(
    chosen: String?,
    setChosen: (String?) -> Unit,
    option: String,
    correctAns: String,
) {
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