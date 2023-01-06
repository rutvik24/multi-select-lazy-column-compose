package com.atliq.multiselectlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.atliq.multiselectlazycolumn.ui.theme.MultiSelectLazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiSelectLazyColumnTheme {
                var items by remember {
                    mutableStateOf((1..20).map {
                        ListItem(
                            title = "Item $it",
                            isSelected = false
                        )
                    })
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(items.size) { i ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    items = items.mapIndexed { index, listItem ->
                                        if (index == i) {
                                            listItem.copy(isSelected = !listItem.isSelected)
                                        } else {
                                            listItem
                                        }
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = items[i].title)
                            if (items[i].isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.Green,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}