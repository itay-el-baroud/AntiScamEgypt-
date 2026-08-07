package com.antiscamegypt.app.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.antiscamegypt.app.ui.theme.GreenSafe
import com.antiscamegypt.app.ui.theme.RedPrimary
import com.antiscamegypt.app.ui.theme.YellowWarning

@Composable
fun NumberDetailScreen(
    phoneNumber: String,
    onNavigateBack: () -> Unit,
    onAddReport: (String) -> Unit,
    onBlockNumber: (String) -> Unit
) {
    // Demo data - هنربطها بالـ API بعدين
    val riskScore = remember { (0..100).random() }
    val reportsCount = remember { (0..50).random() }

    val riskColor = when {
        riskScore >= 80 -> RedPrimary
        riskScore >= 50 -> YellowWarning
        else -> GreenSafe
    }

    val riskText = when {
        riskScore >= 80 -> "خطر جداً"
        riskScore >= 50 -> "تحذير"
        else -> "آمن"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("تفاصيل الرقم") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "رجوع"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Phone Number Card
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = phoneNumber,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Risk Score
                    Surface(
                        color = riskColor.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "$riskScore% - $riskText",
                            style = MaterialTheme.typography.titleLarge,
                            color = riskColor,
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$reportsCount بلاغ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Actions
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ActionButton(
                    text = "إضافة بلاغ",
                    icon = Icons.Default.Report,
                    onClick = { onAddReport(phoneNumber) }
                )

                ActionButton(
                    text = "حظر الرقم",
                    icon = Icons.Default.Block,
                    onClick = { onBlockNumber(phoneNumber) }
                )

                ActionButton(
                    text = "مشاركة",
                    icon = Icons.Default.Share,
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}
