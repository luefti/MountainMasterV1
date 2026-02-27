package com.example.mountainmasterv1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    // State für die Eingaben
    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Funktionen zum Aktualisieren der Eingaben
    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    // Register-Funktion
    fun onRegisterClick() {
        if (validateInputs()) {
            viewModelScope.launch {
                isLoading = true
                errorMessage = null

                // Hier kommt später die echte Registrierung
                Log.d("MountainMaster", "Registrierungs-Versuch mit:")
                Log.d("MountainMaster", "Username: $username")
                Log.d("MountainMaster", "Email: $email")
                Log.d("MountainMaster", "Password: $password")

                // Simuliere Netzwerkaufruf
                delay(2000)

                // Beispiel: Erfolgreiche Registrierung
                Log.d("MountainMaster", "Registrierung erfolgreich!")

                isLoading = false
            }
        }
    }

    // Validierung
    private fun validateInputs(): Boolean {
        return when {
            username.isBlank() -> {
                errorMessage = "Bitte Benutzernamen eingeben"
                false
            }
            email.isBlank() -> {
                errorMessage = "Bitte E-Mail eingeben"
                false
            }
            password.length < 8 -> {
                errorMessage = "Passwort muss mindestens 8 Zeichen lang sein"
                false
            }
            else -> true
        }
    }
}