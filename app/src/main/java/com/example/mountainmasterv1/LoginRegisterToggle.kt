package com.example.mountainmasterv1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoginRegisterToggle(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    isLoginScreen: Boolean
) {
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
                .fillMaxHeight()
                .then(
                    if (!isLoginScreen) {
                        Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF5B4BC4))
                    } else Modifier
                )
                .clickable { onRegisterClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Register",
                color = if (!isLoginScreen) Color.White else Color(0xFF5B4BC4)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .then(
                    if (isLoginScreen) {
                        Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF5B4BC4))
                    } else Modifier
                )
                .clickable { onLoginClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Login",
                color = if (isLoginScreen) Color.White else Color(0xFF5B4BC4)
            )
        }
    }
}