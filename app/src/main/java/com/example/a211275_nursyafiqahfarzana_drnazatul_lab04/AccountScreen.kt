package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.PrimaryBlue
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.LightSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(onGoToHistory: () -> Unit, onBack: () -> Unit) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(LightSurface)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = PrimaryBlue.copy(alpha = 0.2f)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "User", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "My Progress",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ImpactCard(title = "Meals Bought", value = "8", icon = Icons.Default.ShoppingCart)
                ImpactCard(title = "Money Saved", value = "RM 45", icon = Icons.Default.Star)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column {
                    ListItem(
                        headlineContent = { Text("Order History") },
                        leadingContent = { Icon(Icons.Default.List, null, tint = PrimaryBlue) },
                        modifier = Modifier.clickable { onGoToHistory() }
                    )
                    HorizontalDivider()
                    ListItem(
                        headlineContent = { Text("Log Out", color = Color.Red) },
                        leadingContent = { Icon(Icons.Default.ExitToApp, null, tint = Color.Red) },
                        modifier = Modifier.clickable { showLogoutDialog = true }
                    )
                }
            }
        }

        if (showLogoutDialog) {
            AlertDialog(
                onDismissRequest = { showLogoutDialog = false },
                title = { Text("Confirm Log Out") },
                text = { Text("Are you sure you want to log out of your account?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showLogoutDialog = false
                            onBack()
                        }
                    ) {
                        Text("Yes, Log Out", color = Color.Red, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showLogoutDialog = false }) {
                        Text("Cancel", color = PrimaryBlue)
                    }
                }
            )
        }
    }
}

@Composable
fun RowScope.ImpactCard(title: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        modifier = Modifier
            .weight(1f)
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = PrimaryBlue)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = PrimaryBlue)
            Text(text = title, fontSize = 11.sp, color = Color.Gray)
        }
    }
}