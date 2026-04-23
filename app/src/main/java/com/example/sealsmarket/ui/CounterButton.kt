package com.example.sealsmarket.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sealsmarket.R

@Composable
fun CounterButton(
    count: Int,
    modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.extraSmall
            )
            .height(42.dp)
    ) {
        IconButton(
            onClick = {},
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = stringResource(R.string.removeItemOne)
            )
        }
        Text(
            text = count.toString()
        )
        IconButton(
            onClick = {},
            shape = MaterialTheme.shapes.extraSmall
        )
        {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.addItem),
            )
        }
    }
}

@Preview
@Composable
fun ButtonPreview(){
    CounterButton(
        1
    )
}