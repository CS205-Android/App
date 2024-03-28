package mobile.cs205.composables.settings.switches

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * The DifficultySwitch composable that is only being used in Settings screen
 * It serves as a to be done feature in the future to enable quizzes to be more difficult to do by halving the time limit to answer
 * @return A Switch composable
 * */
@Composable
fun DifficultySwitch() {
    var checked by remember { mutableStateOf(true) }
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}