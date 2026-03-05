package com.example.mountainmasterv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mountainmasterv1.ui.screens.login.LoginScreen
import com.example.mountainmasterv1.ui.screens.login.LoginViewModel
import com.example.mountainmasterv1.ui.screens.register.RegisterScreen
import com.example.mountainmasterv1.ui.screens.register.RegisterViewModel
import com.example.mountainmasterv1.ui.theme.MountainMasterV1Theme

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
}

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MountainMasterV1Theme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(Screen.Login.route) {
                        val viewModel: LoginViewModel = viewModel()

                        LoginScreen(
                            email = viewModel.email,
                            onEmailChange = viewModel::onEmailChange,
                            password = viewModel.password,
                            onPasswordChange = viewModel::onPasswordChange,
                            onLoginClick = viewModel::onLoginClick,
                            onNavigateToRegister = {
                                navController.navigate(Screen.Register.route)
                            }
                        )
                    }

                    composable(Screen.Register.route) {
                        val viewModel: RegisterViewModel = viewModel()

                        RegisterScreen(
                            username = viewModel.username,
                            onUsernameChange = viewModel::onUsernameChange,
                            email = viewModel.email,
                            onEmailChange = viewModel::onEmailChange,
                            password = viewModel.password,
                            onPasswordChange = viewModel::onPasswordChange,
                            onRegisterClick = viewModel::onRegisterClick,
                            onNavigateToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}