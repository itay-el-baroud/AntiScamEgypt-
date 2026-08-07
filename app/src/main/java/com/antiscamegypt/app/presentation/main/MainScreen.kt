package com.antiscamegypt.app.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.antiscamegypt.app.presentation.home.HomeScreen

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf(
        BottomNavItem("الرئيسية", Icons.Default.Home),
        BottomNavItem("البحث", Icons.Default.Search),
        BottomNavItem("البلاغات", Icons.Default.Report),
        BottomNavItem("المجتمع", Icons.Default.People),
        BottomNavItem("الحساب", Icons.Default.Person)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> HomeScreen(
                onNavigateToSearch = { selectedTab = 1 },
                onNavigateToNotifications = { /* TODO */ },
                onNavigateToSettings = { /* TODO */ }
            )
            1 -> SearchPlaceholder()
            2 -> ReportsPlaceholder()
            3 -> CommunityPlaceholder()
            4 -> ProfilePlaceholder()
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector
)

@Composable
fun SearchPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("البحث - قريباً")
    }
}

@Composable
fun ReportsPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("البلاغات - قريباً")
    }
}

@Composable
fun CommunityPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("المجتمع - قريباً")
    }
}

@Composable
fun ProfilePlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("الحساب - قريباً")
    }
}
