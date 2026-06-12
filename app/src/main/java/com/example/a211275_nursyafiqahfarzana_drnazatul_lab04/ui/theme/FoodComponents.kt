package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.*

@Composable
fun ExpandableFoodCard(item: FoodItem, onAddToCart: (FoodItem) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(60.dp).background(LightSurface, RoundedCornerShape(8.dp)))

                Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                    Text(text = item.name, fontWeight = FontWeight.Bold)
                    // Properly displays Double values to two decimal points
                    Text(text = "RM ${String.format("%.2f", item.price)}", color = PrimaryBlue)
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Button(
                        onClick = { onAddToCart(item) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Add to Cart")
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCircle(name: String, imageRes: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.dp, PrimaryBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(40.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = name, fontSize = 12.sp)
    }
}

@Composable
fun ReceiptRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
        Text(text = value, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
    }
}