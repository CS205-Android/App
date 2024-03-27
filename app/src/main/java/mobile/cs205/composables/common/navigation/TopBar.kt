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