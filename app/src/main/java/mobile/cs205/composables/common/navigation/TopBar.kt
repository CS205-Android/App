package mobile.cs205.composables.common.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import mobile.cs205.R

/**
 * The TopBar composable that is only being used in Scaffold screen
 * The main purpose is to provide a sense of control for the user to understand where they are currently at out of all the screens
 * In order to prevent the TopBar from moving in scrollable screens, we opt in the use of an experimental pinnedScrollBehaviour to keep it sticky
 * @return A Top Bar composable that can be injected into a Scaffold.topBar
 * @param title : Receives the title from the current state that will be set by the BottomBar of our app
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String = "Hist-O-SG") {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        title = { Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
            rememberTopAppBarState()
        ),
        actions = {
            Icon(
                painterResource(id = R.drawable.logo_only_invis),
                contentDescription = "App logo",
                tint = Color.Unspecified,
                modifier = Modifier.height(56.dp)
            )
        }
    )

}