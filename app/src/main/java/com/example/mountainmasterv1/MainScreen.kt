package com.example.mountainmasterv1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Terrain
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Helper data class for Tab items
data class TabItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    // State to track which tab is currently selected
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    // Define the tabs for the Bottom Bar
    val items = listOf(
        TabItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        TabItem("Mountains", Icons.Filled.Terrain, Icons.Outlined.Terrain),
        TabItem("Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )

    // The purple color from your image/brand
    val brandPurple = Color(0xFF5B4BC4)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = selectedItemIndex == index

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedItemIndex = index },
                        label = { Text(text = item.title) },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            // This changes the 'pill' background color when selected
                            indicatorColor = brandPurple.copy(alpha = 0.1f),
                            // This changes the icon color to purple when selected
                            selectedIconColor = brandPurple,
                            selectedTextColor = brandPurple,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // This is where the content of each tab will be displayed
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (selectedItemIndex) {
                0 -> Text("Mountain Home Feed", style = MaterialTheme.typography.headlineMedium)
                1 -> Text("Explore the Peaks", style = MaterialTheme.typography.headlineMedium)
                2 -> Text("Your Climber Profile", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}