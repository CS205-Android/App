package mobile.cs205.composables.common.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun StartQuizFAB() {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
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
        StartQuizFAB()
    }
}