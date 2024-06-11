package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.presentation.calc.CalcViewModel
import com.example.binaryminification.ui.theme.HistoryScreen
import com.example.binaryminification.ui.theme.MenuScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CalcScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    calcViewModel: CalcViewModel = viewModel(),
) {
    val state by calcViewModel.state.collectAsState()
    Scaffold(
        topBar = {
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
        },
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = modifier.size(24.dp))
                Text(
                    text = stringResource(R.string.`fun`),
                    style = TextStyle(MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier.padding(start = 8.dp)
                )
                BasicTextField(
                    value = state.input,
                    onValueChange = { newValue -> calcViewModel.onInputUpdate(newValue) },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = state.output,
                    style = TextStyle(MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Box(contentAlignment = Alignment.BottomCenter) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("A") }) {
                            Text(text = "A")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("B") }) {
                            Text(text = "B")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("C") }) {
                            Text(text = "C")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("D") }) {
                            Text(text = "D")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("∨") }) {
                            Text(text = "∨")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("∧") }) {
                            Text(text = "∧")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("¬") }) {
                            Text(text = "¬", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("→") }) {
                            Text(text = "→", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("~") }) {
                            Text(text = "~", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("⊕") }) {
                            Text(text = "⊕", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("|") }) {
                            Text(text = "|", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("↓") }) {
                            Text(text = "↓", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("(") }) {
                            Text(text = "(")
                        }
                    }
                    item {
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick(")") }) {
                            Text(text = ")")
                        }
                    }
                    item {
                        FloatingActionButton(
                            onClick = { calcViewModel.onDeleteButtonClick() },
                            modifier = Modifier.combinedClickable(
                                onClick = { },
                                onLongClick = { calcViewModel.onClear() }
                            )
                        ) {
                            Text(text = "Del")
                        }
                    }
                    item {
                        FloatingActionButton(
                            onClick = { calcViewModel.onCalculateButtonClick() }
                        ) {
                            Text(text = "=", style = TextStyle(fontSize = 20.sp))
                        }
                    }
                }
            }
        }
    }
}





