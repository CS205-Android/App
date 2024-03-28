package mobile.cs205.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mobile.cs205.composables.home.canvas.HomeCanvas
import mobile.cs205.composables.home.card.TriviaCard

/**
 * The Home composable represents the Home screen of the application
 * It calls the HomeCanvas and TriviaCard composable to display all of its contents
 * @return A Column composable that represents a fragment for the Home Screen
 * */
@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(LocalConfiguration.current.screenHeightDp.dp / 20),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = with(Modifier) {
                fillMaxWidth()
                    .fillMaxHeight(0.2f)
            }) {
            HomeCanvas()
            Text(
                text = "Welcome to\nHist-O-SG",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        }
        TriviaCard()
    }
}