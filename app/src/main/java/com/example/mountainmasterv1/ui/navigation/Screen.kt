package com.example.mountainmasterv1.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object MailVerify : Screen("mail_verify/{email}") {
        fun passEmail(email: String): String = "mail_verify/$email"
    }
}