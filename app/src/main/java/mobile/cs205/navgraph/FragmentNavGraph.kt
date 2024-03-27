package mobile.cs205.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mobile.cs205.Screen
import mobile.cs205.screens.HomeScreen
import mobile.cs205.screens.QuizListingScreen
import mobile.cs205.screens.SettingsScreen

@Composable
fun FragmentNavGraph(navController: NavHostController, rootNavController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) { HomeScreen() }
        composable(route = Screen.QuizListing.route) { QuizListingScreen(rootNavController) }
        composable(route = Screen.Settings.route) { SettingsScreen() }
    }
}