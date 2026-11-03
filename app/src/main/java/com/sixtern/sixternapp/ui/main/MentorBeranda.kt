package com.sixtern.sixternapp.ui.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF082F75)
private val Blue = Color(0xFF1769C2)
private val Background = Color(0xFFF8FAFC)
private val Muted = Color(0xFF64748B)
private val Border = Color(0xFFE2E8F0)
private val Green = Color(0xFF10B981)
private val Red = Color(0xFFEF4444)

@Composable
fun MentorBeranda() {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Background,
        topBar = {
            MentorHeader()
        },
        bottomBar = {
            MentorBottomBar(
                selectedTab = selectedTab,
                onSelect = {
                    selectedTab = it
                    if (it != 0) {
                        Toast.makeText(
                            context,
                            "Halaman ini akan dibuat berikutnya",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            MentorProfileCard()

            MentorStatistics()

            AttendanceCard()

            QuickActions()

            ActionQueue()

            BroadcastCard()
        }
    }
}

// HEADER

@Composable
fun MentorHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "SIXTERN",
            fontSize = 21.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Navy
        )

        Spacer(Modifier.width(10.dp))

        Surface(
            color = Color(0xFFEAF5FF),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                "Mentor Industri",
                color = Blue,
                fontSize = 10.sp,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 6.dp
                )
            )
        }

        Spacer(Modifier.weight(1f))

        IconButton(onClick = {}) {
            Icon(
                Icons.Default.NotificationsNone,
                contentDescription = "Notifikasi",
                tint = Navy
            )
        }

        Surface(
            shape = CircleShape,
            color = Navy,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Profil",
                tint = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// PROFIL MENTOR

@Composable
fun MentorProfileCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Border),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(52.dp),
                    shape = CircleShape,
                    color = Color(0xFFE8F0FF)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            "BH",
                            color = Navy,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.width(12.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        "Bambang Hidayat, M.Kom",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )

                    Text(
                        "Lead Product Designer • PT Telkom Indonesia",
                        fontSize = 11.sp,
                        color = Blue
                    )

                    Spacer(Modifier.height(7.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SmallBadge(
                            text = "● Mentor Aktif",
                            color = Green,
                            background = Color(0xFFE5F8EF)
                        )

                        Spacer(Modifier.width(6.dp))

                        SmallBadge(
                            text = "Batch Ganjil 24/25",
                            color = Muted,
                            background = Color(0xFFF1F5F9)
                        )
                    }
                }

                Icon(
                    Icons.Default.Tune,
                    contentDescription = "Pengaturan profil",
                    tint = Muted
                )
            }

            Spacer(Modifier.height(14.dp))

            HorizontalDivider(color = Border)

            Spacer(Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Shield,
                    contentDescription = null,
                    tint = Color(0xFFEC4899),
                    modifier = Modifier.size(25.dp)
                )

                Spacer(Modifier.width(8.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        "Fakultas Rekayasa Industri",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Navy
                    )

                    Text(
                        "8 Mahasiswa Binaan Terdaftar",
                        fontSize = 10.sp,
                        color = Blue
                    )
                }

                SmallBadge(
                    text = "Minggu ke-12",
                    color = Red,
                    background = Color(0xFFFFF1F2)
                )
            }
        }
    }
}

// BADGE

@Composable
fun SmallBadge(
    text: String,
    color: Color,
    background: Color
) {
    Surface(
        color = background,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = text,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            color = color,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 5.dp
            )
        )
    }
}

// STATISTIK

@Composable
fun MentorStatistics() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Groups,
            iconColor = Green,
            iconBackground = Color(0xFFE5F8EF),
            value = "8",
            title = "Mhs Binaan",
            description = "6 Hadir • 1 Izin • 1 Belum"
        )

        StatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.CalendarMonth,
            iconColor = Color(0xFF9747FF),
            iconBackground = Color(0xFFF3E8FF),
            value = "5",
            title = "Logbook",
            description = "Perlu tindakan Anda",
            badge = "Review"
        )

        StatCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Star,
            iconColor = Blue,
            iconBackground = Color(0xFFE0F2FE),
            value = "93.4",
            title = "Rerata Skor",
            description = "Sangat Memuaskan"
        )
    }
}

