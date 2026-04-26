package com.example.sealsmarket.ui.catalog

import Category
import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import com.example.sealsmarket.data.ProductsData.ExampleProductsContentHandler
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.ProductsContent
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.theme.SealsMarketTheme

@Composable
    fun Catalog(productsContentHandler : IProductsContentReciever, modifier: Modifier = Modifier)
    {
        val newProductsContent : ProductsContent = productsContentHandler.GetProductsContent() ?: ExampleProductsContentHandler.GetProductsContent();

        val mutItemList = remember { newProductsContent.items.toMutableStateList() }
        val mutCatList = remember { newProductsContent.categories }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            CategoryPanel(
                catList = mutCatList,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onBtnClick = {id ->
                        mutItemList.clear()
                        mutItemList.addAll(newProductsContent.items.filter { item ->  item.categoryId == id })
                    }
            )
            Content(
                itemList = mutItemList,
                modifier = Modifier
                    .padding(16.dp)
            )

        }
    }

    @Composable
    fun CategoryPanel(catList: List<Category>, modifier: Modifier = Modifier, onBtnClick: (catID : String) -> Unit) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            LazyRow(
            ) {
                items(catList){
                    cat -> CategoryButton(
                    cat.id,
                    cat.name,
                    modifier = Modifier,
                    onBtnClick = {id -> onBtnClick(id)}
                    )
                }
            }
        }
    }


    @Composable
    fun CategoryButton(
        catID : String,
        catName: String,
        modifier: Modifier = Modifier,
        onBtnClick: (id: String) -> Unit
    ) {
        Button(
            modifier = modifier.padding(horizontal = 2.dp),
            onClick = {onBtnClick(catID)},
        ) {
            Text(text = catName)
        }
    }

    @Composable
    fun Content(itemList: List<Item>, modifier: Modifier = Modifier)
    {
        LazyColumn( modifier = modifier)
        {
            items(itemList)
            { item ->
                ItemCardContent(
                    item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }

    @Composable
    @Preview
    fun CatalogPreview()
    {
        SealsMarketTheme()
        {
            Scaffold(
                bottomBar = { NavigationPanel({},{}) },

                modifier = Modifier.fillMaxSize()
            )

            {
                innerPadding ->
                    Catalog(
                        productsContentHandler = ExampleProductsContentHandler,
                        modifier = Modifier
                        .padding((innerPadding))
                )
            }
        }
    }
