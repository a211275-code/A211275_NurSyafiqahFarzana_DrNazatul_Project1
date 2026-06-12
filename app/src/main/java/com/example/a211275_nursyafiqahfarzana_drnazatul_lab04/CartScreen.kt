package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    uiState: OrderUiState,
    cartItemsFromDb: List<FoodItem>, // 👈 ADD THIS: Read live data from Room
    onProceed: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            FoodTopAppBar(
                title = "My Cart",
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { p ->
        Column(modifier = Modifier.padding(p).fillMaxSize().padding(16.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                // 👇 CHANGE THIS: Swap uiState.cartItems for cartItemsFromDb
                items(
                    items = cartItemsFromDb,
                    key = { item -> item.id }
                ) { item ->
                    ListItem(
                        headlineContent = { Text(item.name) },
                        supportingContent = { Text(item.vendor) },
                        trailingContent = { Text(String.format("RM %.2f", item.price)) }
                    )
                }
            }
            Button(
                onClick = onProceed,
                modifier = Modifier.fillMaxWidth(),
                // 👇 CHANGE THIS: Button will light up when the DB has items
                enabled = cartItemsFromDb.isNotEmpty()
            ) {
                Text("Proceed to Order")
            }
        }
    }
}