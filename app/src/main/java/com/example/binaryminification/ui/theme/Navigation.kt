package com.example.binaryminification.ui.theme

import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.binaryminification.ui.theme.screens.CalcScreen
import com.example.binaryminification.ui.theme.screens.SplashScreen

object SplashScreen {

    fun route(): String {
        return "main"
    }
}

object CalcScreen {

    fun route(): String {
        return "calc"
    }
}

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashScreen.route(),
        enterTransition = { scaleIn() },
    ) {
        composable(SplashScreen.route()) {
            SplashScreen(navController = navController)
        }
        composable(CalcScreen.route()) {
            CalcScreen(navController = navController)
        }
    }
}
