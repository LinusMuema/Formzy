package com.moose.formzy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moose.formzy.ui.theme.FormzyTheme

class MainActivity : ComponentActivity() {
    private val viewmodel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormzyTheme {
                Content()
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun Content(){

    }
}