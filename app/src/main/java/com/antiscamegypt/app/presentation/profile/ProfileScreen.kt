package com.antiscamegypt.app.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Profile Header
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "المستخدم",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "user@email.com",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Items
        ProfileMenuItem(
            icon = Icons.Default.Edit,
            title = "تعديل البيانات",
            onClick = { /* TODO */ }
        )

        ProfileMenuItem(
            icon = Icons.Default.Lock,
            title = "تغيير كلمة المرور",
            onClick = { /* TODO */ }
        )

        ProfileMenuItem(
            icon = Icons.Default.Settings,
            title = "الإعدادات",
            onClick = { /* TODO */ }
        )

        ProfileMenuItem(
            icon = Icons.Default.History,
            title = "النشاط",
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Logout
        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = "تسجيل خروج"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("تسجيل خروج")
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title) },
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = title
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "التفاصيل"
            )
        },
        modifier = Modifier.clickable(onClick = onClick)
    )
}
