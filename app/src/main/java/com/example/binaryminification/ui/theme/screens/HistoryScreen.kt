package com.example.binaryminification.ui.theme.screens

import android.text.format.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.binaryminification.presentation.history.HistoryItem
import com.example.binaryminification.presentation.history.HistoryViewModel
import com.example.binaryminification.ui.theme.CalcScreen
import java.util.Date

@Composable
fun HistoryScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    historyViewModel: HistoryViewModel = viewModel(),
) {
    var showDialog by remember { mutableStateOf(false) }
    val state by historyViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            HistoryTopBar(
                onNavigationIconClick = { navController.navigate(CalcScreen.route()) },
                onClearRequest = { showDialog = true }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        if (state.items.isNotEmpty()) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(state.items) { item ->
                    HistoryItem(
                        item = item,
                        onItemClick = { historyViewModel.changeExpandStatus(item.id) },
                    )
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.history),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(46.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.no_history),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    )
                }
            }
        }
    }
    if (showDialog) {
        ConfirmationDialog(
            onDismiss = { showDialog = false },
            onClear = {
                historyViewModel.clearHistory()
                showDialog = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryTopBar(
    onNavigationIconClick: () -> Unit,
    onClearRequest: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(id = R.string.calc_history),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.kalam_bold)
                    )
                ),
            )
        },
        actions = {
            IconButton(onClick = onClearRequest) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(id = R.string.history_clear),
                    tint = Color.White,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.back),
                    modifier = Modifier.size(32.dp),
                    tint = Color.White,
                )
            }
        }
    )
}

@Composable
private fun HistoryItem(
    item: HistoryItem,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onItemClick() }
    ) {
        ListItem(
            headlineContent = {
                Text(text = item.input)
            },
            supportingContent = {
                val dateString = DateFormat
                    .getDateFormat(LocalContext.current)
                    .format(Date(item.timestamp))
                Text(dateString)
            },
            trailingContent = {
                if (item.isExpanded) {
                    Image(Icons.Default.KeyboardArrowUp, contentDescription = null)
                } else {
                    Image(Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            },
        )
        if (item.isExpanded) {
            Text(
                text = item.output,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.tertiary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ConfirmationDialog(
    onDismiss: () -> Unit,
    onClear: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(id = R.string.history_clear_question))
        },
        text = {
            Text(text = stringResource(id = R.string.history_clear_description))
        },
        confirmButton = {
            Button(onClick = onClear) {
                Text(text = stringResource(id = R.string.history_clear_button))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(id = android.R.string.cancel))
            }
        }
    )
}