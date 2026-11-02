package com.sixtern.sixternapp.kaprodi

import android.graphics.Color as AndroidColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class KaprodiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = AndroidColor.TRANSPARENT
        window.navigationBarColor = AndroidColor.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        setContent {
            MaterialTheme {
                KaprodiMainScreen(onLogout = { finish() })
            }
        }
    }
}

@Composable
fun KaprodiMainScreen(onLogout: () -> Unit) {
    var tab by rememberSaveable { mutableStateOf(0) }
    var showProfil by rememberSaveable { mutableStateOf(false) }
    val titles = listOf("Beranda", "Validasi Sks", "Pengesahan", "Rekap Nilai")

    BackHandler(enabled = showProfil) { showProfil = false }

    Column(Modifier.fillMaxSize().background(KColor.Background)) {
        if (showProfil) {
            KaprodiHeader(backTitle = "Profil Pengaturan Kaprodi", onBack = { showProfil = false })
            KaprodiProfilScreen(onLogout = onLogout, modifier = Modifier.weight(1f))
        } else {
            KaprodiHeader(subtitle = titles[tab], onProfileClick = { showProfil = true })
            Box(Modifier.weight(1f)) {
                when (tab) {
                    0 -> KaprodiBerandaScreen(onGoValidasi = { tab = 1 }, onGoPengesahan = { tab = 2 })
                    1 -> KaprodiValidasiScreen()
                    2 -> KaprodiPengesahanScreen()
                    else -> Box(Modifier.fillMaxSize())
                }
            }
            KaprodiBottomBar(selected = tab, onSelect = { if (it != 3) tab = it })
        }
    }
}
