package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.sealsmarket.model.empty_list
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.theme.SealsMarketTheme

    @Composable
    fun Catalog(modifier: Modifier = Modifier) {
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
    }

    @Composable
    fun CategoryPanel(modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            //В будущем нужно сделать список категорий, и в цикле для каждой категории создавать кнопку
            //И поместить все кнопки в LazyRow, чтобы они пролистывались по горизонтали
            CategoryButton(R.string.cat_new) {}
            CategoryButton(R.string.cat_jeans) {}
            CategoryButton(R.string.cat_tshorts) {}
        }
    }


    @Composable
    fun CategoryButton(
        catName: Int,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        Button(
            modifier = modifier.padding(horizontal = 2.dp),
            onClick = {},
        ) {
            Text(text = stringResource(catName))
        }
    }

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(empty_list) { item ->
                ItemCardContent(item)
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
