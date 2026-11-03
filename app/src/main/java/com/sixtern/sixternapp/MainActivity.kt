package com.sixtern.sixternapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sixtern.sixternapp.ui.main.MentorBeranda
import com.sixtern.sixternapp.ui.theme.SIXTERNAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SIXTERNAPPTheme {
                MentorBeranda()
            }
        }
    }
}
