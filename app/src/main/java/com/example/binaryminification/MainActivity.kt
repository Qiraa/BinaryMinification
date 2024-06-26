package com.example.binaryminification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.binaryminification.ui.theme.BinaryMinificationTheme
import com.example.binaryminification.ui.theme.Grape
import com.example.binaryminification.ui.theme.SetupNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BinaryMinificationTheme {
                Surface {
                    SetupNavigation()
                }
            }
        }
    }
}

