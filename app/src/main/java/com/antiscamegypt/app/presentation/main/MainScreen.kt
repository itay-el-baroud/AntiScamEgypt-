package com.antiscamegypt.app.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antiscamegypt.app.presentation.home.HomeScreen
import com.antiscamegypt.app.presentation.search.NumberDetailScreen
import com.antiscamegypt.app.presentation.search.SearchScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
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
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> HomeScreen(
                    onNavigateToSearch = { selectedTab = 1 },
                    onNavigateToNotifications = { /* TODO */ },
                    onNavigateToSettings = { /* TODO */ }
                )
                1 -> SearchNavHost()
                2 -> ReportsPlaceholder()
                3 -> CommunityPlaceholder()
                4 -> ProfilePlaceholder()
            }
        }
    }
}

@Composable
fun SearchNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreen(
                onNavigateBack = { /* Bottom nav handles this */ },
                onNumberSelected = { phoneNumber ->
                    navController.navigate("detail/$phoneNumber")
                }
            )
        }

        composable("detail/{phoneNumber}") { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            NumberDetailScreen(
                phoneNumber = phoneNumber,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onAddReport = { /* TODO */ },
                onBlockNumber = { /* TODO */ }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector
)

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
