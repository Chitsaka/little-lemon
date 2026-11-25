package com.example.littlelemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.littlelemon.MenuDao
import com.example.littlelemon.MenuItemNetwork
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor.Black
import com.example.littlelemon.ui.theme.LittleLemonColor.Charcoal
import com.example.littlelemon.ui.theme.LittleLemonColor.Cloud
import com.example.littlelemon.ui.theme.LittleLemonColor.Gray
import com.example.littlelemon.ui.theme.LittleLemonColor.Green
import com.example.littlelemon.ui.theme.LittleLemonColor.White
import com.example.littlelemon.ui.theme.LittleLemonColor.Yellow

@Composable
fun RestaurantName(name: String, color: Color, size: Int) {
    Text(
        text = name,
        color = color,
        fontSize = size.sp
    )
}

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    menuDao: MenuDao
) {
    val menuLiveData = remember { menuDao.getAllMenuItems() }
    val menuItems by menuLiveData.observeAsState(emptyList())
    var searchPhrase by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val filteredMenuItems = menuItems.filter { dish ->

        val matchesCategory =
            selectedCategory == "All" ||
                    dish.category.equals(selectedCategory, ignoreCase = true)

        val matchesSearch =
            dish.title.contains(searchPhrase, ignoreCase = true) ||
                    dish.description.contains(searchPhrase, ignoreCase = true)

        matchesCategory && matchesSearch
    }

    Column(
        modifier=Modifier.fillMaxSize()
    ){
        Row( modifier = Modifier
            .fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,

        ) {
            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo",
                    modifier = Modifier.size(width = 120.dp, height = 40.dp)
                )
            }
            IconButton(
                onClick = onProfileClick,
                modifier = Modifier.size(56.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Open Profile",
                    modifier = Modifier.size(48.dp).clip(
                        RoundedCornerShape(50)
                    )
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Green)
                .padding(16.dp)
        ) {

            RestaurantName(
                name = stringResource(id= R.string.title ),
                Yellow,
                size = 36
            )


            Text(
                text = "Chicago",
                fontSize = 24.sp,
                color = Cloud
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Description + image row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    modifier = Modifier.width(200.dp)
                        .weight(1f)
                        .padding(end = 8.dp),
                    color = Cloud,
                    fontSize = 21.sp

                )

                Image(
                    painter = painterResource(id = R.drawable.upperpanelimage),
                    contentDescription = "Restaurant Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(120.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Start

            ) {
                TextField(
                    value = searchPhrase,
                    onValueChange = { searchPhrase = it },
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)).background(White),
                    placeholder = {
                        Text("Enter Search Phrase")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true
                )

            }
        }

        Column (modifier=Modifier.background(Cloud).fillMaxHeight()){
            MenuContent(
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )
            if (filteredMenuItems.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    Text(
                        text = "No dishes found.",
                        fontSize = 28.sp,
                        color = Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(filteredMenuItems) { dish ->
                        MenuDish(
                            modifier = Modifier,
                            dish = dish,
                            onClick = {
                                // Handle dish click
                            }
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun MenuContent(selectedCategory: String,
                onCategorySelected: (String) -> Unit) {

    val categories = listOf(
        "All",
        "Starters",
        "Mains",
        "Desserts",
        "Drinks"
    )
    Row{
        Text(text="ORDER FOR DELIVERY!",
            fontWeight=FontWeight.Bold,
            fontSize=22.sp,
            color=Black,
            modifier=Modifier.fillMaxWidth().padding(vertical = 8.dp,horizontal = 16.dp)
        )
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,

        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(categories) { category ->
            val isSelected = selectedCategory == category
            Card(
                Modifier.clickable {
                    onCategorySelected(category)
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Green
                    else Color(0xFFDAD6D6)
                )
            ) {
                Text(
                    text = category,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    ),
                    color = if (isSelected) White else Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MenuDish(
    modifier: Modifier = Modifier,
    dish: MenuItemNetwork,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = dish.title,
                    fontWeight=FontWeight.Medium,
                    style = MaterialTheme.typography.headlineSmall,
                    color=Charcoal
                )

                Text(
                    text = dish.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color=Gray,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .fillMaxWidth(.75f),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "$${dish.price}",
                    color=Gray,
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            AsyncImage(
                model = dish.image,
                contentDescription = dish.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .weight(0.3f)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }

    HorizontalDivider(
        modifier = Modifier.padding(
            start = 8.dp,
            end = 8.dp
        ),
        thickness = 1.dp,
        color = Gray
    )
}
