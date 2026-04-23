package com.example.sealsmarket.ui.catalog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sealsmarket.R
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.Size
import com.example.sealsmarket.model.emptyItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInfoSheet(
    item: Item,
    onClose: ()->Unit,
    modifier: Modifier = Modifier){

    //Screen
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current

    //Modal window
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    //Size buttons
    var selectedSize by remember{ mutableStateOf<Size>(item.sizes[0]) }

    ModalBottomSheet(
        onDismissRequest = {
            onClose()
        },
        sheetState = sheetState,
        shape = MaterialTheme.shapes.medium,
        dragHandle = null,
        contentWindowInsets = {WindowInsets(0.dp)},
        modifier = modifier){
        Box(
            modifier = Modifier
                .height(screenHeight * 0.94f)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .navigationBarsPadding()
        ) {
            Column() {

                //Информация в верхней половине экрана
                SheetItemInfo(item, modifier = Modifier.weight(1f))

                //Информация в нижней половине экрана
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    //Строка с размерами
                    LazyRow(
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(item.sizes) { size ->
                            SizeButton(
                                size = size.name,
                                onClick = {selectedSize=size},
                                isSelected = (selectedSize==size),
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                    }
                    //Кнопка добавления в корзину
                    Button(
                        onClick = {
                            /*Добавить в корзину*/
                            Toast.makeText(context, "В корзину будет добавлен размер ${selectedSize.name}", Toast.LENGTH_SHORT).show()
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onClose()
                                }
                            }},

                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp)
                            .height(47.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.addToCart) + " · ${item.priceInKopecks / 100} ₽")
                    }
                }
            }
            //Теги
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
    onClick: ()->Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isSelected)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceBright,

            contentColor = if(isSelected)
            MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSurface

        ),
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
            onClose = {},
            modifier = Modifier
                .padding(innerPadding))
    }

}
