package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.components.ReceiptRow
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.*

@Composable
fun SummaryScreen(uiState: OrderUiState, onReset: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(PrimaryBlue).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(modifier = Modifier.size(100.dp), shape = CircleShape, color = Color.White.copy(alpha = 0.2f)) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White, modifier = Modifier.padding(16.dp).fillMaxSize())
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Order Confirmed!", style = MaterialTheme.typography.headlineMedium, color = Color.White, fontWeight = FontWeight.Bold)
        Text("Show this receipt at the cafe to pay.", color = Color.White.copy(alpha = 0.8f))

        Spacer(modifier = Modifier.height(32.dp))

        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(28.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("RECEIPT DETAILS", style = MaterialTheme.typography.labelLarge, color = PrimaryBlue)
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), thickness = 1.dp, color = LightSurface)

                ReceiptRow(label = "Student", value = uiState.customerName)
                ReceiptRow(label = "Note", value = uiState.deliveryNote.ifBlank { "None" })

                Spacer(modifier = Modifier.height(12.dp))
                Text("Ordered Items:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).background(LightSurface, RoundedCornerShape(12.dp)).padding(12.dp)) {
                    uiState.cartItems.forEach { item ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(item.name, fontSize = 14.sp)
                            Text(String.format("RM %.2f", item.price), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onReset,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text("Back to Home", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}