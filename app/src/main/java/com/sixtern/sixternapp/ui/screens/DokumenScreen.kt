package com.sixtern.sixternapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.ui.components.*
import com.sixtern.sixternapp.ui.theme.*

@Composable
fun DokumenScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SxCard {
            Row {
                Text("STATUS KELENGKAPAN", color = TextGray, fontSize = 12.sp, letterSpacing = 1.sp, modifier = Modifier.weight(1f))
                SxChip("83%", GreenSoft, GreenDark)
            }
            Spacer(Modifier.height(6.dp))
            Text("5 dari 6\nDokumen\nTerverifikasi", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp, lineHeight = 34.sp)
            Spacer(Modifier.height(14.dp))
            SxProgress(5 / 6f, Green)
            Spacer(Modifier.height(14.dp))
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(RedSoft)
                    .border(1.dp, Color(0xFFFAD4D4), RoundedCornerShape(16.dp)).padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.ErrorOutline, null, tint = Red)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Tindakan Diperlukan", color = Red, fontWeight = FontWeight.Bold)
                    Text("Lembar Pengesahan Tri-Partit belum tuntas.", fontSize = 14.sp)
                }
            }
        }

        SxButton("+ Unggah Dokumen Baru", Icons.Outlined.CloudUpload, Modifier.fillMaxWidth(), height = 56.dp)

        SectionTitle("Berkas Wajib & Evaluasi", "Semester Ganjil 2025/2026")

        DocCard("Surat Penerimaan Magang (LoA)", "PDF • 1.4 MB • Diunggah 12 Agu", BlueSoft, Navy) { ViewDownload() }
        DocCard("Surat Tugas & Pakta Integritas", "PDF • 890 KB • Sah oleh Dosen PA", BlueSoft, Navy) { ViewDownload() }
        DocCard("Laporan Kemajuan Mid-Term", "DOCX • 3.2 MB • Revisi Ke-1 Disetujui", GreenSoft, Green,
            badge = { SxChip("Disetujui", GreenSoft, GreenDark, icon = Icons.Outlined.Check) }) { ViewDownload() }
        DocCard("Draft Laporan Akhir Magang", "Turnitin 11% (Ambang aman < 20%)", BlueSoft, Navy,
            badge = { SxChip("Revisi Tersedia", GraySoft, TextGray) }) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                SxButton("Unggah Revisi", Icons.Outlined.Refresh, container = BlueSoft, content = Navy, height = 42.dp)
            }
        }
        DocCard("Pengesahan Tri-Partit MBKM", "Menunggu TTD Kaprodi Informatika", RedSoft, Red,
            icon = Icons.Outlined.Edit, subtitleColor = Red,
            badge = { SxChip("Perlu Tindakan", RedSoft, Red, dot = true) }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Diajukan 3 hari lalu", color = TextGray, fontSize = 13.sp, modifier = Modifier.weight(1f))
                SxButton("Kirim Pengingat", Icons.AutoMirrored.Outlined.Send, height = 42.dp)
            }
        }

        // Terkunci
        Column(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(Color.White.copy(alpha = 0.5f))
                .border(1.dp, Divider, RoundedCornerShape(24.dp)).padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                IconBox(Icons.Outlined.Lock, GraySoft, TextGray)
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text("Sertifikat Kelulusan Magang", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Penerbitan otomatis oleh Mitra & Dekanat", color = TextGray, fontSize = 13.sp)
                }
                SxChip("Terkunci", GraySoft, TextGray)
            }
            Spacer(Modifier.height(12.dp))
            Text(
                "Berkas ini otomatis dapat diunduh setelah seluruh sidang evaluasi dan penetapan yudisium program disahkan.",
                color = TextGray, fontSize = 14.sp, lineHeight = 21.sp,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(Color.White).padding(14.dp)
            )
        }

        SectionTitle("Unggah Berkas Tambahan")
        Column(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(Color.White)
                .border(1.5.dp, Color(0xFFB9CCF2), RoundedCornerShape(24.dp)).padding(vertical = 32.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconBox(Icons.Outlined.FileUpload, BlueSoft, Navy, size = 64.dp, shape = CircleShape)
            Spacer(Modifier.height(14.dp))
            Text("Sentuh untuk pilih file dari ponsel", fontWeight = FontWeight.Bold)
            Text("Mendukung format PDF atau DOCX (Maksimal 20 MB)", color = TextGray, fontSize = 13.sp, textAlign = TextAlign.Center)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.CloudQueue, null, tint = TextGray, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("Penyimpanan Berkas Magang  ", color = TextGray, fontSize = 13.sp)
            Text("120 MB / 500 MB terpakai", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun ViewDownload() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
        SxButton("Lihat", Icons.Outlined.Visibility, container = GraySoft, content = TextDark, height = 40.dp)
        Spacer(Modifier.width(8.dp))
        SxButton("Unduh", Icons.Outlined.Download, container = GraySoft, content = TextDark, height = 40.dp)
    }
}

@Composable
private fun DocCard(
    title: String, subtitle: String, bg: Color, tint: Color,
    icon: ImageVector = Icons.AutoMirrored.Outlined.Assignment,
    subtitleColor: Color = TextGray,
    badge: (@Composable () -> Unit)? = null,
    actions: @Composable () -> Unit
) {
    SxCard(padding = 16.dp) {
        Row(verticalAlignment = Alignment.Top) {
            IconBox(icon, bg, tint)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(Modifier.height(2.dp))
                Text(subtitle, color = subtitleColor, fontSize = 13.sp)
            }
            if (badge != null) { Spacer(Modifier.width(8.dp)); badge() }
        }
        Spacer(Modifier.height(14.dp))
        actions()
    }
}
