package com.sixtern.sixternapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.ui.components.*
import com.sixtern.sixternapp.ui.theme.*

enum class LogStatus { Menunggu, Revisi, Disetujui }

data class LogEntry(
    val date: String,
    val title: String,
    val hours: String,
    val status: LogStatus,
    val description: String? = null,
    val attachment: String? = null,
    val mentorName: String? = null,
    val mentorNote: String? = null,
    val validation: String? = null,
    val score: String? = null
)

private val sampleLogs = listOf(
    LogEntry(
        "Hari ini, 24 Okt 2024", "Perancangan UI High-Fidelity & User Flow", "8 Jam Kerja (08.30 – 17.00)",
        LogStatus.Menunggu,
        description = "Menyelesaikan desain modul evaluasi dan melakukan sync token warna ke figma design system SIXTERN.",
        attachment = "Figma_Prototype.fig"
    ),
    LogEntry(
        "Kemarin, 23 Okt 2024", "Benchmarking Riset Kompetensi", "7.5 Jam Kerja", LogStatus.Revisi,
        mentorName = "Hendra Gunawan",
        mentorNote = "Mohon tambahkan bukti foto dokumentasi wawancara user dan lampiran resume temuan kompetensi ya."
    ),
    LogEntry(
        "Selasa, 22 Okt 2024", "Presentasi Sprint Review 3 Bimbingan", "8 Jam Kerja", LogStatus.Disetujui,
        description = "Pemaparan progres sprint weekly bersama mentor lapangan PT Digitefa dan sinkronisasi bobot penilaian konversi SKS.",
        validation = "Mentor Industri & Dosen Pembimbing", score = "100 / 100"
    )
)

@Composable
fun LogbookScreen(modifier: Modifier = Modifier) {
    var filter by remember { mutableStateOf<LogStatus?>(null) }
    val list = sampleLogs.filter { filter == null || it.status == filter }

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SxCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(10.dp).clip(CircleShape).background(Green))
                Spacer(Modifier.width(10.dp))
                Text("Presensi Kerja Aktif", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                SxChip("Geofence Valid", GreenSoft, GreenDark)
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Check-in Masuk", color = TextGray, fontSize = 13.sp)
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("08:24", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
                        Text(" WIB", color = TextGray, modifier = Modifier.padding(bottom = 4.dp))
                    }
                }
                SxButton("Presensi Pulang", Icons.AutoMirrored.Outlined.ExitToApp, container = BlueSoft, content = Navy, height = 44.dp)
            }
        }

        SxButton("Tulis Logbook Baru", Icons.Outlined.AddCircleOutline, Modifier.fillMaxWidth(), height = 56.dp)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip("Semua", "58", filter == null) { filter = null }
            FilterChip("Disetujui", "54", filter == LogStatus.Disetujui) { filter = LogStatus.Disetujui }
            FilterChip("Perlu Revisi", null, filter == LogStatus.Revisi, badge = "1") { filter = LogStatus.Revisi }
        }

        SectionTitle("Riwayat Aktivitas", "Oktober 2024")
        list.forEach { LogCard(it) }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun FilterChip(label: String, count: String?, selected: Boolean, badge: String? = null, onClick: () -> Unit) {
    Row(
        Modifier.clip(CircleShape)
            .background(if (selected) Navy else Color.White)
            .border(1.dp, if (selected) Navy else Divider, CircleShape)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = if (selected) Color.White else TextDark, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        if (count != null) {
            Spacer(Modifier.width(6.dp))
            Text(count, color = if (selected) Color.White.copy(0.85f) else TextGray, fontSize = 14.sp)
        }
        if (badge != null) {
            Spacer(Modifier.width(6.dp))
            Box(Modifier.size(20.dp).clip(CircleShape).background(Red), contentAlignment = Alignment.Center) {
                Text(badge, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun LogCard(e: LogEntry) {
    SxCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(e.date, color = TextGray, fontSize = 13.sp, modifier = Modifier.weight(1f))
            when (e.status) {
                LogStatus.Menunggu -> SxChip("Menunggu Review", BlueSoft, Navy, icon = Icons.Outlined.HourglassTop)
                LogStatus.Revisi -> SxChip("Perlu Revisi", RedSoft, Red, dot = true)
                LogStatus.Disetujui -> SxChip("Disetujui", GreenSoft, GreenDark, icon = Icons.Outlined.Check)
            }
        }
        Spacer(Modifier.height(10.dp))
        Text(e.title, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.Schedule, null, tint = TextGray, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text(e.hours, color = TextGray, fontSize = 14.sp)
        }
        e.description?.let {
            Spacer(Modifier.height(10.dp))
            Text(it, color = TextGray, fontSize = 14.sp, lineHeight = 21.sp)
        }
        e.attachment?.let {
            Spacer(Modifier.height(14.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    Modifier.clip(RoundedCornerShape(12.dp)).background(BlueSoft).padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.AttachFile, null, tint = Navy, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(it, color = Navy, fontSize = 14.sp)
                }
                Spacer(Modifier.weight(1f))
                Box(Modifier.size(40.dp).clip(CircleShape).background(BlueSoft), contentAlignment = Alignment.Center) {
                    Icon(Icons.Filled.KeyboardArrowRight, null, tint = Navy)
                }
            }
        }
        e.mentorNote?.let {
            Spacer(Modifier.height(14.dp))
            Column(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(RedSoft)
                    .border(1.dp, Color(0xFFFAD4D4), RoundedCornerShape(16.dp)).padding(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.RateReview, null, tint = Red, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Catatan Mentor: ${e.mentorName}", color = Red, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(Modifier.height(6.dp))
                Text("“$it”", fontStyle = FontStyle.Italic, fontSize = 14.sp, lineHeight = 21.sp)
            }
            Spacer(Modifier.height(14.dp))
            SxButton("Perbaiki Entri Sekarang", Icons.Outlined.Edit, Modifier.fillMaxWidth(), height = 50.dp)
        }
        e.validation?.let {
            Spacer(Modifier.height(14.dp))
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color(0xFFEFFAF4))
                    .border(1.dp, Color(0xFFCFEFDD), RoundedCornerShape(16.dp)).padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.CheckCircle, null, tint = Green, modifier = Modifier.size(32.dp))
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Tervalidasi Ganda", fontWeight = FontWeight.Bold)
                    Text(it, color = TextGray, fontSize = 12.sp)
                }
                Text(e.score ?: "", color = GreenDark, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
            }
        }
    }
}
