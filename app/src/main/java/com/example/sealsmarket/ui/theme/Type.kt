package com.example.sealsmarket.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily      // Этого не хватало для SystemFont!
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sealsmarket.R

// 1. Объявляем семейство шрифтов Arial
val ptsans = FontFamily(
    //Font(R.font.inter_28pthin, FontWeight.Normal),
    Font(R.font.ptsans_bold, weight = FontWeight.Bold),

    Font(R.font.ptsans_regular, weight = FontWeight.Normal),

)
val montserrat = FontFamily(
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),

)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = ptsans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = ptsans,
        //fontWeight = FontWeight.SemiBold,

        fontSize = 15.sp
)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)