package com.example.mountainmasterv1.ui.screens.mailVerify

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mountainmasterv1.ui.components.HeaderSection

@Composable
fun MailVerifyScreen(
    email: String,
    onVerifyClick: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    // State für die 6 Ziffern
    val code = remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderSection(140)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color(0xFFF2F2F2))
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Verify Your Email",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Check your Inbox",
                color = Color.Gray,
            )

            Text(
                text = email,
                color = Color(0xFF5B4BC4),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Code Input Field - HIER wird die Funktion aufgerufen
            CodeInputField(
                code = code.value,
                onCodeChange = { newCode ->
                    code.value = newCode

                    // Automatisch weiter zum nächsten Feld
                    for (i in 0 until 5) {
                        if (newCode[i].isNotEmpty() && newCode[i + 1].isEmpty()) {
                            focusRequesters[i + 1].requestFocus()
                            break
                        }
                    }
                },
                focusRequesters = focusRequesters
            )

            Spacer(modifier = Modifier.height(24.dp))

            // CONTINUE BUTTON
            Button(
                onClick = {
                    val fullCode = code.value.joinToString("")
                    onVerifyClick(fullCode)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B4BC4),
                    disabledContainerColor = Color.Gray
                ),
                enabled = code.value.all { it.isNotEmpty() }
            ) {
                Text("Continue")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Back Link
            Text(
                text = "Back to Register",
                color = Color(0xFF5B4BC4),
                modifier = Modifier.clickable { onNavigateBack() }
            )
        }
    }
}

// WICHTIG: Diese Funktionen müssen im gleichen File sein!
@Composable
fun CodeInputField(
    code: List<String>,
    onCodeChange: (List<String>) -> Unit,
    focusRequesters: List<FocusRequester>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Erste 3 Ziffern
        for (i in 0..2) {
            DigitTextField(
                value = code[i],
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && (newValue.isEmpty() || newValue[0].isDigit())) {
                        val newCode = code.toMutableList()
                        newCode[i] = newValue
                        onCodeChange(newCode)
                    }
                },
                focusRequester = focusRequesters[i],
                onNext = {
                    if (i < 5 && code[i].isNotEmpty()) {
                        focusRequesters[i + 1].requestFocus()
                    }
                },
                onPrevious = {
                    if (i > 0) {
                        focusRequesters[i - 1].requestFocus()
                    }
                }
            )
        }

        // Bindestrich
        Text(
            text = "-",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5B4BC4),
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        // Letzte 3 Ziffern
        for (i in 3..5) {
            DigitTextField(
                value = code[i],
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && (newValue.isEmpty() || newValue[0].isDigit())) {
                        val newCode = code.toMutableList()
                        newCode[i] = newValue
                        onCodeChange(newCode)
                    }
                },
                focusRequester = focusRequesters[i],
                onNext = {
                    if (i < 5 && code[i].isNotEmpty()) {
                        focusRequesters[i + 1].requestFocus()
                    }
                },
                onPrevious = {
                    if (i > 0) {
                        focusRequesters[i - 1].requestFocus()
                    }
                }
            )
        }
    }
}

@Composable
fun DigitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(40.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = if (isFocused) 2.dp else 1.dp,
                color = if (isFocused) Color(0xFF5B4BC4) else Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5B4BC4)
            ),
            cursorBrush = SolidColor(Color(0xFF5B4BC4)),
            maxLines = 1,
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)  // Kein extra Padding
                ) {
                    innerTextField()
                }
            }
        )
    }
}