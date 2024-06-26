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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.domain.getEvaluationContentDescription
import com.example.binaryminification.presentation.calc.CalcViewModel
import com.example.binaryminification.ui.theme.HistoryScreen
import com.example.binaryminification.ui.theme.MenuScreen

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun CalcScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    calcViewModel: CalcViewModel = viewModel(),
) {
    val context = LocalContext.current
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
                        fontSize = 32.sp,
                        modifier = Modifier.semantics { invisibleToUser() }
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
                        .semantics {
                            contentDescription = getEvaluationContentDescription(context, state.input)
                        }
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    items(state.output) { pair ->
                        val style = when (pair.second) {
                            "title" -> titleTextStyle()
                            "subtitle" -> subtitleTextStyle()
                            "table" -> tableTextStyle()
                            else -> commonTextStyle()
                        }
                        Text(
                            text = pair.first,
                            style = style,
                            modifier = Modifier.semantics {
                                contentDescription = getEvaluationContentDescription(context, pair.first)
                            }
                        )
                    }
                }
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
                        val contextDescription = stringResource(id = R.string.var_a)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("A") }) {
                            Text(
                                text = "A", 
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.var_b)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("B") }) {
                            Text(
                                text = "B",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.var_c)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("C") }) {
                            Text(
                                text = "C",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.var_d)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("D") }) {
                            Text(
                                text = "D",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_or)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("∨") }) {
                            Text(
                                text = "∨",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_and)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("∧") }) {
                            Text(
                                text = "∧",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_not)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("¬") }) {
                            Text(
                                text = "¬",
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_implication)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("→") }) {
                            Text(
                                style = TextStyle(fontSize = 20.sp),
                                text = "→",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_equal)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("~") }) {
                            Text(
                                text = "~",
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_xor)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("⊕") }) {
                            Text(
                                text = "⊕",
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_nand)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("|") }) {
                            Text(
                                text = "|",
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                })
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_nor)
                        FloatingActionButton(onClick = { calcViewModel.onKeyboardButtonClick("↓") }) {
                            Text(
                                text = "↓",
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
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
                        val contextDescription = stringResource(id = R.string.symb_del)
                        FloatingActionButton(
                            onClick = { calcViewModel.onDeleteButtonClick() },
                            modifier = Modifier.combinedClickable(
                                onClick = { },
                                onLongClick = { calcViewModel.onClear() }
                            )
                        ) {
                            Text(
                                text = "Del",
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                    item {
                        val contextDescription = stringResource(id = R.string.symb_res)
                        FloatingActionButton(
                            onClick = { calcViewModel.onCalculateButtonClick() }
                        ) {
                            Text(
                                text = "=",
                                style = TextStyle(fontSize = 20.sp),
                                modifier = Modifier.semantics {
                                    this.contentDescription = contextDescription
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun commonTextStyle():TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = 18.sp,
        fontWeight = FontWeight.W400
    )
}
@Composable
private fun titleTextStyle():TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = 22.sp,
        fontWeight = FontWeight.W700
    )
}
@Composable
private fun subtitleTextStyle():TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = 19.sp,
        fontWeight = FontWeight.W500
    )
}

@Composable
private fun tableTextStyle():TextStyle {
    return TextStyle(
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = 19.sp,
        fontWeight = FontWeight.W400
    )
}