@Composable
fun StatCard(
    modifier: Modifier,
    icon: ImageVector,
    iconColor: Color,
    iconBackground: Color,
    value: String,
    title: String,
    description: String,
    badge: String? = null
) {
    Card(
        modifier = modifier.heightIn(min = 145.dp),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Border)
    ) {
        Column(
            modifier = Modifier.padding(11.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(9.dp),
                    color = iconBackground
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.padding(7.dp).size(17.dp)
                    )
                }

                if (badge != null) {
                    SmallBadge(
                        badge,
                        Red,
                        Color(0xFFFFF1F2)
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            Text(
                value,
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF172033)
            )

            Text(
                title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Navy
            )

            Spacer(Modifier.height(3.dp))

            Text(
                description,
                fontSize = 8.sp,
                color = if (badge != null) Red else Muted,
                lineHeight = 11.sp
            )
        }
    }
}

// RADAR PRESENSI

@Composable
fun AttendanceCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Border)
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = Green,
                    modifier = Modifier.size(9.dp)
                ) {}

                Spacer(Modifier.width(8.dp))

                Text(
                    "Radar Presensi Geofence TLT",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF172033),
                    modifier = Modifier.weight(1f)
                )

                SmallBadge(
                    "Radius 50m Aktif",
                    Green,
                    Color(0xFFE5F8EF)
                )
            }

            Spacer(Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Check-in Gedung Telkom Landmark Tower",
                    fontSize = 11.sp,
                    color = Blue,
                    modifier = Modifier.weight(1f)
                )

                Column {
                    Text(
                        "6 / 8",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )
                    Text(
                        "Mahasiswa",
                        fontSize = 10.sp,
                        color = Muted
                    )
                }
            }

            Spacer(Modifier.height(9.dp))

            LinearProgressIndicator(
                progress = 0.75f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(9.dp),
                color = Navy,
                trackColor = Color(0xFFCBD5E1)
            )

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendItem(Navy, "Tepat Waktu (6)")
                LegendItem(Color(0xFF9DD8F1), "Izin Resmi (1)")
            }

            Spacer(Modifier.height(5.dp))

            LegendItem(Color(0xFFCBD5E1), "Belum Hadir (1)")

            Spacer(Modifier.height(14.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(7.dp)
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("Cek Peta Kantor", fontSize = 10.sp)
                }

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Navy
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(7.dp)
                ) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("Validasi Presensi", fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            color = color,
            shape = CircleShape,
            modifier = Modifier.size(8.dp)
        ) {}

        Spacer(Modifier.width(5.dp))

        Text(
            text,
            fontSize = 9.sp,
            color = Muted
        )
    }
}

// AKSI CEPAT

@Composable
fun QuickActions() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Aksi Cepat Mentor",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172033)
            )

            Text(
                "Pintasan Utama",
                fontSize = 11.sp,
                color = Blue
            )
        }

        Spacer(Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuickActionItem(
                Icons.Default.MenuBook,
                "Review\nLogbook",
                Color(0xFFFFE4E6),
                Red
            )

            QuickActionItem(
                Icons.Default.Star,
                "Beri Nilai",
                Color(0xFFDCFCE7),
                Green
            )

            QuickActionItem(
                Icons.Default.LocationOn,
                "Presensi\nTim",
                Color(0xFFE0F2FE),
                Blue
            )

            QuickActionItem(
                Icons.Default.Campaign,
                "Broadcast",
                Color(0xFFF3E8FF),
                Color(0xFF9747FF)
            )
        }
    }
}

