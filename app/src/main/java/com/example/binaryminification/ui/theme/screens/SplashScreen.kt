package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.data.OnBoardingManager
import com.example.binaryminification.ui.theme.CalcScreen
import com.example.binaryminification.ui.theme.MenuScreen
import com.example.binaryminification.ui.theme.OnboardingScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val onboardingManager = OnBoardingManager(LocalContext.current)
    LaunchedEffect(Unit) {
        delay(500L)
        if (onboardingManager.isOnBoardingShown()) {
            navController.navigate(CalcScreen.route())
        } else {
            navController.navigate(OnboardingScreen.route())
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.main_background),
                contentScale = ContentScale.Crop,
            )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.size(152.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo)
            )
            Text(
                text = stringResource(id = R.string.logic_calc),
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.kalam_bold)
                    ),
                    fontSize = 32.sp,
                ),
            )

            CircularProgressIndicator()
        }
    }
}

