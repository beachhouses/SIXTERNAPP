
package com.sixtern.sixternapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Business
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import com.sixtern.sixternapp.R

private val Navy = Color(0xFF082F75)
private val Purple = Color(0xFF5524A8)
private val Muted = Color(0xFF64748B)

@Composable
fun MainScreen(
    onMahasiswaClick: () -> Unit = {},
    onDosenClick: () -> Unit = {},
    onPembimbingClick: () -> Unit = {},
    onHelpdeskClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF8F7FF),
                        Color(0xFFEEF1FF),
                        Color(0xFFFAF8FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_sixtern),
                contentDescription = "Logo SixTern",
                modifier = Modifier.size(width = 150.dp, height = 112.dp)
            )

            Text(
                text = "SISTEM EVALUASI & PEMANTAUAN MAGANG",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF94A3B8),
                letterSpacing = 0.5.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Selamat Datang",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Navy,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Pilih peran Anda untuk melanjutkan masuk\nke aplikasi",
                fontSize = 14.sp,
                color = Color(0xFF57515D),
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )

            Spacer(modifier = Modifier.height(34.dp))

            RoleCard(
                title = "Mahasiswa",
                description = "Logbook, presensi GPS & nilai magang",
                icon = Icons.Filled.School,
                iconColor = Color(0xFF2457F5),
                iconBackground = Color(0xFFEEF4FF),
                onClick = onMahasiswaClick
            )

            Spacer(modifier = Modifier.height(14.dp))

            RoleCard(
                title = "Dosen / Kaprodi",
                description = "Supervisi akademik & konversi 20 SKS",
                icon = Icons.Filled.AccountBalance,
                iconColor = Color(0xFF5145E5),
                iconBackground = Color(0xFFF0EEFF),
                onClick = onDosenClick
            )

            Spacer(modifier = Modifier.height(14.dp))

            RoleCard(
                title = "Pembimbing Lapangan",
                description = "Mentor industri & evaluasi lapangan",
                icon = Icons.Filled.Business,
                iconColor = Color(0xFF0085C7),
                iconBackground = Color(0xFFEAF7FF),
                onClick = onPembimbingClick
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Butuh bantuan masuk? ",
                    fontSize = 12.sp,
                    color = Muted
                )

                Text(
                    text = "Hubungi Helpdesk",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2457F5),
                    modifier = Modifier.clickable {
                        onHelpdeskClick()
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "• SIXTERN Mobile v2.4 •",
                fontSize = 11.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Composable
private fun RoleCard(
    title: String,
    description: String,
    icon: ImageVector,
    iconColor: Color,
    iconBackground: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 90.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(17.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            Color(0xFFE8EAF5)
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = iconBackground,
                        shape = RoundedCornerShape(13.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF172033)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = description,
                    fontSize = 11.sp,
                    color = Muted,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        Color(0xFFF1F5F9),
                        RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Pilih $title",
                    tint = Color(0xFF94A3B8),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
