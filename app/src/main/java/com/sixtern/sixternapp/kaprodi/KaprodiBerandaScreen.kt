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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.R

@Composable
fun KaprodiBerandaScreen(
    onGoValidasi: () -> Unit,
    onGoPengesahan: () -> Unit,
    modifier: Modifier = Modifier
)
{ Column (
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profil Kaprodi
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box {
                KAvatar("RW", 64.dp, photo = R.drawable.manpic)
                Box(
                    Modifier.align(Alignment.BottomEnd).size(16.dp).clip(CircleShape)
                        .background(KColor.Green).border(2.dp, Color.White, CircleShape)
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Dr. Ir. Rian Wicaksono, M.T.", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
                Text("Kaprodi S1 Teknologi Informasi", fontSize = 13.sp, color = KColor.TextSecondary)
                Spacer(Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    KChip("●  SIAKAD Terhubung", bg = KColor.ChipBlue, fontSize = 10)
                    KChip("Ganjil 2024/2025  ▾", bg = KColor.ChipBlue, fontSize = 10)
                }
            }
        }

        // Banner tenggat
        KCard {
            Row(Modifier.padding(16.dp)) {
                Box(
                    Modifier.size(44.dp).clip(CircleShape).background(KColor.Navy),
                    contentAlignment = Alignment.Center
                ) { Icon(Icons.Filled.Notifications, null, tint = Color.White, modifier = Modifier.size(22.dp)) }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Tenggat Konversi 20 SKS", fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
                            color = KColor.Maroon, modifier = Modifier.weight(1f)
                        )
                        Text("H-3", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = KColor.Maroon)
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "14 Mahasiswa menunggu persetujuan konversi nilai & e-sign Anda minggu ini.",
                        fontSize = 12.sp, color = KColor.TextSecondary, lineHeight = 17.sp
                    )
                    Spacer(Modifier.height(12.dp))
                    KButton("Periksa Sekarang  →", onClick = onGoValidasi)
                }
            }
        }

        // Statistik
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatCard(Modifier.weight(1f), Icons.Filled.Person, "48", "Mahasiswa Aktif Magang")
            StatCard(Modifier.weight(1f), Icons.Filled.Check, "14", "Siap Validasi SKS")
            StatCard(Modifier.weight(1f), Icons.Filled.Edit, "18", "Berkas Perlu E-Sign")
        }

        // Aksi cepat
        SectionTitle("Aksi Cepat Kaprodi", "Pintasan Layanan")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            QuickAction(Modifier.weight(1f), Icons.Filled.Check, "Validasi\nSKS", onGoValidasi)
            QuickAction(Modifier.weight(1f), Icons.Filled.Edit, "Pengesahan\nTTD", onGoPengesahan)
            QuickAction(Modifier.weight(1f), Icons.Filled.List, "Rekap\nYudisium")
            QuickAction(Modifier.weight(1f), Icons.Filled.Email, "Hubungi\nDPL")
        }

        // Antrean tindakan
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Antrean Tindakan", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
            Spacer(Modifier.width(8.dp))
            KChip("3 Prioritas", bg = KColor.Navy, fg = Color.White)
            Spacer(Modifier.weight(1f))
            Text("Lihat Semua (32)", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary)
        }

        QueueCard(
            initials = "AP", photo = R.drawable.womanpic, name = "Anindya Putri Rahmadani", subtitle = "PT Telkom Indonesia • Software E",
            badge = "94.45 (A)", badgeBg = KColor.ChipBlue, badgeFg = KColor.TextPrimary,
            infoIcon = Icons.Filled.CheckCircle, infoText = "Kelayakan 20 SKS Lengkap",
            infoRight = "DPL: Dr. Hendra", infoRightColor = KColor.TextSecondary
        ) {
            KButton("Detail Portofolio", filled = false)
            KButton("Tinjau & Validasi", onClick = onGoValidasi)
        }

        QueueCard(
            initials = "FR", photo = R.drawable.boypic, name = "Farhan Ramadhan", subtitle = "Bank Mandiri • Data Analyst",
            badge = "Siap TTD", badgeBg = KColor.ChipGray, badgeFg = KColor.TextSecondary,
            infoIcon = Icons.Filled.Edit, infoText = "Lembar Pengesahan Magang",
            infoRight = "2 TTD Lengkap", infoRightColor = KColor.TextSecondary
        ) {
            KButton("Lihat PDF", filled = false)
            KButton("Tanda Tangani", onClick = onGoPengesahan)
        }

        QueueCard(
            initials = "CL", photo = R.drawable.womanpic, name = "Citra Lestari", subtitle = "Shopee • UI/UX Designer",
            badge = "Revisi SKS", badgeBg = KColor.LavenderChip, badgeFg = KColor.Maroon,
            infoIcon = Icons.Filled.Warning, infoText = "Perlu Penyesuaian 4 SKS Pilihan",
            infoRight = "Belum Selaras", infoRightColor = KColor.RedText
        ) {
            KButton(
                "Beri Catatan ke DPL & Mahasiswa  ✎", bg = KColor.Lavender, fg = KColor.Navy,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Kepatuhan review DPL
        KCard {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(40.dp).clip(CircleShape).background(KColor.ChipBlue),
                        contentAlignment = Alignment.Center
                    ) { Icon(Icons.Filled.Info, null, tint = KColor.TextPrimary, modifier = Modifier.size(20.dp)) }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Kepatuhan Review DPL", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                        Text("Logbook mingguan ditinjau tepat waktu", fontSize = 11.sp, color = KColor.TextSecondary)
                    }
                    Text("96.4%", fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF4C6B57))
                }
                Spacer(Modifier.height(12.dp))
                KProgress(0.964f)
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("12 Dosen Pembimbing Aktif", fontSize = 11.sp, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                    Text("Beban Bimbingan Dosen", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.KeyboardArrowRight, null, tint = KColor.TextSecondary, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun StatCard(modifier: Modifier, icon: ImageVector, value: String, label: String) {
    KCard(modifier) {
        Column(Modifier.padding(12.dp)) {
            Box(
                Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).background(KColor.ChipGray),
                contentAlignment = Alignment.Center
            ) { Icon(icon, null, tint = KColor.TextPrimary, modifier = Modifier.size(18.dp)) }
            Spacer(Modifier.height(10.dp))
            Text(value, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
            Spacer(Modifier.height(2.dp))
            Text(label, fontSize = 11.sp, color = KColor.TextSecondary, lineHeight = 15.sp, minLines = 2)
        }
    }
}

@Composable
private fun QuickAction(modifier: Modifier, icon: ImageVector, label: String, onClick: () -> Unit = {}) {
    KCard(modifier.clip(RoundedCornerShape(16.dp)).clickable(onClick = onClick)) {
        Column(
            Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.size(40.dp).clip(CircleShape).background(KColor.SoftRow),
                contentAlignment = Alignment.Center
            ) { Icon(icon, null, tint = KColor.TextPrimary, modifier = Modifier.size(22.dp)) }
            Spacer(Modifier.height(8.dp))
            Text(
                label, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary,
                textAlign = TextAlign.Center, lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun QueueCard(
    initials: String,
    photo: Int? = null,
    name: String,
    subtitle: String,
    badge: String,
    badgeBg: Color,
    badgeFg: Color,
    infoIcon: ImageVector,
    infoText: String,
    infoRight: String,
    infoRightColor: Color,
    buttons: @Composable RowScope.() -> Unit
) {
    KCard {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                KAvatar(initials, 44.dp, photo = photo)
                Spacer(Modifier.width(10.dp))
                Column(Modifier.weight(1f)) {
                    Text(name, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(subtitle, fontSize = 12.sp, color = KColor.TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.width(6.dp))
                KChip(badge, bg = badgeBg, fg = badgeFg)
            }
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(KColor.SoftRow).padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(infoIcon, null, tint = KColor.TextSecondary, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(infoText, fontSize = 12.sp, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                Text(infoRight, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = infoRightColor)
            }
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                content = buttons
            )
        }
    }
}
