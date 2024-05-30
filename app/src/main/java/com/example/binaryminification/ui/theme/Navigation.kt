package com.example.binaryminification.ui.theme

import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.binaryminification.ui.theme.screens.CalcScreen
import com.example.binaryminification.ui.theme.screens.HistoryScreen
import com.example.binaryminification.ui.theme.screens.MenuScreen
import com.example.binaryminification.ui.theme.screens.OnboardingScreen
import com.example.binaryminification.ui.theme.screens.SplashScreen

object SplashScreen {

    fun route(): String {
        return "main"
    }
}

object OnboardingScreen {
    fun route(): String {
        return "onboarding"
    }
}

object CalcScreen {

    fun route(): String {
        return "calc"
    }
}

object MenuScreen {
    fun route():String {
        return "menu"
    }
}

object HistoryScreen {
    fun route():String {
        return "history"
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
        composable(OnboardingScreen.route()) {
            OnboardingScreen(navController = navController)}
        composable(CalcScreen.route()) {
            CalcScreen(navController = navController)
        }
        composable(MenuScreen.route()) {
            MenuScreen(navController = navController)
        }
        composable(HistoryScreen.route()) {
            HistoryScreen(navController = navController)
        }
    }
}
