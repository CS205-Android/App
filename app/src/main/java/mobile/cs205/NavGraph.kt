package mobile.cs205

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mobile.cs205.screens.HomeScreen
import mobile.cs205.screens.QuestionScreen
import mobile.cs205.screens.QuizListingScreen
import mobile.cs205.screens.ScaffoldScreen
import mobile.cs205.screens.SettingsScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Scaffold.route ){
        composable(route = Screen.Question.route){ QuestionScreen() }
        composable(route = Screen.Scaffold.route) { ScaffoldScreen(navController) }
    }
}

@Composable
fun FragmentNavGraph(navController: NavHostController, rootNavController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) { HomeScreen(rootNavController) }
        composable(route = Screen.QuizListing.route) { QuizListingScreen() }
        composable(route = Screen.Settings.route) { SettingsScreen()}
    }
}