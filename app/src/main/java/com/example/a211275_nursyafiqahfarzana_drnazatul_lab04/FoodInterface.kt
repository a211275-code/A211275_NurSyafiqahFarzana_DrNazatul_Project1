package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.data.FoodItem
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Lab3FoodInterface(
    cartCount: Int,
    favouriteItems: List<FoodItem>,
    onAddToCart: (FoodItem) -> Unit,
    onToggleFavourite: (FoodItem) -> Unit,
    onGoToCart: () -> Unit,
    onGoToFavourites: () -> Unit,
    onGoToAccount: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchMode by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    val foodItems = listOf(
        FoodItem(1, "Nasi Ayam Geprek", 3.50, "Cafe Kolej Pendeta Za'ba", R.drawable.nasiayamgeprek),
        FoodItem(2, "Roti Canai ", 2.00, "Lot 10, Pusanika", R.drawable.roticanai),
        FoodItem(3, "Chicken Chop", 5.00, "Unikeb Food Court", R.drawable.chickenchop),
        FoodItem(4, "Nasi Lemak", 1.50, "Cafe D, FTSM", R.drawable.nasilemak)
    )

    val filteredItems = foodItems.filter { it.name.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(LightSurface).padding(top = 35.dp)) {
                Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    if (isSearchMode) {
                        IconButton(onClick = { isSearchMode = false; searchQuery = "" }) {
                            Icon(Icons.Default.ArrowBack, null, tint = PrimaryBlue)
                        }
                    } else {
                        Icon(Icons.Default.LocationOn, null, tint = PrimaryBlue)
                    }
                    Text(if (isSearchMode) "Search Results" else "UKM Campus...", modifier = Modifier.weight(1f).padding(start = 8.dp))

                    BadgedBox(badge = { if(cartCount > 0) Badge { Text("$cartCount") } }) {
                        IconButton(onClick = onGoToCart) { Icon(Icons.Default.ShoppingCart, null, tint = PrimaryBlue) }
                    }
                }
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it; isSearchMode = it.isNotEmpty() },
                    placeholder = { Text("Search UKM cafes & discounted meals", fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = { Icon(Icons.Default.Search, null, modifier = Modifier.size(20.dp)) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
                )
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.Home, null) }, label = { Text("HOME") })
                NavigationBarItem(selected = false, onClick = onGoToFavourites, icon = { Icon(Icons.Default.Favorite, null) }, label = { Text("SAVED") })
                NavigationBarItem(selected = false, onClick = onGoToAccount, icon = { Icon(Icons.Default.Person, null) }, label = { Text("ACCOUNT") })
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color(0xFFF5F9FF))) {
            if (!isSearchMode) {
                Box(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    HorizontalPager(state = pagerState, modifier = Modifier.height(140.dp).fillMaxWidth()) { page ->
                        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier.fillMaxSize().background(
                                    Brush.horizontalGradient(listOf(Color(0xFF1976D2), Color(0xFF42A5F5)))
                                ),
                                contentAlignment = Alignment.Center
                            ) {
                                val promos = listOf("50% OFF PUSANIKA AFTER 4PM", "RM3 STUDENT MEALS", "CLEARANCE AT CAFE KPZ")
                                Text(promos[page], color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            }
                        }
                    }
                    IconButton(
                        onClick = { scope.launch { pagerState.animateScrollToPage((pagerState.currentPage - 1).coerceAtLeast(0)) } },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(Icons.Default.KeyboardArrowLeft, null, tint = Color.White)
                    }
                    IconButton(
                        onClick = { scope.launch { pagerState.animateScrollToPage((pagerState.currentPage + 1).coerceAtMost(2)) } },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(Icons.Default.KeyboardArrowRight, null, tint = Color.White)
                    }
                }
                Text("Meals Near You", fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
            }

            LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
                items(filteredItems) { item ->
                    val isFavourite = favouriteItems.contains(item)
                    ExpandableFoodCardWithFav(item, isFavourite, onToggleFavourite, onAddToCart)
                }
            }
        }
    }
}

@Composable
fun ExpandableFoodCardWithFav(item: FoodItem, isFavourite: Boolean, onToggleFavourite: (FoodItem) -> Unit, onAddToCart: (FoodItem) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable { expanded = !expanded }.animateContentSize(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                    Text(item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(item.vendor, fontSize = 13.sp, color = Color.Gray, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(String.format("RM %.2f", item.price), fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = PrimaryBlue)
                }

                IconButton(onClick = { onToggleFavourite(item) }) {
                    Icon(imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = null, tint = if (isFavourite) Color.Red else Color.Gray)
                }
                Icon(if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, null, tint = Color.Gray)
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFEEEEEE))
                    Button(
                        onClick = { onAddToCart(item) },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                    ) {
                        Text("Add to Cart", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}