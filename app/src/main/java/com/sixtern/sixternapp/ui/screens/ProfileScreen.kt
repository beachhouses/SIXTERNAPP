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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    var logbookReminder by remember { mutableStateOf(true) }
    var mentorNotif by remember { mutableStateOf(true) }
    var siakadSync by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = BgScreen,
        topBar = {
            TopAppBar(
                title = { Text("Profil", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Outlined.ArrowBack, "Kembali") } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgScreen)
            )
        }
    ) { pad ->
        Column(
            Modifier.padding(pad).fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Identitas
            SxCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        Box(Modifier.size(88.dp).clip(CircleShape).background(BlueSoft), contentAlignment = Alignment.Center) {
                            Icon(Icons.Outlined.Person, null, tint = Navy, modifier = Modifier.size(44.dp))
                            // TODO: ganti dengan foto profil (mis. Coil AsyncImage)
                        }
                        Box(
                            Modifier.align(Alignment.BottomEnd).size(32.dp).clip(CircleShape).background(Color.White)
                                .border(2.dp, Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(Modifier.size(28.dp).clip(CircleShape).background(Navy), contentAlignment = Alignment.Center) {
                                Icon(Icons.Outlined.Edit, null, tint = Color.White, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                    Spacer(Modifier.width(16.dp))
                    Column {
                        SxChip("Aktif MBKM • Smt 7", BlueSoft, Navy, dot = true)
                        Spacer(Modifier.height(6.dp))
                        Text("Anindya Putri Rahmadani", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                        Text("21/478921/TK/52901", color = TextGray, fontSize = 14.sp)
                        Text("S1 Teknologi Informasi • USU", color = TextGray, fontSize = 14.sp)
                    }
                }
            }

            // Penugasan
            SxCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconBox(Icons.Outlined.Work, BlueSoft, Navy, size = 40.dp, shape = RoundedCornerShape(12.dp))
                    Spacer(Modifier.width(12.dp))
                    Text("Penugasan Magang", fontWeight = FontWeight.Bold, fontSize = 17.sp, modifier = Modifier.weight(1f))
                    SxChip("24 Minggu", GraySoft, TextGray)
                }
                Spacer(Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("PT Telkom Indonesia", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                        Text("Divisi Digital Service", color = TextGray)
                    }
                    SxChip("UI/UX Intern", BlueSoft, Navy)
                }
                HorizontalDivider(Modifier.padding(vertical = 14.dp), color = Divider)
                InfoRow(Icons.Outlined.SupervisorAccount, "Mentor Lapangan:", "Bambang Hidayat, M.Kom")
                Spacer(Modifier.height(10.dp))
                InfoRow(Icons.Outlined.School, "Dosen Pembimbing:", "Dr. Ir. Rian Wicaksono, M.T")
                Spacer(Modifier.height(10.dp))
                InfoRow(Icons.Outlined.CalendarToday, "Periode:", "15 Agu 2024 – 15 Feb 2025")
            }

            Group("AKUN & KEAMANAN") {
                MenuRow(Icons.Outlined.ContactPage, "Informasi Pribadi & Kontak", "anindya.rahmadani@mail.ugm.ac.id")
                MenuRow(Icons.Outlined.VpnKey, "Ubah Kata Sandi & PIN Presensi", "Terakhir diganti 45 hari lalu")
                MenuRow(Icons.Outlined.Face, "Verifikasi Wajah Biometri", "Presensi otomatis tanpa kontak", badge = "Terdaftar")
                MenuRow(Icons.Outlined.NearMe, "Izin Lokasi & Geofence GPS", "Radius presensi kantor Telkom", badge = "Diizinkan", last = true)
            }

            Group("PREFERENSI NOTIFIKASI") {
                SwitchRow("Pengingat Logbook Harian", "Setiap Senin - Jumat jam 19:30 WIB", logbookReminder) { logbookReminder = it }
                SwitchRow("Notifikasi Revisi Mentor", "Komentar & arahan logbook", mentorNotif) { mentorNotif = it }
                SwitchRow("Sinkronisasi SIAKAD Otomatis", "Pembaruan 20 SKS konversi berkala", siakadSync, last = true) { siakadSync = it }
            }

            Group("PUSAT BANTUAN & PANDUAN") {
                MenuRow(Icons.Outlined.Description, "Buku Panduan Mahasiswa MBKM", "Format PDF pedoman laporan akhir", trailing = Icons.Outlined.Download)
                MenuRow(Icons.Outlined.Headset, "Helpdesk & Layanan Kampus", "WhatsApp Unit Pengelola MBKM USU")
                MenuRow(Icons.Outlined.Shield, "Kebijakan Privasi", "Perlindungan data logbook & presensi", last = true)
            }

            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF3C4C4)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = RedSoft, contentColor = Red)
            ) {
                Icon(Icons.AutoMirrored.Outlined.ExitToApp, null)
                Spacer(Modifier.width(10.dp))
                Text("Keluar dari Akun Mahasiswa", fontWeight = FontWeight.Bold)
            }
            Text(
                "SIXTERN v2.4 (Mobile Edition) • Academic Year 2025/2026",
                color = TextGray, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = TextGray, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(10.dp))
        Text(label, color = TextGray, fontSize = 14.sp)
        Spacer(Modifier.width(6.dp))
        Text(value, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

@Composable
private fun Group(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, color = TextGray, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp, modifier = Modifier.padding(start = 4.dp))
        SxCard(padding = 0.dp, content = content)
    }
}

@Composable
private fun MenuRow(
    icon: ImageVector, title: String, subtitle: String,
    badge: String? = null, trailing: ImageVector = Icons.Filled.KeyboardArrowRight, last: Boolean = false
) {
    Row(
        Modifier.fillMaxWidth().clickable { }.padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBox(icon, GraySoft, TextGray, size = 44.dp)
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(subtitle, color = TextGray, fontSize = 13.sp)
        }
        if (badge != null) { SxChip(badge, BlueSoft, Navy); Spacer(Modifier.width(6.dp)) }
        Icon(trailing, null, tint = TextGray)
    }
    if (!last) HorizontalDivider(color = Divider)
}

@Composable
private fun SwitchRow(title: String, subtitle: String, checked: Boolean, last: Boolean = false, onChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(subtitle, color = TextGray, fontSize = 13.sp)
        }
        Switch(
            checked = checked, onCheckedChange = onChange,
            colors = SwitchDefaults.colors(checkedTrackColor = Navy, checkedThumbColor = Color.White)
        )
    }
    if (!last) HorizontalDivider(color = Divider, modifier = Modifier.padding(horizontal = 16.dp))
}
