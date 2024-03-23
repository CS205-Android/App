package mobile.cs205.composables.common.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mobile.cs205.Screen
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun StartQuizFAB(navController: NavHostController) {
    ExtendedFloatingActionButton(
        containerColor   = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.onTertiary,
        onClick = { navController.navigate(Screen.Question.route) },
        icon = {
            Icon(
                Icons.Outlined.PlayArrow,
                "Extended Floating Action Button"
            )
        },
        text = { Text(text = "Start a random Quiz!") }
    )
}

@Composable
@Preview
fun StartQuizFABPreview() {
    CS205Theme {
        var controller = rememberNavController()
        StartQuizFAB(controller)
    }
}