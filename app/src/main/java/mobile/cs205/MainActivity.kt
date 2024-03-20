package mobile.cs205

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mobile.cs205.composables.common.navigation.TopBar
import mobile.cs205.ui.theme.CS205Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS205Theme {
                HomePage()

            }
        }
    }
}

@Composable
fun HomePage() {
    Scaffold(
        topBar = { TopBar("Hello World") },
        bottomBar = {},
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
                icon = { Icon(Icons.Outlined.PlayArrow, "Extended Floating Action Button") },
                text = { Text(text = "Start a random Quiz!") }
            )
        }, floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.primary,
                text = """
                            This ia an example of a scaffold.
                        """.trimIndent()
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    CS205Theme {
        HomePage()
    }
}