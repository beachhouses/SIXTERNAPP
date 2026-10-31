package com.sixtern.sixternapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sixtern.sixternapp.ui.theme.SIXTERNAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SIXTERNAPPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    SixternAppNavigation()
                }
            }
        }
    }
}

@Composable
fun SixternAppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "role_selection") {
        // Halaman Pemilihan Peran (Multi-role)
        composable("role_selection") {
            RoleSelectionScreen(
                onMahasiswaClick = {
                    navController.navigate("login_mahasiswa")
                },
                onDosenClick = {
                    navController.navigate("login_kaprodi")
                },
                onMentorClick = {
                    navController.navigate("login_mentor")
                }
            )
        }

        // Login Mahasiswa
        composable("login_mahasiswa") {
            LoginMahasiswaScreen(
                onBackClick = { navController.popBackStack() },
                onGoogleLoginClick = { navController.navigate("google_campus_login") },
                onLoginSuccess = { navController.navigate("beranda_utama") }
            )
        }

        // Login Kaprodi
        composable("login_kaprodi") {
            LoginKaprodiScreen(
                onBackClick = { navController.popBackStack() },
                onGoogleLoginClick = { navController.navigate("google_campus_login") },
                onLoginSuccess = { navController.navigate("beranda_utama") }
            )
        }

        // Login Mentor Lapangan (File terpisah: LoginMentorScreen.kt)
        composable("login_mentor") {
            LoginMentorScreen(
                onBackClick = { navController.popBackStack() },
                onSSOLoginClick = { navController.navigate("google_campus_login") },
                onLoginSuccess = { navController.navigate("beranda_utama") }
            )
        }

        // Halaman SSO / Google Akun Kampus / Perusahaan Mitra
        composable("google_campus_login") {
            GoogleCampusLoginScreen(
                onBackClick = { navController.popBackStack() },
                onAccountSelected = { navController.navigate("beranda_utama") }
            )
        }

        // Beranda Utama Dummy
        composable("beranda_utama") {
            BerandaUtamaScreen(
                onLogout = {
                    navController.popBackStack("role_selection", inclusive = false)
                }
            )
        }
    }
}

// ==========================================
// HALAMAN PEMILIHAN PERAN
// ==========================================
@Composable
fun RoleSelectionScreen(
    onMahasiswaClick: () -> Unit,
    onDosenClick: () -> Unit,
    onMentorClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_sixtern),
            contentDescription = "Logo Sixtern",
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "SIXTERN",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827),
            letterSpacing = 1.5.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "SISTEM EVALUASI & PEMANTAUAN MAGANG",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6B7280),
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Selamat Datang",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Pilih peran Anda untuk melanjutkan masuk\nke aplikasi",
            fontSize = 13.sp,
            color = Color(0xFF6B7280),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        RoleCard(
            title = "Mahasiswa",
            description = "Logbook, presensi GPS & nilai magang",
            icon = Icons.Default.Person,
            onClick = onMahasiswaClick
        )

        Spacer(modifier = Modifier.height(14.dp))

        RoleCard(
            title = "Dosen / Kaprodi",
            description = "Supervisi akademik & konversi 20 SKS",
            icon = Icons.Default.AccountBalance,
            onClick = onDosenClick
        )

        Spacer(modifier = Modifier.height(14.dp))

        RoleCard(
            title = "Pembimbing Lapangan",
            description = "Mentor industri & evaluasi lapangan",
            icon = Icons.Default.BusinessCenter,
            onClick = onMentorClick
        )

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Butuh bantuan masuk? ", fontSize = 12.sp, color = Color(0xFF6B7280))
            Text(
                text = "Hubungi Helpdesk",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A),
                modifier = Modifier.clickable { /* Aksi Helpdesk */ }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• SIXTERN Mobile v2.4 •",
            fontSize = 11.sp,
            color = Color(0xFF9CA3AF)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun RoleCard(title: String, description: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEFF6FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF1E3A8A),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF111827))
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = description, fontSize = 12.sp, color = Color(0xFF6B7280))
                }
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF9CA3AF)
            )
        }
    }
}

// ==========================================
// HALAMAN BERANDA / DASHBOARD UTAMA (DUMMY)
// ==========================================
@Composable
fun BerandaUtamaScreen(onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFF1E3A8A)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Beranda Utama",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Selamat datang di Portal SIXTERN MBKM 2026.\nAnda berhasil masuk ke sistem.",
            fontSize = 13.sp,
            color = Color(0xFF6B7280),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF4444))
        ) {
            Text(text = "Keluar (Logout)", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}