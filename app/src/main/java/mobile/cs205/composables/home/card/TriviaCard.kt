package mobile.cs205.composables.home.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mobile.cs205.data.remote.HistoryService
import mobile.cs205.data.remote.SingaporeImageListing
import mobile.cs205.data.remote.data.HistoryResponse
import mobile.cs205.ui.theme.CS205Theme

@Composable
fun TriviaCard() {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    val service = HistoryService.create()
    val history = produceState(
        initialValue = HistoryResponse(
            "XX",
            "XX",
            "XX",
            "Loading..."
        )
    ) {
        val data = service.getHistory()
        value = data[data.indices.random()]
    }
    val image = produceState(initialValue = "") {
        value = SingaporeImageListing[SingaporeImageListing.indices.random()]
    }

    ElevatedCard(
        modifier = androidx.compose.ui.Modifier
            .width(screenWidth - 48.dp)
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
    ) {
        Box(modifier = with(Modifier) {
            fillMaxWidth()
                .height(screenWidth / 1.8f)
                .background(Color.Blue)
        }) {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = with(Modifier) {
                    fillMaxWidth()
                        .height(screenWidth / 1.8f)
                },
                model = image.value,
                contentDescription = "Singapore"
            )
        }

        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Do You know?", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "In ${history.value.day}/${history.value.month}/${history.value.year}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = history.value.event,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Composable
@Preview
fun TriviaCardPreview() {
    CS205Theme {
        TriviaCard()
    }
}