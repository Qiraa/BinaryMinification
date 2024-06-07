package com.example.binaryminification.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.binaryminification.data.OnBoardingManager
import com.example.binaryminification.ui.theme.CalcScreen
import com.example.binaryminification.ui.theme.Grape
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val onboardingManager = OnBoardingManager(LocalContext.current)
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.main_background),
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomCenter,
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = { 3 })
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f, fill = true),
                contentAlignment = Alignment.Center,
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> Page(
                            textId = R.string.welcome,
                            buttonTextId = R.string.next,
                            skipTextId = R.string.skip,
                            pageCount = pagerState.pageCount,
                            currentPageIndex = page,
                            onNextClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(1)
                                }
                            },
                            onSkipClick = {
                                onboardingManager.setOnBoardingShown()
                                navController.navigate(CalcScreen.route())
                            }
                        )
                        1 -> Page(
                            textId = R.string.welcome,
                            buttonTextId = R.string.next,
                            skipTextId = R.string.skip,
                            pageCount = pagerState.pageCount,
                            currentPageIndex = page,
                            onNextClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(2)
                                }
                            },
                            onSkipClick = {
                                onboardingManager.setOnBoardingShown()
                                navController.navigate(CalcScreen.route())
                            }
                        )
                        2 -> Page(
                            textId = R.string.welcome,
                            buttonTextId = R.string.next,
                            skipTextId = R.string.skip,
                            pageCount = pagerState.pageCount,
                            currentPageIndex = page,
                            onNextClick = {
                                onboardingManager.setOnBoardingShown()
                                navController.navigate(CalcScreen.route())
                            },
                            onSkipClick = {
                                onboardingManager.setOnBoardingShown()
                                navController.navigate(CalcScreen.route())
                            }
                        )
                        else -> { }
                    }
                }
            }
        }
    }
}

@Composable
private fun Page(
    textId: Int,
    buttonTextId: Int,
    skipTextId: Int,
    pageCount: Int,
    currentPageIndex: Int,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
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
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.kalam_bold))
                    ),
                    color = Color.White,
                    fontSize = 32.sp,
                )
                Text(
                    text = stringResource(id = textId),
                    color = Color.Yellow,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.kalam_bold)
                        )
                    ),
                    fontSize = 32.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row {
                    repeat(pageCount) { index ->
                        val selectedBrush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFE5E6EC),
                                Color(0xFF6700FF),
                            ),
                        )
                        val unselectedBrush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFE5E6EC),
                                Color(0xFFFF9900),
                            ),
                        )
                        val brush = if (currentPageIndex == index) selectedBrush else unselectedBrush
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(brush)
                                .size(16.dp),
                        )
                    }
                }
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                ) {
                    Text(text = stringResource(id = buttonTextId))
                }
                TextButton(onClick = onSkipClick) {
                    Text(text = stringResource(id = skipTextId), color = Grape)
                }
            }
        }
    }
}
