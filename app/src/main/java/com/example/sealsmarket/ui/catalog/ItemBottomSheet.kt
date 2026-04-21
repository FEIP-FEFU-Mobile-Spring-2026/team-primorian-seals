package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sealsmarket.R
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInfoSheet(item: Item, modifier: Modifier = Modifier.navigationBarsPadding()){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    //ModalBottomSheet cоздаёт всплывающее окно
    ModalBottomSheet(
        onDismissRequest = {/*Закрыть*/},
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true //Сразу открывается во весь размер
        ),
        shape = MaterialTheme.shapes.medium,
        dragHandle = null,
        modifier = modifier){
        Box(
            modifier = Modifier
                .height(screenHeight * 0.9f)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column() {
                SheetItemInfo(item, modifier = Modifier.weight(1f))

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(item.sizes) { size ->
                            SizeButton(
                                size = size,
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                    }
                    Button(
                        onClick = {/*Добавить в корзину*/ },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                            .height(47.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.addToCart) + " · ${item.priceInKopecks / 100} ₽",
                            style = MaterialTheme.typography.labelLarge //именно здесь это не работает, скорее всего что-то выше переопределяет стиль
                        )
                    }
                }
            }
            LazyRow(modifier = Modifier
                .align(Alignment.TopStart)) {
                items(item.tags) { tag ->
                    TagText(
                        text = tag,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SheetItemInfo(item:Item, modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        AsyncImage(
            contentDescription = null,
            model = item.imageUrl,
            placeholder = painterResource(R.drawable.empty),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(
                bottom = 8.dp,
                start = 8.dp,
                end = 8.dp
            )
        )

            Text(
                text = item.longDescription,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    ),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

    }
}
@Composable
fun SizeButton(
    size: String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {/*Выбрать этот размер*/},
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier){
        Text(text = size)
    }
}

@Composable
fun TagText(text:String, modifier: Modifier = Modifier){
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp))
    }
}
@Preview
@Composable
fun WidnowPreview(){
    Scaffold(
        modifier = Modifier.background(Color.Gray)
    ) {innerPadding->
        ItemInfoSheet(
            emptyItem,
            modifier = Modifier
                .padding(innerPadding))
    }

}
