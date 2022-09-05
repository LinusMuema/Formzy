package com.moose.formzy.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    body1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    )
)