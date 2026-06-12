package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(uiState: OrderUiState, onProceed: () -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) } }
            )
        }
    ) { p ->
        Column(modifier = Modifier.padding(p).fillMaxSize().padding(16.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.cartItems) { item ->
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
                enabled = uiState.cartItems.isNotEmpty()
            ) {
                Text("Proceed to Order")
            }
        }
    }
}