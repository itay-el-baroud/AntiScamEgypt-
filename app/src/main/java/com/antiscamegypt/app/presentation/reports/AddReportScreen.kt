package com.antiscamegypt.app.presentation.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReportScreen(
    onNavigateBack: () -> Unit,
    onSubmitReport: () -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Scam") }
    var description by remember { mutableStateOf("") }

    val reportTypes = listOf("Scam", "Spam", "Fake Company")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("إضافة بلاغ") },
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
            // Phone Number
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length <= 11 && it.all { char -> char.isDigit() }) {
                        phoneNumber = it
                    }
                },
                label = { Text("رقم الهاتف") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Report Type
            Text(
                text = "نوع البلاغ",
                style = MaterialTheme.typography.titleMedium
            )

            reportTypes.forEach { type ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedType == type,
                        onClick = { selectedType = type }
                    )
                    Text(
                        text = when (type) {
                            "Scam" -> "احتيال"
                            "Spam" -> "إزعاج"
                            "Fake Company" -> "شركة وهمية"
                            else -> type
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("وصف البلاغ") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 4
            )

            // Submit Button
            Button(
                onClick = {
                    if (phoneNumber.isNotBlank() && description.isNotBlank()) {
                        onSubmitReport()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = phoneNumber.isNotBlank() && description.isNotBlank()
            ) {
                Text("إرسال البلاغ")
            }
        }
    }
}
