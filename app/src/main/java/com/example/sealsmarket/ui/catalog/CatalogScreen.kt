package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sealsmarket.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import com.example.sealsmarket.data.ProductsData
import com.example.sealsmarket.model.emptyItem
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.theme.SealsMarketTheme

    @Composable
    fun Catalog(modifier: Modifier = Modifier) {
        Box(modifier = modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                CategoryPanel(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Content(
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
//                ItemInfoSheet(
//                    item = emptyItem
//                )
            }
    }


    @Composable
    fun CategoryPanel(modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            LazyRow(
            ) {
                items(ProductsData.GetCategoriesList()){
                    cat -> CategoryButton(
                    cat.name,
                    onClick={/*Фильтрация*/})
                }
            }
        }
    }


    @Composable
    fun CategoryButton(
        catName: String,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        Button(
            modifier = modifier.padding(horizontal = 2.dp),
            onClick = {},
        ) {
            Text(text = catName)
        }
    }

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        LazyColumn(
            modifier = modifier) {
            items(ProductsData.GetItemsList()) { item ->
                ItemCardContent(
                    item =item,
                    onButtonClick = {},
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }

    @Composable
    @Preview
    fun CatalogPreview() {
        SealsMarketTheme() {
            Scaffold(
                bottomBar = {
                    NavigationPanel({},{}) },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Catalog(
                    modifier = Modifier
                        .padding((innerPadding))
                )
            }
        }
    }
