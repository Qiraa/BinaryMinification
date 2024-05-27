package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.ui.theme.OnboardingScreen

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.main_background),
                contentScale = ContentScale.Crop

            )){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Image(
                    alignment = Alignment.Center,
                    modifier = Modifier.size(152.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo)
                )
                Text(
                    text = stringResource(id = R.string.logic_calc),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                )
        }
    }
}