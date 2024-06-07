package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.ui.theme.Grape
import com.example.binaryminification.ui.theme.HistoryScreen
import com.example.binaryminification.ui.theme.MenuScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CalcScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var message by remember {
        mutableStateOf(" ")
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            Row {
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
                            style = TextStyle(
                                fontFamily = FontFamily(
                                    Font(R.font.kalam_bold)
                                )
                            ),
                            fontSize = 32.sp
                        )
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(HistoryScreen.route()) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.history),
                                contentDescription = stringResource(id = R.string.calc_history),
                                tint = Color.White,
                                modifier = Modifier.size(32.dp),
                            )
                        }
                        IconButton(onClick = { navController.navigate(MenuScreen.route()) }) {
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
            Text(
                text = stringResource(R.string.`fun`),
                style = TextStyle(MaterialTheme.colorScheme.tertiary),
                modifier = Modifier.padding(start = 8.dp)
            )
            BasicTextField(
                value = message,
                onValueChange = { newValue -> message = newValue },
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),

                    ) {
                    item {
                        FloatingActionButton(onClick = { message += "A" }) {
                            Text(text = "A")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "B" }) {
                            Text(text = "B")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "C" }) {
                            Text(text = "C")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "D" }) {
                            Text(text = "D")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "∨" }) {
                            Text(text = "∨")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "∧" }) {
                            Text(text = "∧")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "¬" }) {
                            Text(text = "¬", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "→" }) {
                            Text(text = "→", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "~" }) {
                            Text(text = "~", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "⊕" }) {
                            Text(text = "⊕", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "|" }) {
                            Text(text = "|", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "↓" }) {
                            Text(text = "↓", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += ")" }) {
                            Text(text = "(")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += ")" }) {
                            Text(text = ")")
                        }
                    }
                    item {
                        FloatingActionButton(
                            onClick = { message = message.dropLast(1) },
                            modifier = Modifier.combinedClickable(
                                onClick = { },
                                onLongClick = { message = " " }
                            )
                        ) {
                            Text(text = "Del")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { message += "" }) {
                            Text(text = "=", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                }
            }
        }

    }
}





