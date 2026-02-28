package com.example.mountainmasterv1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HeaderSection(
    topPadding: Int = 0  // NEU: Parameter für zusätzlichen Abstand nach oben
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
            .background(Color(0xFF5B4BC4))
    ) {
        // Kreis + Logo Stack - jetzt mit anpassbarem padding
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)  // Am oberen Rand ausrichten
                .padding(top = topPadding.dp),  // Abstand von oben anpassbar
            contentAlignment = Alignment.Center
        ) {
            // Großer transparenter Kreis
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f))
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