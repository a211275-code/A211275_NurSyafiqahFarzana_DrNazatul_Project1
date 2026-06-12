package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.foundation.clickable // 👈 ADD THIS IMPORT
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.OrderUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    uiState: OrderUiState,
    onRemoveFavourite: (FoodItem) -> Unit,
    onItemClick: (FoodItem) -> Unit, // 👈 ADD THIS: To handle checkout navigation
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            FoodTopAppBar(
                title = "My Favourites",
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { p ->
        Column(modifier = Modifier.padding(p).fillMaxSize().padding(16.dp)) {
            if (uiState.favouriteItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No favourites yet. Start exploring!", color = Color.Gray)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(
                        items = uiState.favouriteItems,
                        key = { item -> item.id }
                    ) { item ->
                        ListItem(
                            headlineContent = { Text(item.name) },
                            // 👇 ADD THIS: Makes the row clickable to send you to checkout!
                            modifier = Modifier.clickable { onItemClick(item) },
                            trailingContent = {
                                IconButton(onClick = { onRemoveFavourite(item) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remove Favourite",
                                        tint = Color.Red
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}