@Composable
fun QuickActionItem(
    icon: ImageVector,
    title: String,
    background: Color,
    tint: Color
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clickable {
                Toast.makeText(
                    context,
                    "$title akan segera tersedia",
                    Toast.LENGTH_SHORT
                ).show()
            }
    ) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = background,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = tint,
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(Modifier.height(6.dp))

        Text(
            title,
            fontSize = 10.sp,
            color = Navy,
            lineHeight = 13.sp
        )
    }
}

// ANTREAN TINDAKAN

@Composable
fun ActionQueue() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Antrean Tindakan",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172033)
            )

            Spacer(Modifier.width(8.dp))

            SmallBadge(
                "3 Butuh Aksi",
                Red,
                Color(0xFFFFF1F2)
            )

            Spacer(Modifier.weight(1f))

            Text(
                "Semua (5)",
                fontSize = 11.sp,
                color = Blue
            )
        }

        Spacer(Modifier.height(12.dp))

        StudentTaskCard(
            name = "Anindya Putri Rahmadani",
            role = "UI/UX Design Intern",
            title = "High-Fi Prototype Modul Evaluasi Mentoring",
            description = "Menyelesaikan 14 screen interaktif flow di Figma untuk modul rekap penilaian akhir mentor industri.",
            time = "Diajukan 08:30 WIB",
            initials = "AP"
        )

        Spacer(Modifier.height(12.dp))

        StudentTaskCard(
            name = "Dimas Arya Pratama",
            role = "Frontend Developer Intern",
            title = "Integrasi Geofence API & Testing Koordinat TLT",
            description = "Mengatasi kendala presisi GPS mobile browser saat mahasiswa check-in di lantai 12 dengan radius...",
            time = "Diajukan 09:12 WIB",
            initials = "DA"
        )

        Spacer(Modifier.height(12.dp))

        LeaveRequestCard()
    }
}

@Composable
fun StudentTaskCard(
    name: String,
    role: String,
    title: String,
    description: String,
    time: String,
    initials: String
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Border)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8F0FF),
                    shape = CircleShape,
                    modifier = Modifier.size(38.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            initials,
                            color = Navy,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(Modifier.width(9.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        name,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )

                    Text(
                        "$role • Hari ke-58",
                        fontSize = 9.sp,
                        color = Blue
                    )
                }

                SmallBadge(
                    "Logbook",
                    Red,
                    Color(0xFFFFF1F2)
                )
            }

            Spacer(Modifier.height(12.dp))

            Surface(
                color = Color(0xFFF8FAFC),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Border)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        title,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        description,
                        fontSize = 10.sp,
                        color = Muted,
                        lineHeight = 16.sp
                    )

                    Spacer(Modifier.height(9.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Link,
                            contentDescription = null,
                            tint = Blue,
                            modifier = Modifier.size(13.dp)
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            "1 Link Figma",
                            fontSize = 9.sp,
                            color = Blue
                        )

                        Text(
                            "  •  $time",
                            fontSize = 9.sp,
                            color = Blue
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Detail logbook $name",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(11.dp),
                    contentPadding = PaddingValues(7.dp)
                ) {
                    Icon(
                        Icons.Default.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("Detail", fontSize = 10.sp)
                }

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Persetujuan logbook belum terhubung",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Navy
                    ),
                    shape = RoundedCornerShape(11.dp),
                    contentPadding = PaddingValues(7.dp)
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("Setujui Cepat", fontSize = 10.sp)
                }
            }
        }
    }
}

// PENGAJUAN IZIN

