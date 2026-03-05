package com.example.mountainmasterv1.ui.components

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
import com.example.mountainmasterv1.R

@Composable
fun HeaderSection(
    topPadding: Int = 0
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
            .background(Color(0xFF5B4BC4))
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = topPadding.dp),
            contentAlignment = Alignment.Center
        ) {
            // Großer Kreis
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