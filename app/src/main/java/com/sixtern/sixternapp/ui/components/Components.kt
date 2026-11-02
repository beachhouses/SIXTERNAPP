
package com.sixtern.sixternapp.ui.components

import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.ui.theme.*

@Composable
fun SixternTopBar(onProfileClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Navy)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.School, null, tint = Color.White)
        }

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(
                "SIXTERN",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF34D399))
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    "Mahasiswa Aktif",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 13.sp
                )
            }
        }

        Box(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Notifications, null, tint = Color.White)

            Box(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 9.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEF4444))
            )
        }

        Spacer(Modifier.width(12.dp))

        Box(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onProfileClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Outlined.Person, null, tint = Navy)
        }
    }
}

@Composable
fun SxCard(
    modifier: Modifier = Modifier,
    container: Color = Color.White,
    border: BorderStroke? = null,
    padding: Dp = 20.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = container),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = border
    ) {
        Column(Modifier.padding(padding), content = content)
    }
}

@Composable
fun IconBox(
    icon: ImageVector,
    bg: Color,
    tint: Color,
    size: Dp = 48.dp,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(14.dp)
) {
    Box(
        Modifier
            .size(size)
            .clip(shape)
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            icon,
            null,
            tint = tint,
            modifier = Modifier.size(size * 0.5f)
        )
    }
}

@Composable
fun SxChip(
    text: String,
    bg: Color,
    fg: Color,
    modifier: Modifier = Modifier,
    dot: Boolean = false,
    icon: ImageVector? = null,
    border: Color? = null
) {
    Row(
        modifier
            .clip(CircleShape)
            .background(bg)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (dot) {
            Box(
                Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(fg)
            )
            Spacer(Modifier.width(6.dp))
        }

        if (icon != null) {
            Icon(icon, null, tint = fg, modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(4.dp))
        }

        Text(
            text,
            color = fg,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun SxButton(
    text: String,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    container: Color = Navy,
    content: Color = Color.White,
    height: Dp = 52.dp,
    trailingIcon: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = container,
            contentColor = content
        ),
        contentPadding = PaddingValues(horizontal = 18.dp)
    ) {
        if (icon != null && !trailingIcon) {
            Icon(icon, null, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
        }

        Text(text, fontWeight = FontWeight.Bold, fontSize = 15.sp)

        if (icon != null && trailingIcon) {
            Spacer(Modifier.width(8.dp))
            Icon(icon, null, modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
fun SxProgress(
    progress: Float,
    color: Color = Navy,
    track: Color = GraySoft,
    height: Dp = 8.dp
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(track)
    ) {
        Box(
            Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .fillMaxHeight()
                .clip(CircleShape)
                .background(color)
        )
    }
}

@Composable
fun SectionTitle(text: String, trailing: String? = null) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )

        if (trailing != null) {
            Text(trailing, color = TextGray, fontSize = 13.sp)
        }
    }
}

@Composable
fun InitialAvatar(
    initials: String,
    size: Dp = 52.dp,
    bg: Color = GreenSoft,
    fg: Color = GreenDark
) {
    Box(
        Modifier
            .size(size)
            .clip(CircleShape)
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Text(
            initials,
            color = fg,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    }
}

enum class Tab(val label: String, val icon: ImageVector) {
    Beranda("Beranda", Icons.Outlined.Home),
    Logbook("Logbook", Icons.AutoMirrored.Outlined.MenuBook),
    Evaluasi("Evaluasi", Icons.AutoMirrored.Outlined.FactCheck),
    Dokumen("Dokumen", Icons.Outlined.Folder)
}

@Composable
fun SixternBottomBar(selected: Tab, onSelect: (Tab) -> Unit) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        Tab.entries.forEach { tab ->
            NavigationBarItem(
                selected = tab == selected,
                onClick = { onSelect(tab) },
                icon = { Icon(tab.icon, null) },
                label = {
                    Text(
                        tab.label,
                        fontSize = 12.sp,
                        fontWeight = if (tab == selected)
                            FontWeight.Bold
                        else
                            FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Navy,
                    selectedTextColor = Navy,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = TextGray,
                    unselectedTextColor = TextGray
                )
            )
        }
    }
}
