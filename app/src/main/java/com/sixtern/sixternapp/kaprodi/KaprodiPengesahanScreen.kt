package com.sixtern.sixternapp.kaprodi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KaprodiPengesahanScreen(modifier: Modifier = Modifier) {
    var tab by remember { mutableStateOf(0) }

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Judul + status BSrE
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Pengesahan Berkas", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
                Text("Verifikasi & Tanda Tangan Elektronik Tri-Partit", fontSize = 12.sp, color = KColor.TextSecondary, lineHeight = 16.sp)
            }
            Spacer(Modifier.width(8.dp))
            Row(
                Modifier.clip(RoundedCornerShape(16.dp)).background(KColor.ChipBlue).padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(KColor.DarkGray))
                Spacer(Modifier.width(6.dp))
                Text("BSrE\nAktif", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary, lineHeight = 15.sp)
            }
        }

        // Tab
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(KColor.LavenderChip.copy(alpha = 0.5f))
                .border(1.dp, KColor.Border, RoundedCornerShape(14.dp)).padding(4.dp)
        ) {
            listOf("Perlu TTD" to "18", "Sudah Disahkan" to "42").forEachIndexed { i, (label, count) ->
                val selected = tab == i
                Row(
                    Modifier.weight(1f).clip(RoundedCornerShape(10.dp))
                        .background(if (selected) Color.White else Color.Transparent)
                        .clickable { tab = i }.padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                    Spacer(Modifier.width(6.dp))
                    KChip(count, bg = KColor.ChipBlue, fg = KColor.TextSecondary, fontSize = 10)
                }
            }
        }

        if (tab == 0) {
            // Batch signing
            Box(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
                    .background(Brush.linearGradient(listOf(KColor.Navy, Color(0xFF1B3C96))))
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(KColor.NavyLight),
                            contentAlignment = Alignment.Center
                        ) { Icon(Icons.Filled.CheckCircle, null, tint = Color.White, modifier = Modifier.size(20.dp)) }
                        Spacer(Modifier.width(10.dp))
                        Text("BATCH SIGNING", fontSize = 12.sp, color = Color(0xFFD0D8F0), modifier = Modifier.weight(1f))
                        KChip("Prioritas Tinggi", bg = Color(0xFFC62828), fg = Color.White)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text("Ada 18 lembar pengesahan menunggu", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                    Spacer(Modifier.height(2.dp))
                    Text(
                        "Telah diverifikasi penuh oleh Pembimbing Lapangan dan Dosen Pembimbing Akademik.",
                        fontSize = 11.sp, color = Color(0xFFD0D8F0), lineHeight = 15.sp
                    )
                    Spacer(Modifier.height(12.dp))
                    KButton(
                        "Tanda Tangani Semua Berkas (18)", icon = Icons.Filled.Create,
                        bg = Color.White, fg = KColor.Navy, modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Daftar Dokumen Tri-Partit", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                Text("Terbaru", fontSize = 12.sp, color = KColor.TextSecondary)
            }

            DocCard(
                icon = Icons.Filled.Menu, title = "Lembar Pengesahan", owner = "Anindya Putri • NIM 21014023",
                orgIcon = Icons.Filled.Home, org = "PT Telkom Indonesia (Divisi Digital)",
                orgRight = "Step 3/4", showSteps = true, buttonText = "Bubuhkan TTD"
            )
            DocCard(
                icon = Icons.Filled.List, title = "Berita Acara Seminar Kemajuan", owner = "Farhan Ramadhan • NIM 21014088",
                orgIcon = Icons.Filled.Home, org = "Bank Mandiri (IT Application Delivery)",
                orgRight = "Lulus Ujian", showSteps = false,
                note = "Telah diuji dan ditandatangani oleh Dosen Penguji Utama (Dr. Hendra, S.T., M.T.)",
                buttonText = "Tanda Tangani"
            )
            DocCard(
                icon = Icons.Filled.AccountBox, title = "Surat Rekomendasi Magang", owner = "Rina Novitasari • NIM 21014102",
                orgIcon = Icons.Filled.ShoppingCart, org = "Shopee Indonesia (Operations Support)",
                orgRight = "Semester\nGenap", showSteps = false, buttonText = "Tanda Tangani"
            )
        } else {
            KCard {
                Box(Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                    Text("42 dokumen telah disahkan.\n(Daftar dokumen akan ditampilkan di sini)", fontSize = 13.sp, color = KColor.TextSecondary)
                }
            }
        }

        // Info keamanan
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(KColor.SoftRow).padding(16.dp)
        ) {
            Box(
                Modifier.size(40.dp).clip(CircleShape).background(KColor.GreenSoft),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Filled.Lock, null, tint = KColor.Green, modifier = Modifier.size(20.dp)) }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Validitas & Keamanan Dokumen Resmi", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                Spacer(Modifier.height(2.dp))
                Text(
                    "Setiap tanda tangan digital dilengkapi QR Code verifikasi resmi dan tercatat langsung pada ledger akademik universitas berstandar BSrE.",
                    fontSize = 12.sp, color = KColor.TextSecondary, lineHeight = 17.sp
                )
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun DocCard(
    icon: ImageVector,
    title: String,
    owner: String,
    orgIcon: ImageVector,
    org: String,
    orgRight: String,
    showSteps: Boolean,
    buttonText: String,
    note: String? = null
) {
    KCard {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(icon, null, tint = KColor.Navy, modifier = Modifier.size(26.dp))
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                    Text(owner, fontSize = 12.sp, color = KColor.TextSecondary)
                }
                KChip("Menunggu TTD", bg = KColor.LavenderChip, fg = KColor.Maroon, fontSize = 10)
            }
            Spacer(Modifier.height(10.dp))
            Column(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(KColor.SoftRow).padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(orgIcon, null, tint = KColor.TextSecondary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(org, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                    Text(orgRight, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.TextSecondary, lineHeight = 13.sp)
                }
                if (showSteps) {
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        repeat(4) { i ->
                            Box(
                                Modifier.weight(1f).height(5.dp).clip(RoundedCornerShape(50))
                                    .background(if (i == 3) KColor.Navy else KColor.BlueMid)
                            )
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Row {
                        listOf("Mahasiswa ✓", "Mentor Ind. ✓", "Dosen PA ✓", "Kaprodi").forEachIndexed { i, s ->
                            Text(
                                s, fontSize = 10.sp, modifier = Modifier.weight(1f),
                                fontWeight = if (i == 3) FontWeight.Bold else FontWeight.Medium,
                                color = if (i == 3) KColor.Navy else KColor.TextPrimary
                            )
                        }
                    }
                }
                if (note != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(note, fontSize = 12.sp, color = KColor.TextSecondary, lineHeight = 17.sp)
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                KButton("Lihat PDF", filled = false, icon = Icons.Filled.Search, modifier = Modifier.weight(0.8f))
                KButton(buttonText, icon = Icons.Filled.Edit, modifier = Modifier.weight(1.2f))
            }
        }
    }
}
