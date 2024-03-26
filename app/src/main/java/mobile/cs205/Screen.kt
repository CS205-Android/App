package mobile.cs205

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object QuizListing : Screen("quiz_listing")
    data object Question : Screen("question")
    data object Settings : Screen("setting")
    data object Scaffold : Screen("scaffold")
    data object QuizQuestion: Screen("quiz_question")
}