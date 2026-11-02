package com.sixtern.sixternapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.automirrored.outlined.TrendingUp
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.ui.components.*
import com.sixtern.sixternapp.ui.theme.*

@Composable
fun EvaluasiScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Ringkasan transkrip
        SxCard {
            Row(verticalAlignment = Alignment.Top) {
                Text("HASIL EVALUASI MAGANG MBKM", color = TextGray, fontSize = 12.sp, letterSpacing = 1.sp, modifier = Modifier.weight(1f))
                SxChip("Resmi Tervalidasi", GreenSoft, GreenDark, dot = true)
            }
            Spacer(Modifier.height(6.dp))
            Text("Transkrip\nKumulatif", fontWeight = FontWeight.ExtraBold, fontSize = 28.sp, lineHeight = 34.sp)
            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(BgScreen)
                    .border(1.dp, Divider, RoundedCornerShape(20.dp)).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Rata-Rata Akhir", color = TextGray, fontSize = 14.sp)
                    Text(buildAnnotatedString {
                        withStyle(SpanStyle(color = Navy, fontWeight = FontWeight.ExtraBold, fontSize = 36.sp)) { append("94.45") }
                        withStyle(SpanStyle(color = TextGray, fontSize = 16.sp)) { append(" /100") }
                    })
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SxChip("Grade A", Navy, Color.White)
                    Spacer(Modifier.height(4.dp))
                    Text("Sangat Memuaskan", color = TextGray, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.CheckCircle, null, tint = Green, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Kelayakan 20 SKS Penuh", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text("TERPENUHI (100%)", color = GreenDark, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
            Spacer(Modifier.height(8.dp))
            SxProgress(1f, Green)
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Check, null, tint = Green, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text("Nilai di atas ambang batas prodi (Min. 80.0)", color = TextGray, fontSize = 13.sp)
            }
        }

        SectionTitle("Penilaian Tri-Partit", "3 Komponen Resmi")

        ScoreCard(Icons.Outlined.Work, BlueSoft, Navy, "Pembimbing Lapangan", "Bobot Evaluasi: 50%", "94.5", "Grade A",
            listOf("Disiplin kerja industri & ketepatan target sprint", "Inisiatif tinggi & integrasi tim lintas divisi", "Hasil kerja riil: 4 modul design system rampung"))
        ScoreCard(Icons.Outlined.School, GreenSoft, Green, "Dosen Pembimbing Kampus", "Bobot Evaluasi: 30%", "92.0", "Grade A",
            listOf("Ketercapaian Capaian Pembelajaran Lulusan (CPL)", "Konsistensi bimbingan berkala & laporan kemajuan"))
        ScoreCard(Icons.Outlined.Schedule, GraySoft, TextGray, "Presensi & Logbook Sistem", "Bobot Evaluasi: 20%", "98.0", "Grade A+",
            listOf("Validasi radius Geofence GPS presensi: 100% tepat", "Kepatuhan submit logbook harian: 90 dari 90 hari"))

        // Capaian kompetensi
        SxCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.AutoMirrored.Outlined.TrendingUp, null, tint = Navy, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("Capaian Kompetensi", fontWeight = FontWeight.Bold, fontSize = 17.sp, modifier = Modifier.weight(1f))
                Text("Memenuhi Target", color = GreenDark, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(16.dp))
            Competency("Keterampilan Teknis & Desain UI/UX", 96, Navy)
            Spacer(Modifier.height(14.dp))
            Competency("Komunikasi & Kolaborasi Tim", 95, Green)
            Spacer(Modifier.height(14.dp))
            Competency("Pemecahan Masalah & Problem Solving", 92, Color(0xFF4B5563))
        }

        // Testimoni mentor
        SxCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                InitialAvatar("BH", bg = BlueSoft, fg = Navy)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Bambang Hidayat, S.Ds.", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Lead Product Designer • PT Astra Graphia", color = TextGray, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(14.dp))
            Row(Modifier.height(IntrinsicSize.Min)) {
                Box(Modifier.width(3.dp).fillMaxHeight().background(Navy))
                Spacer(Modifier.width(14.dp))
                Text(
                    "“Anindya menunjukkan etos kerja yang sangat tinggi dan adaptif. Mampu mengeksekusi kebutuhan riset pengguna menjadi prototipe presisi tinggi dengan cepat.”",
                    fontStyle = FontStyle.Italic, color = TextGray, fontSize = 14.sp, lineHeight = 22.sp
                )
            }
        }

        // Aksi
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(96.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Navy)
        ) {
            Icon(Icons.Outlined.Download, null, modifier = Modifier.size(40.dp))
            Spacer(Modifier.weight(1f))
            Text("Unduh Transkrip Nilai\nResmi (PDF)", fontWeight = FontWeight.Bold, fontSize = 17.sp, textAlign = TextAlign.End)
        }
        SxButton("Kirim Lembar Pengesahan ke Kaprodi", Icons.AutoMirrored.Outlined.Send,
            Modifier.fillMaxWidth(), container = Color(0xFFD1F0DF), content = GreenDark, height = 56.dp)
        Text(
            "Dokumen ditandatangani secara digital dengan sertifikat resmi SIXTERN & Lembaga Akreditasi Kampus.",
            color = TextGray, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        )
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun ScoreCard(
    icon: ImageVector, bg: Color, tint: Color, title: String, weight: String,
    score: String, grade: String, points: List<String>
) {
    SxCard {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBox(icon, bg, tint)
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(weight, color = TextGray, fontSize = 13.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(score, color = Navy, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                Text(grade, color = TextGray, fontSize = 12.sp)
            }
        }
        Spacer(Modifier.height(12.dp))
        Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(BgScreen).padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            points.forEach { Text("•  $it", color = TextGray, fontSize = 14.sp, lineHeight = 20.sp) }
        }
    }
}

@Composable
private fun Competency(label: String, value: Int, color: Color) {
    Row { Text(label, color = TextGray, fontSize = 14.sp, modifier = Modifier.weight(1f)); Text("$value%", fontWeight = FontWeight.Bold) }
    Spacer(Modifier.height(6.dp))
    SxProgress(value / 100f, color, height = 8.dp)
}
