package com.moose.formzy.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FormzyTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
        shapes = Shapes,
        content = content,
        colors = FormzyColors,
        typography = Typography,
    )
}