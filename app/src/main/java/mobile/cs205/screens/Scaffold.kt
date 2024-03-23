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
import mobile.cs205.FragmentNavGraph
import mobile.cs205.composables.common.navigation.BottomBar
import mobile.cs205.composables.common.navigation.TopBar

@Composable
fun ScaffoldScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    var (current, setCurrent) =  remember {
        mutableStateOf("Hist-O-SG")
    }

    Scaffold(
        topBar = { TopBar(current) },
        bottomBar = { BottomBar(navController, setCurrent) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FragmentNavGraph(navController = navController, rootNavController = rootNavController)
        }

    }
}