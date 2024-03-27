package mobile.cs205.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mobile.cs205.Screen
import mobile.cs205.screens.HomeScreen
import mobile.cs205.screens.QuizListingScreen
import mobile.cs205.screens.QuizQuestionScreen
import mobile.cs205.screens.ScaffoldScreen
import mobile.cs205.screens.SettingsScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Scaffold.route ){
        composable(route = Screen.QuizQuestion.route + "/{quizId}") {QuizQuestionScreen(navController)}
        composable(route = Screen.Scaffold.route) { ScaffoldScreen(navController) }
    }
}


