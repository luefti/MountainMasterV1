package com.example.mountainmasterv1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // State für die Eingaben
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Funktionen zum Aktualisieren der Eingaben
    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    // Login-Funktion
    fun onLoginClick() {
        if (validateInputs()) {
            viewModelScope.launch {
                isLoading = true
                errorMessage = null

                // Hier kommt später die echte Authentifizierung
                Log.d("MountainMaster", "Login-Versuch mit:")
                Log.d("MountainMaster", "Email: $email")
                Log.d("MountainMaster", "Password: $password")

                // Simuliere Netzwerkaufruf
                delay(2000)

                // Beispiel: Erfolgreicher Login nur mit Testdaten
                if (email == "test@test.com" && password == "123456") {
                    Log.d("MountainMaster", "Login erfolgreich!")
                } else {
                    errorMessage = "Falsche E-Mail oder Passwort"
                }

                isLoading = false
            }
        }
    }

    // Validierung
    private fun validateInputs(): Boolean {
        return when {
            email.isBlank() -> {
                errorMessage = "Bitte E-Mail eingeben"
                false
            }
            password.isBlank() -> {
                errorMessage = "Bitte Passwort eingeben"
                false
            }
            else -> true
        }
    }
}