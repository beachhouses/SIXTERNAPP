package com.sixtern.sixternapp.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixtern.sixternapp.R
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(5.seconds)
        onSplashFinished()
    }

    var startAnimation by remember { mutableStateOf(value = false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "alphaAnim",
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.75f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "scaleAnim",
    )

    val infiniteTransition = rememberInfiniteTransition(label = "splashInfinite")

    val floatOffsetY by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "floatOffsetY",
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "glowAlpha",
    )

    // Continuous rotation for background geometric wireframes
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 40000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "rotationAngle",
    )

    val nodeTimePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * Math.PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "nodeTimePhase",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onSplashFinished()
            },
    ) {
        ConstellationBackgroundCanvas(
            rotationAngle = rotationAngle,
            timePhase = nodeTimePhase,
            glowAlpha = glowAlpha,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Central Glowing Card Container
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .scale(scaleAnim)
                    .alpha(alphaAnim)
                    .offset(y = floatOffsetY.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(260.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF38BDF8).copy(alpha = glowAlpha * 0.7f),
                                    Color(0xFF818CF8).copy(alpha = glowAlpha * 0.45f),
                                    Color(0xFF4F46E5).copy(alpha = 0f),
                                ),
                            ),
                            shape = RoundedCornerShape(130.dp),
                        ),
                )

                Image(
                    painter = painterResource(id = R.drawable.logo_sixtern),
                    contentDescription = "SixTern Logo",
                    modifier = Modifier.size(220.dp),
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                text = "SIXTERN",
                style = TextStyle(
                    fontSize = 43.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 4.sp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF60A5FA), // Light Blue
                            Color(0xFF38BDF8), // Sky Blue
                            Color(0xFFA855F7), // Vibrant Purple accent for X/T
                            Color(0xFF60A5FA), // Light Blue
                        ),
                    ),
                ),
                modifier = Modifier
                    .scale(scaleAnim)
                    .alpha(alphaAnim),
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Subtitle Text
            Text(
                text = "Student Internship Experience & Evaluation App",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFCBD5E1).copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                letterSpacing = 0.4.sp,
                lineHeight = 18.sp,
                modifier = Modifier
                    .alpha(alphaAnim)
                    .padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Tap anywhere to skip",
                fontSize = 11.sp,
                color = Color(0xFF64748B).copy(alpha = 0.6f),
                modifier = Modifier
                    .alpha(alphaAnim)
                    .padding(bottom = 24.dp),
            )
        }
    }
}

