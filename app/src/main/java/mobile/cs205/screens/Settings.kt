package mobile.cs205.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mobile.cs205.composables.settings.switches.DifficultySwitch

/**
 * The Settings composable represents the Settings screen of the application
 * TODO: Allows user to change question duration to 5s per question (Unfortunately run out of time before we can finish)
 * @return A Row composable
 * */
@Composable
fun SettingsScreen() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .padding(top = 5.dp)
            .fillMaxWidth()

    ) {
        Text(text = "Hard Mode (5s per question)")
        DifficultySwitch()
    }
}

