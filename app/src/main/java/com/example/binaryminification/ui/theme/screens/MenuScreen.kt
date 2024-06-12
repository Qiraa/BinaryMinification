package com.example.binaryminification.ui.theme.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.binaryminification.R
import com.example.binaryminification.ui.theme.CalcScreen
import com.example.binaryminification.ui.theme.InfoScreen
import com.example.binaryminification.ui.theme.utils.openUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(id = R.string.back),
                            modifier = Modifier.size(32.dp),
                            tint = Color.White,
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.menu),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontFamily = FontFamily(
                                Font(R.font.kalam_bold)
                            )
                        ),
                        fontSize = 24.sp,
                    )
                }
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = modifier
                    .padding(contentPadding)
                    .padding(vertical = 100.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 24.dp),
                    modifier = modifier
                        .width(300.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        val context = LocalContext.current
                        MenuItem(icon = Icons.Filled.MailOutline, text = R.string.feedback) {
                            context.openUrl("https://t.me/EkaterinaMinger")
                        }
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        MenuItem(icon = Icons.Filled.Info, text = R.string.information) {
                            navController.navigate(InfoScreen.route())
                        }
                    }
                }
            }
            val activity = (LocalContext.current as? Activity)
            FilledTonalButton(
                onClick = { activity?.finish() },
                shape = RoundedCornerShape(8),
                modifier = Modifier
                    .shadow(4.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults
                    .buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.ExitToApp,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.tertiary,
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = stringResource(id = R.string.exit),
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }
    }
}

@Composable
private fun MenuItem(
    icon: ImageVector,
    text: Int,
    onClick: () -> Unit = { },
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.size(24.dp),
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = stringResource(id = text),
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 24.sp,
        )
    }
}

