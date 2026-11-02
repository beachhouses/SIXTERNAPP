package com.sixtern.sixternapp.kaprodi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.sixtern.sixternapp.R

private data class MataKuliah(val no: Int, val nama: String, val detail: String, val grade: String)

@Composable
fun KaprodiValidasiScreen(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf(0) }

    val matkul = listOf(
        MataKuliah(1, "Proyek Perangkat Lunak Terapan", "Bobot: 4 SKS • Capaian Portofolio UI Kit", "A"),
        MataKuliah(2, "Interaksi Manusia & Komputer Lanjut", "Bobot: 3 SKS • Usability Testing Valid", "A"),
        MataKuliah(3, "Etika & Komunikasi Profesional", "Bobot: 3 SKS • Evaluasi Mentor 96/100", "A"),
        MataKuliah(4, "Praktik Kerja Lapangan MBKM", "Bobot: 10 SKS • 900 Jam Kerja Terpenuhi", "A")
    )

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Judul
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Check, null, tint = KColor.Navy, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("KURIKULUM MBKM TERPADU", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = KColor.Navy)
            }
            Spacer(Modifier.height(6.dp))
            Text("Validasi Konversi 20 SKS", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
            Spacer(Modifier.height(4.dp))
            Text(
                "Penyetaraan capaian magang industri ke transkrip akademik S1 Teknologi Informasi semester gasal.",
                fontSize = 13.sp, color = KColor.TextSecondary, lineHeight = 18.sp
            )
        }

        // Pencarian
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text("Cari nama mahasiswa atau instansi...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Filled.Search, null) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = KColor.Navy,
                    unfocusedBorderColor = KColor.Border,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
            Spacer(Modifier.width(8.dp))
            Box(
                Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(KColor.ChipGray),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Filled.Settings, "Filter", tint = KColor.TextSecondary, modifier = Modifier.size(20.dp)) }
        }

        // Filter chip
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Menunggu Validasi" to "14", "Sudah Disahkan" to "34", "Perlu Revisi" to "3").forEachIndexed { i, (label, count) ->
                val selected = i == filter
                Row(
                    Modifier.clip(RoundedCornerShape(50))
                        .background(if (selected) KColor.Navy else KColor.ChipGray)
                        .clickable { filter = i }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = if (selected) Color.White else KColor.TextSecondary)
                    Spacer(Modifier.width(6.dp))
                    Text(count, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (selected) Color.White else KColor.TextSecondary)
                }
            }
        }

        // Kartu utama mahasiswa
        KCard {
            Column(Modifier.padding(14.dp)) {
                // Foto (placeholder inisial; ganti dengan Image jika sudah ada drawable)
                Image(
                    painter = painterResource(id = R.drawable.validasisks),
                    contentDescription = "Foto mahasiswa",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 90.dp, bottomStart = 90.dp, topEnd = 12.dp, bottomEnd = 12.dp))
                )
                Spacer(Modifier.height(10.dp))
                Text("Anindya Putri Rahmadani", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                Text("PT Telkom Indonesia • Software Engineer", fontSize = 12.sp, color = KColor.TextSecondary)
                Spacer(Modifier.height(10.dp))

                // Skor
                Row(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(KColor.SoftRow).padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(KColor.ChipBlue),
                        contentAlignment = Alignment.Center
                    ) { Icon(Icons.Filled.Star, null, tint = KColor.Navy, modifier = Modifier.size(20.dp)) }
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Skor Tri-Partit Gabungan", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = KColor.Navy)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text("94.45", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = KColor.TextPrimary)
                            Text(" / 100", fontSize = 12.sp, color = KColor.TextSecondary)
                        }
                    }
                    KChip("Grade A", bg = KColor.ChipBlue, fg = KColor.TextSecondary)
                }

                Spacer(Modifier.height(14.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Peta Kesetaraan Kurikulum", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = KColor.TextPrimary, modifier = Modifier.weight(1f))
                    Text("(4 Mata Kuliah)", fontSize = 11.sp, color = KColor.Maroon)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.Filled.CheckCircle, null, tint = KColor.TextSecondary, modifier = Modifier.size(14.dp))
                    Text(" 20 / 20 SKS (100%)", fontSize = 11.sp, color = KColor.TextSecondary)
                }
                Spacer(Modifier.height(8.dp))

                matkul.forEach { mk ->
                    Row(
                        Modifier.fillMaxWidth().padding(vertical = 4.dp).clip(RoundedCornerShape(10.dp))
                            .background(KColor.SoftRow).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier.size(24.dp).clip(RoundedCornerShape(6.dp)).background(KColor.Navy),
                            contentAlignment = Alignment.Center
                        ) { Text("${mk.no}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold) }
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text(mk.nama, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                            Text(mk.detail, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.Navy)
                        }
                        Spacer(Modifier.width(8.dp))
                        Box(
                            Modifier.clip(RoundedCornerShape(8.dp)).background(Color.White).padding(horizontal = 12.dp, vertical = 6.dp)
                        ) { Text(mk.grade, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = KColor.Navy) }
                    }
                }

                Spacer(Modifier.height(10.dp))
                Text("Beban Transkrip Otomatis", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = KColor.Navy)
                Spacer(Modifier.height(6.dp))
                Row(Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(50)).background(KColor.Lavender)) {
                    Box(Modifier.weight(0.25f).fillMaxHeight().background(KColor.Navy))
                    Box(Modifier.weight(0.2f).fillMaxHeight().background(KColor.BlueMid))
                    Box(Modifier.weight(0.2f).fillMaxHeight().background(KColor.Navy))
                    Box(Modifier.weight(0.35f).fillMaxHeight())
                }
                Spacer(Modifier.height(4.dp))
                Row {
                    Text("0 SKS", fontSize = 11.sp, color = KColor.Navy, modifier = Modifier.weight(1f))
                    Text("Target Kelulusan Semester: 20 SKS", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = KColor.Navy)
                }
                Spacer(Modifier.height(12.dp))
                KButton("Setujui Konversi 20 SKS", icon = Icons.Filled.CheckCircle, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                KButton("Sesuaikan Bobot SKS", filled = false, icon = Icons.Filled.Settings, modifier = Modifier.fillMaxWidth())
            }
        }

        // Daftar validasi mahasiswa
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Validasi Mahasiswa", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
            Spacer(Modifier.width(8.dp))
            KChip("13 Mahasiswa", bg = KColor.ChipGray, fg = KColor.TextSecondary)
            Spacer(Modifier.weight(1f))
            Text("Lihat\nSemua", fontSize = 11.sp, color = KColor.Maroon, lineHeight = 13.sp)
        }

        StudentRow(
            "DA", "Dimas Arya", "Frontend Dev • PT Telkom",
            tag = "• 20 SKS Siap", tagColor = KColor.TextPrimary, extra = "Skor: 92.1",
            actionText = "Setujui Instan", actionFilled = true,
            photo = R.drawable.boypic
        )
        StudentRow(
            "CL", "Citra Lestari", "Data Analyst • Shopee Internatio",
            tag = "• Butuh Konfirmasi 1 MK", tagColor = KColor.RedText, extra = "17/20 SKS",
            actionText = "Tinjau", actionFilled = false,
            photo = R.drawable.womanpic
        )
        // Validasi masal
        KCard {
            Column(Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(36.dp).clip(CircleShape).background(KColor.ChipBlue),
                        contentAlignment = Alignment.Center
                    ) { Icon(Icons.Filled.Done, null, tint = KColor.TextSecondary, modifier = Modifier.size(20.dp)) }
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text("Validasi Cepat Masal", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                        Text(
                            "12 mahasiswa telah memenuhi 100% rubrik tanpa catatan",
                            fontSize = 12.sp, color = KColor.TextSecondary, lineHeight = 16.sp
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                KButton("Validasi Masal 12 Mahasiswa On-Track", icon = Icons.Filled.Done, modifier = Modifier.fillMaxWidth())
            }
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun StudentRow(
    initials: String,
    name: String,
    subtitle: String,
    tag: String,
    tagColor: Color,
    extra: String,
    actionText: String,
    actionFilled: Boolean,
    photo: Int? = null
) {
    KCard {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            KAvatar(initials, 40.dp, photo = photo)
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = KColor.TextPrimary)
                Text(subtitle, fontSize = 12.sp, color = KColor.TextSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (tagColor == KColor.TextPrimary) {
                        KChip(tag, bg = KColor.ChipBlue, fg = tagColor, fontSize = 10)
                    } else {
                        Text(tag, fontSize = 10.sp, fontWeight = FontWeight.Medium, color = tagColor)
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(extra, fontSize = 10.sp, color = KColor.TextSecondary)
                }
            }
            Spacer(Modifier.width(8.dp))
            if (actionFilled) {
                KButton(actionText)
            } else {
                KButton(actionText, filled = false, icon = Icons.Filled.Search)
            }
        }
    }
}
