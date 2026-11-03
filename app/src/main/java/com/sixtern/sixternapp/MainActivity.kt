package com.sixtern.sixternapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sixtern.sixternapp.ui.main.MainScreen
import com.sixtern.sixternapp.ui.splash.SplashScreen
import com.sixtern.sixternapp.ui.theme.SIXTERNAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SIXTERNAPPTheme {
                var showSplash by remember { mutableStateOf(value = true) }
                Crossfade(
                    targetState = showSplash,
                    animationSpec = tween(durationMillis = 800),
                    label = "SplashToMainTransition",
                ) { isSplash ->
                    if (isSplash) {
                        SplashScreen(
                            onSplashFinished = {
                                showSplash = false
                            },
                        )
                    } else {
                        MainScreen()
                    }
                }
            }
        }
    }
}