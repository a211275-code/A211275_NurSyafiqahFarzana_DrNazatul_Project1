package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.R
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.LightSurface
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.PrimaryBlue

data class HistoryItem(
    val date: String,
    val vendor: String,
    val price: Double,
    val status: String,
    val details: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(onBack: () -> Unit, onAddToCart: (FoodItem) -> Unit, onGoToCart: () -> Unit) {
    val historyList = listOf(
        HistoryItem("17 May 2026, 5:30 PM", "Cafe Kolej Pendeta Za'ba", 3.50, "Completed", "Nasi Ayam Geprek"),
        HistoryItem("15 May 2026, 6:00 PM", "Pusanika Food Court", 2.00, "Completed", "Roti Canai"),
        HistoryItem("11 May 2026, 4:15 PM", "Danau Golf Cafe", 5.00, "Completed", "Chicken Chop")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order History", fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(LightSurface)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(historyList) { item ->
                OrderHistoryCard(item, onAddToCart, onGoToCart)
            }
        }
    }
}

@Composable
fun OrderHistoryCard(item: HistoryItem, onAddToCart: (FoodItem) -> Unit, onGoToCart: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(48.dp).background(LightSurface, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = PrimaryBlue)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(item.vendor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(item.date, fontSize = 13.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(item.details, fontSize = 14.sp, color = Color.DarkGray, modifier = Modifier.weight(1f))
                Text(text = String.format("RM %.2f", item.price), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), thickness = 1.dp, color = LightSurface)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.status, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PrimaryBlue)

                OutlinedButton(
                    onClick = {
                        val cleanedName = item.details.substringAfter("x ")

                        val reorderedItem = FoodItem(
                            id = item.hashCode(), // Using a pure memory hash instead of database incrementers
                            name = cleanedName,
                            price = item.price,
                            vendor = item.vendor,
                            imageResId = R.drawable.nasilemak
                        )

                        onAddToCart(reorderedItem)
                        onGoToCart()
                    },
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text("Reorder", fontSize = 12.sp, color = PrimaryBlue, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}