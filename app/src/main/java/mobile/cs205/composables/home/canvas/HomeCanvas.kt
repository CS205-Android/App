package mobile.cs205.composables.home.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * The HomeCanvas composable that is only being used in Home screen
 * Utilizing Canvas from 2D graphics, we created a linear gradient partial circular background to welcome the user to our application
 * @return A Canvas as a composable
 * */
@Composable
fun HomeCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFFFFDADA),
                    Color(0xFFFB89AE)
                )
            ),
            center = Offset(x = size.width / 2, y = size.height - 1050f),
            radius = size.width
        )
    }
}