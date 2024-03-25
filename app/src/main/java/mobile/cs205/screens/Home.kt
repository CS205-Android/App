package mobile.cs205.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mobile.cs205.composables.common.navigation.BottomBar
import mobile.cs205.composables.common.navigation.TopBar
import mobile.cs205.composables.home.canvas.HomeCanvas
import mobile.cs205.composables.home.card.TriviaCard
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(LocalConfiguration.current.screenHeightDp.dp / 20),
        horizontalAlignment = Alignment.CenterHorizontally
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

@Preview(showBackground = true, device = Devices.PIXEL_5)
@Composable
fun HomeScreenPreview() {
    CS205Theme {
        val navController = rememberNavController()
        Scaffold(
            topBar = { TopBar("Hist-O-SG") },
            bottomBar = {
                BottomBar(navController = navController) {
                }
            }

        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                HomeScreen(navController)
            }

        }
    }
}