package com.sixtern.sixternapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Navy = Color(0xFF00266E)
val BgScreen = Color(0xFFF8F9FC)
val TextDark = Color(0xFF111827)
val TextGray = Color(0xFF6B7280)
val Green = Color(0xFF0E9F6E)
val GreenDark = Color(0xFF047857)
val GreenSoft = Color(0xFFE3F8EC)
val Red = Color(0xFFD92D20)
val RedSoft = Color(0xFFFEF0F0)
val BlueSoft = Color(0xFFEAF1FE)
val GraySoft = Color(0xFFF1F3F7)
val Divider = Color(0xFFE5E7EB)

private val Scheme = lightColorScheme(
    primary = Navy,
    onPrimary = Color.White,
    background = BgScreen,
    surface = Color.White,
    onSurface = TextDark
)

@Composable
fun SixternTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Scheme, content = content)
}
