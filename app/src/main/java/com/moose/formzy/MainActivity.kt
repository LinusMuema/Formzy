package com.moose.formzy

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.moose.formzy.ui.theme.FormzyTheme

class MainActivity : ComponentActivity() {
    private val TAG = this.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormzyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Screen()
                }
            }
        }
    }

    @Composable
    fun Screen(){
        val state by remember { mutableStateOf(FormState()) }

        Column {
            Form(
                state = state,
                fields = listOf(
                    Field(name = "username", validators = listOf(Required())),
                    Field(name = "email", validators = listOf(Required(), Email()))
                )
            )
            Button(onClick = { if (state.validate()) toast("Our form works!") }) {
                Text("Submit")
            }
        }
    }

    // the toast extension function
    private fun Context.toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}