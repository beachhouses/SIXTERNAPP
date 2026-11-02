package com.sixtern.sixternapp.kaprodi

import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sixtern.sixternapp.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KaprodiProfilScreen(onLogout: () -> Unit, modifier: Modifier = Modifier) {
    var autoSync by remember { mutableStateOf(true) }

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Identitas
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.manpic),
                contentDescription = "Foto Kaprodi",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Column(){
                Row(
                    Modifier.clip(RoundedCornerShape(50)).background(KColor.ChipBlue).padding(horizontal = 10.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(Modifier.size(7.dp).clip(CircleShape).background(KColor.TextPrimary))
                    Spacer(Modifier.width(6.dp))
                    Text("Otoritas Akademik Aktif", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary)
                }
                Spacer(Modifier.height(4.dp))
                Text("Dr. Ir. Rian Wicaksono, M.T.", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
                Text("NIDN: 0012057801", fontSize = 12.sp, color = KColor.TextSecondary)
                Text("Ketua Program Studi S1 Teknologi Informasi", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary)
            }
        }

        // Sertifikasi & TTD
        SectionHeader(Icons.Filled.CheckCircle, "Sertifikasi & Tanda Tangan Digital")
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Lock, null, tint = KColor.Navy, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(16.dp))
            Text("Sertifikat Elektronik BSrE", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
            KChip("Aktif s/d 2025", bg = KColor.ChipBlue, fg = KColor.TextSecondary)
        }

        KCard {
            Column(Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Create, null, tint = KColor.Navy, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Pratinjau Spesimen TTD & Stempel Prodi", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                    Text("Perbarui", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = KColor.Red)
                }
                Spacer(Modifier.height(10.dp))
                Text("Goresan Digital Terenkripsi SHA-256", fontSize = 11.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic, color = KColor.TextSecondary)
                Row(Modifier.fillMaxWidth().padding(top = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Canvas(Modifier.weight(1f).height(56.dp)) {
                        val w = size.width
                        val h = size.height
                        val path = Path().apply {
                            moveTo(0f, h * 0.85f)
                            cubicTo(w * 0.10f, h * 0.30f, w * 0.20f, h * 0.95f, w * 0.30f, h * 0.45f)
                            cubicTo(w * 0.40f, h * 0.00f, w * 0.50f, h * 0.90f, w * 0.60f, h * 0.40f)
                            cubicTo(w * 0.70f, h * 0.05f, w * 0.80f, h * 0.50f, w * 0.90f, h * 0.30f)
                        }
                        drawPath(path, Color.Black, style = Stroke(width = 3f, cap = StrokeCap.Round))
                    }
                    Spacer(Modifier.width(16.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.CheckCircle, null, tint = KColor.TextPrimary, modifier = Modifier.size(20.dp))
                        Text("RESMI\nPRODI S1 TI", fontSize = 9.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, lineHeight = 11.sp, color = KColor.TextPrimary)
                    }
                    Spacer(Modifier.width(8.dp))
                }
            }
        }

        MenuRow(Icons.Filled.Lock, "Ubah PIN Pengesahan E-Sign")

        // Aturan & konfigurasi
        SectionHeader(Icons.Filled.Settings, "Aturan & Konfigurasi Konversi SKS")
        KCard {
            Column(Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Ambang Batas Nilai 20 SKS", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                        Text("Standar kelulusan ekuivalensi penuh", fontSize = 11.sp, color = KColor.Navy)
                    }
                    Text("80.0", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = KColor.Navy)
                    Text(" /B+", fontSize = 12.sp, color = KColor.TextSecondary)
                }
                Spacer(Modifier.height(14.dp))
                Row {
                    Text("Bobot Penilaian Tri-Partit", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                    Text("Total: 100%", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = KColor.Navy)
                }
                Spacer(Modifier.height(6.dp))
                Row(Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(50))) {
                    Box(Modifier.weight(0.5f).fillMaxHeight().background(KColor.Navy))
                    Box(Modifier.weight(0.3f).fillMaxHeight().background(KColor.BlueMid))
                    Box(Modifier.weight(0.2f).fillMaxHeight().background(KColor.DarkGray))
                }
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Legend(KColor.Navy, "Industri 50%")
                    Legend(KColor.BlueMid, "Dosen 30%")
                    Legend(KColor.DarkGray, "Logbook 20%")
                }
                Spacer(Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Sinkronisasi Otomatis SIAKAD & PDDikti", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                    Switch(
                        checked = autoSync, onCheckedChange = { autoSync = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = KColor.Navy
                        )
                    )
                }
            }
        }

        // Manajemen DPL
        SectionHeader(Icons.Filled.Person, "Manajemen Dosen Pembimbing (DPL)")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            DplStat(Modifier.weight(1f), Icons.Filled.Face, "5 Mhs", "Rata-rata beban per Dosen")
            DplStat(Modifier.weight(1f), Icons.Filled.Email, "96.4%", "Kepatuhan review mingguan")
        }
        KCard {
            Row(Modifier.fillMaxWidth().clickable { }.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.AccountBox, null, tint = KColor.Navy, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(12.dp))
                Text("Plotting & Alokasi Dosen Pembimbing", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary)
            }
        }

        // Bantuan & regulasi
        SectionHeader(Icons.Filled.Info, "Bantuan & Regulasi")
        MenuRow(Icons.Filled.List, "Buku Pedoman Konversi MBKM Pro...", "Revisi Kurikulum Terkini 2024 (PDF)", trailing = Icons.Filled.KeyboardArrowDown)
        MenuRow(Icons.Filled.Call, "Helpdesk SIAKAD & Admin Fakultas", "Biro Akademik & Kemahasiswaan", trailing = Icons.Filled.KeyboardArrowRight)

        // Logout
        KButton(
            "Keluar dari Akun Kaprodi", icon = Icons.AutoMirrored.Filled.ExitToApp,
            modifier = Modifier.fillMaxWidth(), onClick = onLogout
        )

        Text(
            "SIXTERN v2.4 (Edisi Mobile) • Hak Cipta Program Studi S1 Teknologi Informasi",
            fontSize = 11.sp, color = KColor.TextSecondary, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun SectionHeader(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = KColor.Navy, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
    }
}

@Composable
private fun MenuRow(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    trailing: ImageVector = Icons.Filled.KeyboardArrowRight
) {
    Row(
        Modifier.fillMaxWidth().clickable { }.padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = KColor.Navy, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            if (subtitle != null) Text(subtitle, fontSize = 12.sp, color = KColor.TextSecondary)
        }
        Icon(trailing, null, tint = KColor.TextSecondary, modifier = Modifier.size(22.dp))
    }
}

@Composable
private fun Legend(color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(8.dp).clip(CircleShape).background(color))
        Spacer(Modifier.width(5.dp))
        Text(text, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary)
    }
}

@Composable
private fun DplStat(modifier: Modifier, icon: ImageVector, value: String, label: String) {
    KCard(modifier) {
        Column(Modifier.padding(14.dp)) {
            Icon(icon, null, tint = KColor.TextPrimary, modifier = Modifier.size(20.dp))
            Spacer(Modifier.height(10.dp))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
            Text(label, fontSize = 12.sp, color = KColor.TextSecondary, lineHeight = 16.sp)
        }
    }
}
