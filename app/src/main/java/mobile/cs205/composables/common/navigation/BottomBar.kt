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
import mobile.cs205.Screen

/**
 * The NavData class which is responsible to store each pages identifier and their icons to display on the BottomBar
 * @param page : The text to be displayed referencing the page for each NavigationBarItem
 * @param name : The route to the particular page
 * @param icon : The ImageVector icon to display in the BottomBar for each fragment
 * */
data class NavData(val page: String, val name: String, val icon: ImageVector)

/**
 * Holds the list of NavData to be dynamically rendered in the BottomBar
 * */
val items = listOf(
    NavData("Home", Screen.Home.route, Icons.Outlined.Home),
    NavData("Quiz", Screen.QuizListing.route, Icons.Outlined.Quiz),
    NavData(
        "Settings",
        Screen.Settings.route,
        Icons.Outlined.Settings
    )
)

/**
 * The BottomBar will only be injected to the Scaffold screen.
 * It is responsible as the primary method of navigation between each screen in our application
 * @param navController : A NavController component passed from FragmentNavHost
 * @param setCurrent : A setter of the mutableState to modify the current selected navigation bar item
 * @return A Navigation Bar composable that can be injected into a Scaffold.bottomBar
 * */
@Composable
fun BottomBar(navController: NavController, setCurrent: (String) -> Unit) {
    NavigationBar {
        // This is to retrieve the present screen that the user is at
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        // This will render each NavigationBarItem based on the items we declared above
        items.forEach { item ->
            NavigationBarItem(
                // The selected tab will show the state of the particular item as active if its route matches the name of the item
                selected = currentDestination?.hierarchy?.any { it.route == item.name } == true,
                onClick = {
                    /**
                     * When navigating to another screen, the back stack entry can easily become "thick" if the user constantly change screens
                     * On top of that, the user's current scroll position in the QuizListing page is not persisted causing the user to have to scroll the whole list from the beginning if navigate back to it
                     * Furthermore, the Home screen that consists of network calls will be reloaded everytime the user navigate to another page.
                     *
                     * To tackle this issue, we implemented the 3 points commented below
                     * */
                    navController.navigate(item.name) {
                        // 1) Save state of the current screen when the user changes to a different screen
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // 2) Launch only a single instance of the destination to prevent a "thick" back stack when the user constantly press the bottomBar navigations
                        launchSingleTop = true
                        // 3) Restores the state of the current fragment when the user return to it from the other fragments
                        restoreState = true
                    }

                    // This is to set the top bar contents to Hist-O-SG when user is at the home page
                    setCurrent(if (item.page == "Home") "Hist-O-SG" else item.page)
                },
                icon = { Icon(item.icon, contentDescription = item.page) },
                label = { Text(item.page) })
        }
    }
}