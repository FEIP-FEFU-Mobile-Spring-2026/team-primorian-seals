package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sealsmarket.R
import com.example.sealsmarket.data.ProductsData.ExampleProductsContentHandler
import com.example.sealsmarket.data.ProductsData.interfaces.IProductsContentReciever
import com.example.sealsmarket.model.Category
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import com.example.sealsmarket.ui.NavigationPanel
import com.example.sealsmarket.ui.theme.SealsMarketTheme


@Composable
fun Catalog(
    productsContentHandler: IProductsContentReciever,
    modifier: Modifier = Modifier
) {
    val viewModel: CatalogViewModel = viewModel(
        factory = CatalogViewModelFactory(productsContentHandler)
    )
    val loadingState by viewModel.loadingState.collectAsState()
    val selectedCatId by viewModel.selectedCatId.collectAsState()

    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        if (loadingState == 0) {
            // Загрузка
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = stringResource(R.string.catalog_loading),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        } else if (loadingState == 2) {
            // Ошибка
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = stringResource(R.string.err_loading),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = stringResource(R.string.check_connection),
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )
                    Button(onClick = { viewModel.loadProducts() }) {
                        Text(stringResource(R.string.repeat))
                    }
                }
            }
        } else {
            // Успех - показываем каталог
            CategoryPanel(
                catList = viewModel.getCategories(),
                selectedCatId = selectedCatId,
                onBtnClick = { id ->
                    viewModel.selectCategory(id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Content(
                onButtonClick = { item ->
                    selectedItem = item
                },
                itemList = viewModel.getFilteredItems(),
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
    
    selectedItem?.let {
        ItemInfoSheet(
            item = it,
            onClose = { selectedItem = null }
        )
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
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = catList,
                key = { cat -> cat.id }
            ) { cat ->
                CategoryButton(
                    cat = cat,
                    modifier = Modifier,
                    selectedCatId = selectedCatId,
                    onBtnClick = { id -> onBtnClick(id) }
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

//    @Composable
//    @Preview
//    fun CatalogPreview()
//    {
//        SealsMarketTheme()
//        {
//            Scaffold(
//                bottomBar = { NavigationPanel({},{}) },
//
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }


@Composable
@Preview
fun CatalogPreview()
{
    SealsMarketTheme {
        Scaffold(
            bottomBar = { NavigationPanel({ }, { }) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Catalog(
                productsContentHandler = ExampleProductsContentHandler,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
