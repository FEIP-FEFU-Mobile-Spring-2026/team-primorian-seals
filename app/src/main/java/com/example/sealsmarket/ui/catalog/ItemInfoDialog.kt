package com.example.sealsmarket.ui.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sealsmarket.R
import com.example.sealsmarket.model.Item
import com.example.sealsmarket.model.emptyItem
import com.example.sealsmarket.ui.theme.SealsMarketTheme

@Composable
fun InfoDialog(
    item: Item,
    modifier: Modifier = Modifier,
    onClose: ()->Unit
){
    Dialog(
        onDismissRequest = {onClose()},
    ) {
        Card(
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.secondary, //цвет нужно поменять
                disabledContentColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    ) {
                IconButton(
                    onClick = {onClose()},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .height(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close)
                    )
                }
                Column(
                    modifier = modifier
                    .padding(vertical = 19.dp, horizontal = 8.dp)) {
                    TextInfoLine(
                        stringResource(R.string.material),
                        item.material,
                        modifier = modifier.padding(16.dp)
                    )
                    TextInfoLine(
                        stringResource(R.string.weight),
                        item.weight,
                        modifier = modifier.padding(16.dp)
                    )
                    TextInfoLine(
                        stringResource(R.string.season),
                        item.season,
                        modifier = modifier.padding(16.dp)
                    )
                    TextInfoLine(
                        stringResource(R.string.country_of_origin),
                        item.countryOfOrigin,
                        modifier = modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TextInfoLine(title: String, info: String, modifier: Modifier = Modifier){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Text(
                text = title
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = info,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal
            )
        }
}

@Preview
@Composable
fun InfoDialogPreview(){
    SealsMarketTheme() {
        InfoDialog(emptyItem){}
    }
}