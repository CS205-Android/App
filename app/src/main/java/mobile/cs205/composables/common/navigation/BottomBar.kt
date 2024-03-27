package mobile.cs205.composables.common.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavData(val page: String, val name: String, val icon: ImageVector)

val items = listOf(
    NavData("Home", "home", Icons.Outlined.Home),
    NavData("Quiz", "quiz_listing", Icons.Outlined.Quiz),
    NavData("Settings", "setting", Icons.Outlined.Settings) // Placeholder activity for now!
)

@Composable
fun BottomBar(navController: NavController, setCurrent: (String) -> Unit) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.name } == true,
                onClick = {
                    navController.navigate(item.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    setCurrent(if (item.page == "Home") "Hist-O-SG" else item.page)
                },
                icon = { Icon(item.icon, contentDescription = item.page) },
                label = { Text(item.page) })
        }
    }
}