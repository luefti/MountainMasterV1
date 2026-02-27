package com.example.mountainmasterv1

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    // Animations-Status
    var startAnimation by remember { mutableStateOf(false) }

    // Bildschirmkonfiguration für responsive Werte
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // LILA HEADER
        HeaderSection()

        // WEISSE KARTE MIT ANIMATION
        val cardHeight = screenHeight * 0.6f // Karte ist 60% der Bildschirmhöhe

        // Animation -> startet unten und stoppt in der Mitte
        val animatedY by animateDpAsState(
            targetValue = if (startAnimation)
                screenHeight * 0.4f // Stoppt bei 40% vom oberen Rand (genug Platz für Logo)
            else
                screenHeight + 100.dp, // Start außerhalb des Bildschirms (unten)
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            label = "cardAnimation"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = animatedY)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color(0xFFF2F2F2))
                .padding(horizontal = 32.dp)
                .then(Modifier.fillMaxSize()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fade-In Animation für den Inhalt
            AnimatedVisibility(
                visible = startAnimation,
                enter = fadeIn(animationSpec = tween(800, delayMillis = 500))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp), // Kleiner Padding am unteren Rand
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                }
                Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(top = 60.dp)) {
                    Text(
                        text = "Welcome back!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "You've been missed",
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // E-Mail Feld
                    OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        placeholder = { Text("E-Mail") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Passwort Feld mit Auge
                    var passwordVisible by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        placeholder = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        trailingIcon = {
                            val icon = if (passwordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff

                            val description = if (passwordVisible)
                                "Passwort verbergen"
                            else
                                "Passwort anzeigen"

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = icon, contentDescription = description)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Forgot Password?",
                        modifier = Modifier.align(Alignment.End),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B4BC4))
                    ) {
                        Text("Login")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    RegisterLoginToggle()
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
            .background(Color(0xFF5B4BC4)),
        contentAlignment = Alignment.Center
    ) {
        // Logo Container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            // Logo mit Animation
            val infiniteTransition = rememberInfiniteTransition()
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.9f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Box(contentAlignment = Alignment.Center) {
                // Großer transparenter Kreis
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f * scale))
                )

                // Innerer Kreis
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF2F2F2))
                )

                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logosvg),
                    contentDescription = null,
                    modifier = Modifier.size(130.dp)
                )
            }
        }
    }
}

@Composable
fun RegisterLoginToggle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.Transparent)
            .border(1.dp, Color.Black, RoundedCornerShape(14.dp))
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text("Register", color = Color(0xFF5B4BC4))
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFF5B4BC4)),
            contentAlignment = Alignment.Center
        ) {
            Text("Login", color = Color.White)
        }
    }
}