@Composable
fun LeaveRequestCard() {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Border)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8F0FF),
                    shape = CircleShape,
                    modifier = Modifier.size(38.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            "RN",
                            color = Navy,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(Modifier.width(9.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        "Rina Novitasari",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )

                    Text(
                        "Data Analyst Intern • Pengajuan Izin",
                        fontSize = 9.sp,
                        color = Blue
                    )
                }

                SmallBadge(
                    "Perlu Konfirmasi",
                    Color(0xFFD97706),
                    Color(0xFFFFF7E6)
                )
            }

            Spacer(Modifier.height(12.dp))

            Surface(
                color = Color(0xFFF8FAFC),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Border)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.MedicalServices,
                        contentDescription = null,
                        tint = Red
                    )

                    Spacer(Modifier.width(9.dp))

                    Column(Modifier.weight(1f)) {
                        Text(
                            "Izin Sakit (1 Hari) – 24 Okt 2024",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Navy
                        )
                        Text(
                            "Surat keterangan dokter terlampir",
                            fontSize = 9.sp,
                            color = Muted
                        )
                    }

                    Icon(
                        Icons.Default.Description,
                        contentDescription = null,
                        tint = Muted
                    )
                }
            }

            Spacer(Modifier.height(11.dp))

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Halaman konfirmasi izin belum tersedia",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Navy
                ),
                shape = RoundedCornerShape(11.dp)
            ) {
                Icon(
                    Icons.Default.AssignmentTurnedIn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(7.dp))
                Text(
                    "Tinjau Surat & Konfirmasi",
                    fontSize = 11.sp
                )
            }
        }
    }
}

// KIRIM ARAHAN

@Composable
fun BroadcastCard() {
    val context = LocalContext.current
    var instruction by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, Border)
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = Color(0xFFE8EEFF),
                    shape = RoundedCornerShape(9.dp)
                ) {
                    Icon(
                        Icons.Default.Chat,
                        contentDescription = null,
                        tint = Navy,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(Modifier.width(9.dp))

                Column(Modifier.weight(1f)) {
                    Text(
                        "Kirim Arahan Singkat",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )
                    Text(
                        "Langsung ke notifikasi WhatsApp & App Mahasiswa",
                        fontSize = 9.sp,
                        color = Blue
                    )
                }

                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Blue
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = instruction,
                onValueChange = { instruction = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Tulis instruksi harian, jadwal sprint review, atau agenda tatap muka...",
                        fontSize = 11.sp,
                        color = Color(0xFF94A3B8)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                minLines = 2,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Blue,
                    unfocusedBorderColor = Border
                )
            )

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "◷  14:00 WIB\n    Standup",
                        fontSize = 9.sp,
                        color = Navy,
                        modifier = Modifier.padding(9.dp)
                    )
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "⌖  Lt. 12\n    Coworking",
                        fontSize = 9.sp,
                        color = Navy,
                        modifier = Modifier.padding(9.dp)
                    )
                }

                Button(
                    onClick = {
                        if (instruction.isBlank()) {
                            Toast.makeText(
                                context,
                                "Tulis arahan terlebih dahulu",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Arahan siap dikirim setelah fitur broadcast terhubung",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Navy
                    ),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 10.dp
                    )
                ) {
                    Text("Kirim", fontSize = 10.sp)
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        Icons.Default.Send,
                        contentDescription = null,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        }
    }
}

// BOTTOM NAVIGATION

@Composable
fun MentorBottomBar(
    selectedTab: Int,
    onSelect: (Int) -> Unit
) {
    val items = listOf(
        Icons.Default.GridView,
        Icons.Default.MenuBook,
        Icons.Default.EditNote,
        Icons.Default.People
    )

    val labels = listOf(
        "Beranda",
        "Logbook",
        "Evaluasi",
        "Binaan"
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        items.forEachIndexed { index, icon ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onSelect(index) },
                icon = {
                    Icon(
                        icon,
                        contentDescription = labels[index]
                    )
                },
                label = {
                    Text(
                        labels[index],
                        fontSize = 10.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Navy,
                    selectedTextColor = Navy,
                    indicatorColor = Color(0xFFE8EEFF),
                    unselectedIconColor = Blue,
                    unselectedTextColor = Blue
                )
            )
        }
    }
}
