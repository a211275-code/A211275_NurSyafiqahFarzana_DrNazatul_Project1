/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.components.ReceiptRow

// Local fallback colors in case they aren't parsed from your theme files
private val PrimaryBlue = Color(0xFF1E88E5)
private val LightSurface = Color(0xFFA0A0A0).copy(alpha = 0.1f)

@Composable
fun SummaryScreen(
    uiState: OrderUiState,
    cartItemsFromDb: List<FoodItem>, // Live tracking data passed directly from Room via NavHost
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.2f)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Order Confirmed!",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Show this receipt at the cafe to pay.",
            color = Color.White.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "RECEIPT DETAILS",
                    style = MaterialTheme.typography.labelLarge,
                    color = PrimaryBlue
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = LightSurface
                )

                ReceiptRow(label = "Student", value = uiState.customerName)
                ReceiptRow(label = "Note", value = uiState.deliveryNote.ifBlank { "None" })

                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Ordered Items:", fontWeight = FontWeight.Bold, fontSize = 14.sp)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(LightSurface, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    // FIXED: Now loops through the genuine room database list (cartItemsFromDb)
                    // instead of the empty uiState local list.
                    cartItemsFromDb.forEach { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.name, fontSize = 14.sp)
                            Text(
                                text = String.format("RM %.2f", item.price),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onReset,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                ) {
                    Text(text = "Back to Home", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}