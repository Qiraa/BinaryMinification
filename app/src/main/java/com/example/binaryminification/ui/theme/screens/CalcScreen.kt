package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.binaryminification.R

@Composable
fun CalcScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val message = remember {
        mutableStateOf(" ")
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column {
            Row (modifier = modifier
                .background(colorResource(id = R.color.blueViolet))
                .fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.logic_calc),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                )
            }

            TextField(
                value = message.value,
                onValueChange = { newText -> message.value = newText },
                placeholder = { Text(text = stringResource(id = R.string.`fun`))},
            )
        }
    }
}

