package com.moose.formzy

import android.util.Patterns
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Field(val name: String, val label: String = "", val validators: List<Validator>){
    var text: String by mutableStateOf("")
    var lbl: String by mutableStateOf(label)
    var hasError: Boolean by mutableStateOf(false)

    fun clear(){ text = "" }

    private fun showError(error: String){
        hasError = true
        lbl = error
    }

    private fun hideError(){
        lbl = label
        hasError = false
    }

    @Composable
    fun Content(){
        TextField(
            value = text,
            isError = hasError,
            label = { Text(text = lbl) },
            modifier = Modifier.padding(10.dp),
            onValueChange = { value ->
                hideError()
                text = value
            }
        )
    }

    fun validate(): Boolean {
        return validators.map {
            when (it){
                is Email -> {
                    if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()){
                        showError(it.message)
                        return@map false
                    }
                    true
                }
                is Required -> {
                    if (text.isEmpty()){
                        showError(it.message)
                        return@map  false
                    }
                    true
                }
                is Regex -> {
                    if (!it.regex.toRegex().containsMatchIn(text)){
                        showError(it.message)
                        return@map false
                    }
                    true
                }
            }
        }.all { it }
    }
}