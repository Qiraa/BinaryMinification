package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.calcfunction.BinaryCalculator
import com.example.binaryminification.ui.theme.HistoryScreen
import com.example.binaryminification.ui.theme.MenuScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var outputMessage: String = ""
    val message = remember {
        mutableStateOf(" ")
    }
    val outputMessageState = remember {
        mutableStateOf("")
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            Row(modifier = modifier) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = stringResource(id = R.string.logic_calc),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    actions = {
                        IconButton(onClick = {navController.navigate(HistoryScreen.route()) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.history),
                                contentDescription = stringResource(id = R.string.calc_history),
                                tint = Color.White,
                                modifier = Modifier.size(32.dp),
                            )
                        }
                        IconButton(onClick = {navController.navigate(MenuScreen.route()) }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = stringResource(id = R.string.menu),
                                tint = Color.White,
                                modifier = Modifier.size(32.dp),
                            )
                        }
                    }
                )
            }

            Spacer(modifier = modifier.size(24.dp))

            Text(text = stringResource(R.string.`fun`),
                modifier = Modifier.padding(start = 8.dp))
            BasicTextField(
                value = message.value,
                onValueChange = { newValue -> message.value = newValue},
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            )
            Button(onClick = {
                val a = BinaryCalculator();
                a.calcFunction("(x->y)&&z")
                outputMessageState.value = a.allPrintsString
            }){}
            LazyColumn {
                item {
                    Text(text = outputMessageState.value)
                    Spacer(modifier = modifier.size(24.dp))
                }
            }

        }
    }
}



