package com.sixtern.sixternapp

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginMahasiswaScreen(
    onBackClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var nim by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberSession by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Kembali
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF3F4F6))
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = Color(0xFF111827),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Logo Sixtern
        Image(
            painter = painterResource(id = R.drawable.logo_sixtern),
            contentDescription = "Logo Sixtern",
            modifier = Modifier.size(56.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "SIXTERN", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF111827))
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFFEFF6FF))
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text(text = "● Mahasiswa", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1D4ED8))
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "PORTAL MAHASISWA MAGANG",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6B7280),
            letterSpacing = 1.2.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Selamat Datang, Mahasiswa",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Masuk untuk pengisian logbook harian, presensi GPS,\ndan memantau nilai magang MBKM Anda.",
            fontSize = 12.sp,
            color = Color(0xFF6B7280),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Form Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE5E7EB))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Input NIM / Email Kampus
                Text(text = "NIM / Email Kampus", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF111827))
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = nim,
                    onValueChange = { nim = it },
                    placeholder = { Text("Contoh: 2110512001", fontSize = 12.sp, color = Color(0xFF9CA3AF)) },
                    leadingIcon = { Icon(Icons.Default.School, contentDescription = null, tint = Color(0xFF6B7280)) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Input Kata Sandi
                Text(text = "Kata Sandi", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF111827))
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Masukkan kata sandi", fontSize = 12.sp, color = Color(0xFF9CA3AF)) },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFF6B7280)) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color(0xFF6B7280)
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = rememberSession, onCheckedChange = { rememberSession = it })
                        Text(text = "Ingat saya", fontSize = 12.sp, color = Color(0xFF111827))
                    }
                    Text(text = "Lupa Sandi?", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1E3A8A))
                }

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = { onLoginSuccess() },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E3A8A))
                ) {
                    Text(text = "Masuk sebagai Mahasiswa  →", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Opsi Masuk Cepat: Akun Email Kampus
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onGoogleLoginClick() },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE5E7EB))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFEFF6FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFF1D4ED8), modifier = Modifier.size(20.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = "Akun Email Kampus (SSO)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color(0xFF111827))
                    }
                }
                Icon(Icons.Outlined.ChevronRight, contentDescription = null, tint = Color(0xFF9CA3AF))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
