package com.example.mountainmasterv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mountainmasterv1.navigation.Screen
import com.example.mountainmasterv1.ui.screens.login.LoginScreen
import com.example.mountainmasterv1.ui.screens.login.LoginViewModel
import com.example.mountainmasterv1.ui.screens.mailVerify.MailVerifyScreen
import com.example.mountainmasterv1.ui.screens.register.RegisterScreen
import com.example.mountainmasterv1.ui.screens.register.RegisterViewModel
import com.example.mountainmasterv1.ui.theme.MountainMasterV1Theme

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
                            onNavigateToMailVerify = { email ->
                                navController.navigate(Screen.MailVerify.passEmail(email))
                            },
                            onNavigateToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(
                        route = Screen.MailVerify.route,
                        arguments = listOf(navArgument("email") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""

                        MailVerifyScreen(
                            email = email,
                            onVerifyClick = { code ->
                                // Hier kommt die Verifikations-Logik
                                println("Verifying code: $code for $email")
                                // Bei Erfolg: navController.navigate("home")
                            },
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}