package mobile.cs205.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mobile.cs205.Screen
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun HomeScreen(navController: NavController) {
    Text(text = "Home")
    Button(onClick = { navController.navigate(Screen.Question.route) }) {
        Text(text = "Go Quiz")
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CS205Theme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}