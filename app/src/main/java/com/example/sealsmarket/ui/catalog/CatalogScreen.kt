package com.example.sealsmarket.ui.catalog

import Category
import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import com.example.sealsmarket.data.ProductsData.ExampleProductsContentHandler
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.ProductsContent
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.theme.SealsMarketTheme


    @Composable
    fun Catalog(productsContentHandler : IProductsContentReciever, modifier: Modifier = Modifier)
    {
        val newProductsContent : ProductsContent = productsContentHandler.GetProductsContent() ?: ExampleProductsContentHandler.GetProductsContent();
		var selectedItem by remember { mutableStateOf<Item?>(null) }
        var selectedCatId by rememberSaveable{mutableStateOf(newProductsContent.categories[0].id)}

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            CategoryPanel(
                catList = newProductsContent.categories,
                selectedCatId = selectedCatId,
                onBtnClick = {id ->
                         selectedCatId = id
                    },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Content(
                onButtonClick = {item-> selectedItem = item },
                itemList = newProductsContent.items.filter{item -> item.categoryId == selectedCatId},
                modifier = Modifier
                    .padding(16.dp)
            )

            selectedItem?.let {
                ItemInfoSheet(
                    item = emptyItem,
                    onClose = { selectedItem=null }
                )
            }
        }
    }


    @Composable
    fun CategoryPanel(
        catList: List<Category>,
        selectedCatId: String,
        onBtnClick: (catID : String) -> Unit,
        modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            LazyRow(
            ) {
                items(catList){
                    cat -> CategoryButton(
                    cat,
                    modifier = Modifier,
                    selectedCatId = selectedCatId,
                    onBtnClick = {id -> onBtnClick(id)}
                    )
                }
            }
        }
    }


    @Composable
    fun CategoryButton(
        cat: Category,
        selectedCatId: String,
        modifier: Modifier = Modifier,
        onBtnClick: (id: String) -> Unit
    ) {
        Button(
            modifier = modifier.padding(horizontal = 2.dp),
            onClick = {onBtnClick(cat.id)},
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCatId==cat.id) MaterialTheme.colorScheme.tertiary
                else
                    MaterialTheme.colorScheme.onSurface,
                contentColor = if (selectedCatId==cat.id) MaterialTheme.colorScheme.secondary
                else
                    MaterialTheme.colorScheme.secondary
            )){
            Text(
				text = cat.name,
				style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary)

        }
    }

    @Composable
    fun Content(
		onButtonClick: (Item) -> Unit,
		itemList: List<Item>, 
		modifier: Modifier = Modifier)
    {
        LazyColumn( modifier = modifier)
        {
            items(itemList)
            { item ->
                ItemCardContent(
                    item,
					onButtonClick = {onButtonClick(item)},
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
