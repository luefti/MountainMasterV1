package com.example.mountainmasterv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mountainmasterv1.ui.theme.MountainMasterV1Theme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MountainMasterV1Theme {
                // Hier verbinden wir Design und Logik
                val viewModel: LoginViewModel = viewModel()

                LoginScreen(
                    email = viewModel.email,
                    onEmailChange = viewModel::onEmailChange,
                    password = viewModel.password,
                    onPasswordChange = viewModel::onPasswordChange,
                    onLoginClick = viewModel::onLoginClick
                )
            }
        }
    }
}