@Composable
private fun ConstellationBackgroundCanvas(
    rotationAngle: Float,
    timePhase: Float,
    glowAlpha: Float,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        //Base Gradient Background
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1E2942), // Dark slate blue top
                    Color(0xFF111827), // Deep navy center
                    Color(0xFF090D16), // Midnight dark navy bottom
                ),
            ),
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFF2563EB).copy(alpha = 0.25f),
                    Color.Transparent,
                ),
                center = Offset(width * 0.2f, height * 0.15f),
                radius = width * 0.8f,
            ),
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFF7C3AED).copy(alpha = 0.15f * glowAlpha),
                    Color.Transparent,
                ),
                center = Offset(width * 0.5f, height * 0.45f),
                radius = width * 0.6f,
            ),
        )

        // Animated Floating Geometric Polygons
        rotate(degrees = rotationAngle * 0.3f, pivot = Offset(width * 0.3f, height * 0.25f)) {
            val p1 = Offset(width * 0.15f, height * 0.15f)
            val p2 = Offset(width * 0.32f, (height * 0.12f) + (sin(timePhase) * 10f))
            val p3 = Offset(width * 0.43f, height * 0.21f)
            val p4 = Offset(width * 0.25f, (height * 0.27f) + (cos(timePhase) * 10f))

            val path = Path().apply {
                moveTo(p1.x, p1.y)
                lineTo(p2.x, p2.y)
                lineTo(p3.x, p3.y)
                lineTo(p4.x, p4.y)
                close()
            }

            drawPath(
                path = path,
                color = Color(0xFF60A5FA).copy(alpha = 0.18f),
                style = Stroke(width = 1.2.dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)),
            )

            listOf(p1, p2, p3, p4).forEach { pt ->
                drawCircle(color = Color(0xFF93C5FD).copy(alpha = 0.4f), radius = 5.dp.toPx(), center = pt)
                drawCircle(color = Color.White, radius = 2.5.dp.toPx(), center = pt)
            }
        }

        rotate(degrees = -rotationAngle * 0.2f, pivot = Offset(width * 0.75f, height * 0.35f)) {
            val p1 = Offset(width * 0.69f, (height * 0.29f) + (cos(timePhase + 1f) * 8f))
            val p2 = Offset(width * 0.88f, height * 0.26f)
            val p3 = Offset(width * 0.82f, (height * 0.44f) + (sin(timePhase + 1f) * 8f))
            val p4 = Offset(width * 0.62f, height * 0.41f)

            val path = Path().apply {
                moveTo(p1.x, p1.y)
                lineTo(p2.x, p2.y)
                lineTo(p3.x, p3.y)
                lineTo(p4.x, p4.y)
                close()
            }

            drawPath(
                path = path,
                color = Color(0xFF93C5FD).copy(alpha = 0.15f),
                style = Stroke(width = 1.dp.toPx()),
            )

            // Node dots
            listOf(p1, p2, p3, p4).forEach { pt ->
                drawCircle(color = Color(0xFF60A5FA).copy(alpha = 0.5f), radius = 4.dp.toPx(), center = pt)
                drawCircle(color = Color.White, radius = 2.dp.toPx(), center = pt)
            }
        }

        rotate(degrees = rotationAngle * 0.15f, pivot = Offset(width * 0.72f, height * 0.62f)) {
            val p1 = Offset(width * 0.72f, height * 0.57f)
            val p2 = Offset(width * 0.88f, (height * 0.55f) + (sin(timePhase + 2f) * 12f))
            val p3 = Offset(width * 0.83f, height * 0.67f)
            val p4 = Offset(width * 0.65f, (height * 0.66f) + (cos(timePhase + 2f) * 12f))

            val path = Path().apply {
                moveTo(p1.x, p1.y)
                lineTo(p2.x, p2.y)
                lineTo(p3.x, p3.y)
                lineTo(p4.x, p4.y)
                close()
            }

            drawPath(
                path = path,
                color = Color(0xFFA855F7).copy(alpha = 0.12f),
                style = Stroke(width = 1.dp.toPx()),
            )

            listOf(p1, p2, p3, p4).forEach { pt ->
                drawCircle(color = Color(0xFFC084FC).copy(alpha = 0.4f), radius = 4.dp.toPx(), center = pt)
                drawCircle(color = Color.White, radius = 2.dp.toPx(), center = pt)
            }
        }

        val nodes = listOf(
            Offset((width * 0.28f) + (sin(timePhase * 0.8f) * 15f), (height * 0.42f) + (cos(timePhase * 0.8f) * 15f)),
            Offset((width * 0.18f) + (cos(timePhase * 0.6f) * 10f), (height * 0.52f) + (sin(timePhase * 0.6f) * 10f)),
            Offset((width * 0.35f) + (sin(timePhase * 1.1f) * 12f), (height * 0.62f) + (cos(timePhase * 1.1f) * 12f)),
            Offset((width * 0.78f) + (cos(timePhase * 0.7f) * 14f), (height * 0.78f) + (sin(timePhase * 0.7f) * 14f)),
            Offset((width * 0.52f) + (sin(timePhase * 0.9f) * 10f), (height * 0.22f) + (cos(timePhase * 0.9f) * 10f)),
        )

        for (i in nodes.indices) {
            for (j in (i + 1) until nodes.size) {
                val dx = nodes[i].x - nodes[j].x
                val dy = nodes[i].y - nodes[j].y
                val dist = kotlin.math.sqrt(dx * dx + dy * dy)
                if (dist < width * 0.55f) {
                    val lineAlpha = ((1f - (dist / (width * 0.55f))) * 0.2f).coerceIn(0f, 0.2f)
                    drawLine(
                        color = Color(0xFF93C5FD).copy(alpha = lineAlpha),
                        start = nodes[i],
                        end = nodes[j],
                        strokeWidth = 1.dp.toPx(),
                    )
                }
            }

            drawCircle(
                color = Color(0xFF38BDF8).copy(alpha = 0.4f * glowAlpha),
                radius = 6.dp.toPx(),
                center = nodes[i],
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.9f),
                radius = 2.5.dp.toPx(),
                center = nodes[i],
            )
        }
    }
}
