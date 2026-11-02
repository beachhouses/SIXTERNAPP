package com.sixtern.sixternapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.ui.components.*
import com.sixtern.sixternapp.ui.theme.*

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onOpenLogbook: () -> Unit = {}) {
    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Sapaan + progres
        SxCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Halo, Anindya! 👋", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, modifier = Modifier.weight(1f))
                SxChip("Aktif", GreenSoft, GreenDark, dot = true)
            }
            Text("PT Telkom Indonesia · UI/UX Intern", color = TextGray, fontSize = 15.sp)
            Spacer(Modifier.height(18.dp))
            Row {
                Text("Progres Magang", color = TextGray, modifier = Modifier.weight(1f))
                Text(buildAnnotatedString {
                    withStyle(SpanStyle(color = Navy, fontWeight = FontWeight.Bold)) { append("Minggu 12") }
                    append(" dari 24 (50%)")
                }, color = TextGray)
            }
            Spacer(Modifier.height(8.dp))
            SxProgress(0.5f)
        }

        // Presensi
        SxCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconBox(Icons.Outlined.CalendarMonth, GreenSoft, Green)
                Spacer(Modifier.width(16.dp))
                Text("Kamis, 24 Okt", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.weight(1f))
                Text("08:30 - 17:30 WIB", color = TextGray, fontSize = 13.sp)
            }
            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth().clip(CircleShape).background(GraySoft).padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(10.dp).clip(CircleShape).background(Green))
                Spacer(Modifier.width(10.dp))
                Text("Dalam Radius Kantor (Telkom Landmark)", fontSize = 14.sp)
            }
            Spacer(Modifier.height(14.dp))
            SxButton("Presensi Masuk Sekarang", Icons.Outlined.PhotoCamera, Modifier.fillMaxWidth(), height = 56.dp)
        }

        // Banner logbook
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(BlueSoft)
                .border(1.dp, Color(0xFFD6E4FB), RoundedCornerShape(24.dp)).padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(56.dp).clip(CircleShape).background(Navy), contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.Timer, null, tint = Color.White, modifier = Modifier.size(28.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text("LOGBOOK HARIAN", color = Navy, fontWeight = FontWeight.Bold, fontSize = 12.sp, letterSpacing = 1.sp)
                Text("Tersisa 3 Jam lagi", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text("Batas: Hari ini pukul 21:00", color = TextGray, fontSize = 12.sp)
            }
            SxButton("Tulis", Icons.AutoMirrored.Outlined.ArrowForward, height = 44.dp, trailingIcon = true, onClick = onOpenLogbook)
        }

        // Ringkasan kinerja
        SectionTitle("Ringkasan Kinerja")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(Modifier.weight(1f), Icons.Outlined.Schedule, BlueSoft, Navy, "384", "/600", "Jam Magang")
            StatCard(Modifier.weight(1f), Icons.Outlined.TaskAlt, GreenSoft, Green, "98%", "", "Kehadiran")
            StatCard(Modifier.weight(1f), Icons.Outlined.Star, GraySoft, TextGray, "4.85", "/5", "Nilai Mentor")
        }

        // Akses cepat
        SectionTitle("Akses Cepat")
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            QuickAction(Icons.Outlined.Edit, BlueSoft, Navy, "Logbook\nHari Ini", onOpenLogbook)
            QuickAction(Icons.Outlined.EditCalendar, GraySoft, Navy, "Ajukan\nIzin")
            QuickAction(Icons.Outlined.ChatBubbleOutline, GreenSoft, Green, "Hubungi\nMentor")
            QuickAction(Icons.Outlined.Download, GraySoft, Navy, "Unduh\nBerkas")
        }

        // Umpan balik mentor
        SectionTitle("Umpan Balik Mentor", "Kemarin")
        SxCard {
            Text(
                "“ Kerja bagus untuk sprint minggu ini, teruskan kerapian dokumentasinya.”",
                fontStyle = FontStyle.Italic, fontSize = 17.sp, lineHeight = 26.sp
            )
            Spacer(Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                InitialAvatar("BH")
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Bambang Hidayat", fontWeight = FontWeight.Bold)
                    Text("Field Mentor · Lead Product Designer", color = TextGray, fontSize = 12.sp)
                }
                SxChip("Review Mingguan", GraySoft, TextDark)
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun StatCard(
    modifier: Modifier, icon: ImageVector, bg: Color, tint: Color,
    value: String, suffix: String, label: String
) {
    SxCard(modifier, padding = 14.dp) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            IconBox(icon, bg, tint, size = 40.dp, shape = RoundedCornerShape(12.dp))
            Spacer(Modifier.height(10.dp))
            Text(buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)) { append(value) }
                withStyle(SpanStyle(color = TextGray, fontSize = 14.sp)) { append(suffix) }
            })
            Text(label, color = TextGray, fontSize = 12.sp)
        }
    }
}

@Composable
private fun QuickAction(icon: ImageVector, bg: Color, tint: Color, label: String, onClick: () -> Unit = {}) {
    Column(
        Modifier.width(76.dp).clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.size(64.dp).clip(RoundedCornerShape(20.dp)).background(Color.White)
                .border(1.dp, Divider, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) { IconBox(icon, bg, tint, size = 44.dp, shape = RoundedCornerShape(14.dp)) }
        Spacer(Modifier.height(8.dp))
        Text(label, fontSize = 12.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center, lineHeight = 16.sp)
    }
}
