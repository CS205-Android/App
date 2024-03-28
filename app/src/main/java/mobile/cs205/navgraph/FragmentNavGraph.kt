package mobile.cs205.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mobile.cs205.Screen
import mobile.cs205.screens.HomeScreen
import mobile.cs205.screens.QuizListingScreen
import mobile.cs205.screens.SettingsScreen

/**
 * A composable that stores the NavHost to Home, Quiz Listing & Home
 * This should be use inside a Scaffold because the screens referred to here are all fragments where the top bar and the bottom bar are actually reused rather than replaced
 * @param navController : The NavHostController for this composable
 * @param rootNavController : The NavHostController to be passed to the child for the pages that requires logic to route back to the QuizQuestion Screen located in the root Nav Host
 * @return A NavHost type composable
 * */
@Composable
fun FragmentNavGraph(navController: NavHostController, rootNavController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) { HomeScreen() }
        composable(route = Screen.QuizListing.route) { QuizListingScreen(rootNavController) }
        composable(route = Screen.Settings.route) { SettingsScreen() }
    }
}