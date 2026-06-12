package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(onConfirm: (String, String) -> Unit, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order Details", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) } },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = LightSurface)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().background(LightSurface).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = PrimaryBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Collection Info", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }

            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Student Details", fontWeight = FontWeight.SemiBold, color = Color.Gray)
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        placeholder = { Text("E.g. Siti Bin Rais") },
                        leadingIcon = { Icon(Icons.Default.Person, null, tint = PrimaryBlue) },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                }
            }

            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Message to Cafe", fontWeight = FontWeight.SemiBold, color = Color.Gray)
                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        label = { Text("Pickup ETA") },
                        placeholder = { Text("E.g. I will pick this up at 5:30 PM") },
                        leadingIcon = { Icon(Icons.Default.Edit, null, tint = PrimaryBlue) },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        minLines = 3,
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onConfirm(name, note) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = name.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, disabledContainerColor = Color.LightGray)
            ) {
                Text("Confirm Order", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}