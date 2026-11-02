package com.sixtern.sixternapp.kaprodi

import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

/** Color palette Kaprodi (diambil dari desain Figma) */
object KColor {
    val Navy = Color(0xFF002B87)
    val NavyLight = Color(0xFF1B3C96)
    val BlueMid = Color(0xFF0B5CAD)
    val Background = Color(0xFFFBF9F7)
    val Border = Color(0xFFEAE6E4)
    val TextPrimary = Color(0xFF1B1B1F)
    val TextSecondary = Color(0xFF5F6368)
    val TextMuted = Color(0xFF9AA0A6)
    val ChipBlue = Color(0xFFE6EDF5)
    val ChipGray = Color(0xFFECECEE)
    val SoftRow = Color(0xFFF6F5F8)
    val Lavender = Color(0xFFE3E8F7)
    val Red = Color(0xFFD32F2F)
    val RedText = Color(0xFFB3261E)
    val RedSoft = Color(0xFFFDE7E7)
    val Maroon = Color(0xFF4A1530)
    val LavenderChip = Color(0xFFEDE7F6)
    val Green = Color(0xFF3B7D4F)
    val GreenSoft = Color(0xFFD7EFD9)
    val DarkGray = Color(0xFF4F4A52)
}

@Composable
fun KaprodiHeader(
    subtitle: String = "",
    onProfileClick: () -> Unit = {},
    backTitle: String? = null,
    onBack: () -> Unit = {}
) {
    Box(Modifier.fillMaxWidth().background(KColor.Navy)) {
        Row(
            Modifier.statusBarsPadding().padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (backTitle != null) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = Color.White,
                    modifier = Modifier.size(24.dp).clickable(onClick = onBack)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    backTitle, color = Color.White, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f)
                )
            } else {
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("SIXTERN", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(8.dp))
                        KChip("Kaprodi", bg = Color(0xFFE3ECF6), fg = KColor.Navy)
                    }
                    Text(subtitle, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
            }
            Box(
                Modifier.size(40.dp).clip(CircleShape).background(KColor.NavyLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Notifications, "Notifikasi", tint = Color.White, modifier = Modifier.size(20.dp))
                Box(
                    Modifier.align(Alignment.TopEnd).padding(top = 9.dp, end = 9.dp)
                        .size(8.dp).clip(CircleShape).background(KColor.Red)
                )
            }
            Spacer(Modifier.width(10.dp))
            Box(
                Modifier.size(40.dp).clip(CircleShape).background(Color.White).clickable(onClick = onProfileClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Person, "Profil", tint = KColor.Navy, modifier = Modifier.size(22.dp))
            }
        }
    }
}

@Composable
fun KaprodiBottomBar(selected: Int, onSelect: (Int) -> Unit) {
    val items = listOf(
        "Beranda" to Icons.Filled.Home,
        "Validasi SKS" to Icons.Filled.Check,
        "Pengesahan" to Icons.Filled.Edit,
        "Rekap Nilai" to Icons.Filled.List
    )
    Surface(color = Color.White, shadowElevation = 8.dp) {
        Row(Modifier.fillMaxWidth().navigationBarsPadding().padding(vertical = 8.dp)) {
            items.forEachIndexed { i, (label, icon) ->
                val c = if (i == selected) KColor.Navy else KColor.TextMuted
                Column(
                    Modifier.weight(1f).clickable { onSelect(i) }.padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(icon, label, tint = c, modifier = Modifier.size(24.dp))
                    Text(
                        label, color = c, fontSize = 11.sp,
                        fontWeight = if (i == selected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun KCard(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = color,
        border = BorderStroke(1.dp, KColor.Border)
    ) {
        Column(content = content)
    }
}

@Composable
fun KChip(
    text: String,
    modifier: Modifier = Modifier,
    bg: Color = KColor.ChipBlue,
    fg: Color = KColor.TextPrimary,
    fontSize: Int = 11
) {
    Box(
        modifier.clip(RoundedCornerShape(50)).background(bg).padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(text, color = fg, fontSize = fontSize.sp, fontWeight = FontWeight.Medium, maxLines = 1)
    }
}

@Composable
fun KAvatar(
    initials: String,
    size: Dp = 48.dp,
    bg: Color = KColor.NavyLight,
    @DrawableRes photo: Int? = null
) {
    if (photo != null) {
        Image(
            painter = painterResource(id = photo),
            contentDescription = initials,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(size).clip(CircleShape)
        )
    } else {
        Box(
            Modifier.size(size).clip(CircleShape).background(bg),
            contentAlignment = Alignment.Center
        ) {
            Text(initials, color = Color.White, fontWeight = FontWeight.Bold, fontSize = (size.value / 3).sp)
        }
    }
}

@Composable
fun KButton(
    text: String,
    modifier: Modifier = Modifier,
    filled: Boolean = true,
    icon: ImageVector? = null,
    bg: Color? = null,
    fg: Color? = null,
    onClick: () -> Unit = {}
) {
    val background = bg ?: if (filled) KColor.Navy else KColor.ChipGray
    val foreground = fg ?: if (filled) Color.White else KColor.TextPrimary
    Row(
        modifier.clip(RoundedCornerShape(50)).background(background).clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(icon, null, tint = foreground, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
        }
        Text(text, color = foreground, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
    }
}

@Composable
fun SectionTitle(left: String, right: String, rightColor: Color = KColor.TextSecondary) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(left, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
        Text(right, fontSize = 12.sp, color = rightColor)
    }
}

@Composable
fun KProgress(fraction: Float, modifier: Modifier = Modifier, color: Color = KColor.Navy, track: Color = KColor.ChipBlue) {
    Box(modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(50)).background(track)) {
        Box(Modifier.fillMaxWidth(fraction).fillMaxHeight().clip(RoundedCornerShape(50)).background(color))
    }
}

@Composable
fun PlaceholderScreen(text: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text, color = KColor.TextSecondary, fontSize = 14.sp)
    }
}
