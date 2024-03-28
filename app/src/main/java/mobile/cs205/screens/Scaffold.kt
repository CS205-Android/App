package mobile.cs205.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mobile.cs205.navgraph.FragmentNavGraph
import mobile.cs205.composables.common.navigation.BottomBar
import mobile.cs205.composables.common.navigation.TopBar

/**
 * The Scaffold composable represents the Scaffold screen of the application
 * It is mainly used to render and reuse the top and bottom bar with the contents of Home, QuizListing and Settings depending of the current location in the FragmentNavController
 * It holds the FragmentNavGraph in it so that the top and bottom bar doesn't appear to be redrawn into the screen but instead transition when a different fragment is requested
 * @return An Scaffold composable
 * @param rootNavController : A NavHostController from the root to be passed to screens that requires the rootNavController in the Fragment NavGraph
 * */
@Composable
fun ScaffoldScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    val (current, setCurrent) = remember {
        mutableStateOf("Hist-O-SG")
    }

    Scaffold(
        topBar = { TopBar(current) },
        bottomBar = { BottomBar(navController, setCurrent) },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FragmentNavGraph(navController = navController, rootNavController = rootNavController)
        }

    }
}