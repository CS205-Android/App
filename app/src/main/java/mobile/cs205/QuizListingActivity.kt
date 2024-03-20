package mobile.cs205

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mobile.cs205.ui.theme.CS205Theme

class QuizListingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS205Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ShowList()
                }
            }
        }
    }

    @Composable
    fun ShowList(modifier: Modifier = Modifier) {
        val names = listOf("person 1\n", "person 2", "person 3",
                        "person 4", "person 5", "person 6",
                        "person 7", "person 8", "person 9",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
                        "person 10", "person 11", "person 12",
            )
        Scaffold (

        ) {
            innerPadding ->
            LazyColumn {
                items(names) { name ->
                    TextButton(
                        onClick = { onClick() },
                        modifier = Modifier.fillMaxWidth()) {
                        Text(name, modifier = Modifier.padding(innerPadding))
                    }
                    Divider()
                }
            }
        }

    }

    private fun onClick() {
//        TODO("Not yet implemented")
        println("Button pressed")
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun ShowListPreview() {
        CS205Theme {
            ShowList()
        }
    }
}