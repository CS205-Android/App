package mobile.cs205.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mobile.cs205.FragmentNavGraph
import mobile.cs205.Screen
import mobile.cs205.composables.common.button.StartQuizFAB
import mobile.cs205.composables.common.navigation.BottomBar
import mobile.cs205.composables.common.navigation.TopBar

@Composable
fun ScaffoldScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    val (current, setCurrent) = remember {
        mutableStateOf("Hist-O-SG")
    }

    Scaffold(
        topBar = { TopBar(current) },
        bottomBar = { BottomBar(navController, setCurrent) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (current == "Hist-O-SG") {
                StartQuizFAB(rootNavController)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FragmentNavGraph(navController = navController, rootNavController = rootNavController)
        }

    }
}