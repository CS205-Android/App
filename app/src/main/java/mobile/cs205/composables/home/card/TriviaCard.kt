package mobile.cs205.composables.home.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import mobile.cs205.services.HistoryService
import mobile.cs205.data.home.SingaporeImageListing
import mobile.cs205.data.home.HistoryResponse

/**
 * The TriviaCard composable is only being used in Home screen
 * The main use of this composable is to create an Elevated Card that contains a trivia historical event that happened in Singapore
 *
 * The image and event rendered in this composable is retrieved from the Internet using Coil & Ktor Client respectively
 * This means that the INTERNET permission must be enabled for the contents of this particular composable to be fetched
 *
 * It is not directly coded inside the Home screen so that the logic and states involved to render the card is not being all in one file which can significantly make the maintainability of the file much harder
 * @return An ElevatedCard composable
 * */
@Composable
fun TriviaCard() {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    // Creates an image request to fetch the image when rendering the composable
    val imageModel = ImageRequest.Builder(LocalContext.current)
        .data(SingaporeImageListing[SingaporeImageListing.indices.random()])
        .crossfade(true)
        .build()
    // Create the Ktor client and retrieve data from the network
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
        // Retrieve a random value in the list of data returned to be displayed to the user
        value = data[data.indices.random()]
    }

    ElevatedCard(
        modifier = Modifier
            .width(screenWidth - 48.dp)
            .height(IntrinsicSize.Min)
            .wrapContentHeight()
    ) {
        Box(
            modifier = with(Modifier) {
                fillMaxWidth()
                    .height(screenWidth / 1.8f)
                    .background(MaterialTheme.colorScheme.surface)
            },
        ) {
            /**
             * SubcomposeAsyncImage loads the image when the network request is fulfilled with the contents being scaled to fit the bounds of the parent box
             * A CirculuarProgressIndicator will be displayed while the image request is still yet to be fulfilled
             * */
            SubcomposeAsyncImage(
                contentScale = ContentScale.FillBounds,
                model = imageModel,
                contentDescription = "Singapore"
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp))
                    }
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {

            /**
             * This particular section is where the history data fetched is being used
             * The date needs to be concatenated to display properly while the event description can be directly rendered based on the return body by the API
             * */
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