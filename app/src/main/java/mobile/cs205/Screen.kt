package mobile.cs205

/**
 * This sealed class provides a way to ensure consistency between all composable when referencing the route to each and every one of the pages in our application
 * */
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object QuizListing : Screen("quiz_listing")
    data object Settings : Screen("setting")
    data object Scaffold : Screen("scaffold")
    data object QuizQuestion : Screen("quiz_question")
}