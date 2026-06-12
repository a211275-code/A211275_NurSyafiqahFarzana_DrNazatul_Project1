package com.example.a211275_nursyafiqahfarzana_drnazatul_lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a211275_nursyafiqahfarzana_drnazatul_lab04.ui.theme.FoodAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                // Initialize the clean, repository-free ViewModel directly
                val viewModel: FoodViewModel = viewModel()

                // Pass the single shared state ViewModel instance into your Navigation tree
                FoodAppNavigation(viewModel = viewModel)
            }
        }
    }
}

//data class FoodItem(val id: Int, val name: String, val price: String, val vendor: String)
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun FoodAppInterface() {
//    var searchQuery by remember { mutableStateOf("") }
//    // State to track if we are in "Search Mode"
//    var isSearchMode by remember { mutableStateOf(false) }
//
//    val pagerState = rememberPagerState(pageCount = { 3 })
//    val scope = rememberCoroutineScope()
//
//    val foodItems = listOf(
//        FoodItem(1, "Fried Kuey Teow", "RM 12.80", "Cafe Mint"),
//        FoodItem(2, "Nasi Lemak Special", "RM 8.50", "Warung Kita"),
//        FoodItem(3, "Chicken Bento", "RM 18.00", "Sushido"),
//        FoodItem(4, "Beef Burger", "RM 15.00", "Burger King")
//    )
//
//    val filteredItems = foodItems.filter { it.name.contains(searchQuery, ignoreCase = true) }
//
//    Scaffold(
//        topBar = {
//            Column(modifier = Modifier.background(LightSurface).padding(top = 35.dp)) {
//                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
//                    // Back Button appears only during search
//                    if (isSearchMode) {
//                        IconButton(onClick = {
//                            isSearchMode = false
//                            searchQuery = ""
//                        }) {
//                            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = PrimaryBlue)
//                        }
//                    } else {
//                        Icon(Icons.Default.LocationOn, null, tint = PrimaryBlue)
//                    }
//
//                    Text(
//                        if (isSearchMode) "Search Results" else "Danau Golf UKM...",
//                        Modifier.weight(1f).padding(start = 8.dp)
//                    )
//
//                    if (!isSearchMode) Icon(Icons.Default.Notifications, null)
//                }
//
//                OutlinedTextField(
//                    value = searchQuery,
//                    onValueChange = {
//                        searchQuery = it
//                        if (it.isNotEmpty()) isSearchMode = true
//                    },
//                    placeholder = { Text("Search stores and food") },
//                    modifier = Modifier.fillMaxWidth().padding(16.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    leadingIcon = { Icon(Icons.Default.Search, null) },
//                    trailingIcon = {
//                        if (searchQuery.isNotEmpty()) {
//                            IconButton(onClick = { searchQuery = "" }) { Icon(Icons.Default.Close, null) }
//                        }
//                    },
//                    colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White)
//                )
//            }
//        },
//        bottomBar = {
//            NavigationBar(containerColor = Color.White) {
//                NavigationBarItem(selected = !isSearchMode, onClick = { isSearchMode = false }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("HOME") })
//                NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.ShoppingCart, null) }, label = { Text("CART") })
//                NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.List, null) }, label = { Text("ORDERS") })
//                NavigationBarItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Person, null) }, label = { Text("ACCOUNT") })
//            }
//        }
//    ) { innerPadding ->
//        LazyColumn(Modifier.padding(innerPadding).fillMaxSize()) {
//
//            // IF SEARCH MODE IS ACTIVE, HIDE BANNER AND CATEGORIES
//            if (!isSearchMode) {
//                // 1. Promo Banner
//                item {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Box(Modifier.padding(16.dp)) {
//                            HorizontalPager(state = pagerState, modifier = Modifier.height(150.dp).fillMaxWidth()) { page ->
//                                Card(colors = CardDefaults.cardColors(containerColor = PrimaryBlue), shape = RoundedCornerShape(16.dp)) {
//                                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                                        Text("PROMOTION ${page + 1}", color = Color.White, fontWeight = FontWeight.Bold)
//                                    }
//                                }
//                            }
//                            Row(Modifier.fillMaxWidth().align(Alignment.Center), Arrangement.SpaceBetween) {
//                                IconButton(onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) } }) {
//                                    Icon(Icons.Default.KeyboardArrowLeft, null, tint = Color.White)
//                                }
//                                IconButton(onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }) {
//                                    Icon(Icons.Default.KeyboardArrowRight, null, tint = Color.White)
//                                }
//                            }
//                        }
//                        // Dot Indicators
//                        Row(Modifier.padding(bottom = 12.dp)) {
//                            repeat(3) { i ->
//                                val color = if (pagerState.currentPage == i) PrimaryBlue else Color.LightGray
//                                Box(Modifier.padding(3.dp).clip(CircleShape).background(color).size(8.dp))
//                            }
//                        }
//                    }
//                }
//
//                // 2. Categories
//                item {
//                    Text("Categories", fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
//                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
//                        CategoryCircle("Meals")
//                        CategoryWidget("Drinks")
//                        CategoryWidget("Dessert")
//                        CategoryWidget("Snacks")
//                    }
//                    Spacer(Modifier.height(20.dp))
//                    Text("Near to You", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
//                }
//            }
//
//            // 3. The List (Visible in both modes, but filtered)
//            items(filteredItems) { item ->
//                ExpandableFoodCard(item)
//            }
//
//            if (filteredItems.isEmpty()) {
//                item {
//                    Box(Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
//                        Text("No items found for \"$searchQuery\"", color = Color.Gray)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable fun CategoryWidget(n: String) = CategoryCircle(n) // Shortcut
//
//@Composable
//fun CategoryCircle(name: String) {
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Box(Modifier.size(60.dp).clip(CircleShape).background(LightSurface).border(1.dp, PrimaryBlue, CircleShape))
//        Text(name, fontSize = 12.sp)
//    }
//}
//
//@Composable
//fun ExpandableFoodCard(item: FoodItem) {
//    var expanded by remember { mutableStateOf(false) }
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).clickable { expanded = !expanded }.animateContentSize(),
//        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Column(Modifier.padding(12.dp)) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Box(Modifier.size(60.dp).background(LightSurface, RoundedCornerShape(8.dp)))
//                Column(Modifier.padding(start = 12.dp).weight(1f)) {
//                    Text(item.name, fontWeight = FontWeight.Bold)
//                    Text(item.price, color = PrimaryBlue)
//                    Text(item.vendor, fontSize = 12.sp, color = Color.Gray)
//                }
//                Icon(if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, null)
//            }
//            AnimatedVisibility(visible = expanded) {
//                Column {
//                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
//                    Text("Freshly prepared. Estimated delivery: 20-30 mins.")
//                    Button(onClick = {}, Modifier.align(Alignment.End)) { Text("Order") }
//                }
//            }
//        }
//    }
//}