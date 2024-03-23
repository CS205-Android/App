package mobile.cs205.composables.home.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun TriviaCard(){
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp

    ElevatedCard(
        modifier = androidx.compose.ui.Modifier
            .width(screenWidth - 48.dp)
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
    ) {
//            This part will be the image
        Box(modifier = with(Modifier) {
            fillMaxWidth()
                .height(screenWidth / 1.8f)
                .background(Color.Blue)
        })
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Do You know?", style = MaterialTheme.typography.bodyLarge)

        }

    }
}