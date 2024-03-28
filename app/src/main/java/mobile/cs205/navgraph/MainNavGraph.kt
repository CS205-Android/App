package mobile.cs205.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mobile.cs205.Screen
import mobile.cs205.screens.QuizQuestionScreen
import mobile.cs205.screens.ScaffoldScreen

/**
 * A composable that stores the NavHost to Scaffold & QuizQuestion
 * This is the outermost NavHost that is used in MainActivity
 * The purpose of making such as nested NavHost architecture is to be able to reuse the TopBar and BottomBar of screens that requires the Scaffold
 * It also allows the TopBar and BottomBar to be hidden when the user is doing a quiz
 * @param navController : The NavHostController for this composable
 * @return A NavHost type composable
 * */
@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Scaffold.route ){
        composable(route = Screen.QuizQuestion.route + "/{quizId}") {QuizQuestionScreen(navController)}
        composable(route = Screen.Scaffold.route) { ScaffoldScreen(navController) }
    }
}


