
package com.sixtern.sixternapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.sixtern.sixternapp.ui.components.*
import com.sixtern.sixternapp.ui.screens.*
import com.sixtern.sixternapp.ui.theme.BgScreen
import com.sixtern.sixternapp.ui.theme.SixternTheme

class MahasiswaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { SixternTheme { SixternApp() } }
    }
}

@Composable
fun SixternApp() {
    var tab by rememberSaveable { mutableStateOf(Tab.Beranda) }
    var showProfile by rememberSaveable { mutableStateOf(false) }

    BackHandler(enabled = showProfile) {
        showProfile = false
    }

    if (showProfile) {
        ProfileScreen(onBack = { showProfile = false })
        return
    }

    Scaffold(
        containerColor = BgScreen,
        topBar = {
            SixternTopBar(onProfileClick = { showProfile = true })
        },
        bottomBar = {
            SixternBottomBar(
                selected = tab,
                onSelect = { tab = it }
            )
        }
    ) { padding ->
        val m = Modifier.padding(padding)

        when (tab) {
            Tab.Beranda -> HomeScreen(m, onOpenLogbook = { tab = Tab.Logbook })
            Tab.Logbook -> LogbookScreen(m)
            Tab.Evaluasi -> EvaluasiScreen(m)
            Tab.Dokumen -> DokumenScreen(m)
        }
    }
}
