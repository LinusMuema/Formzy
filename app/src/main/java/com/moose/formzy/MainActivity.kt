package com.moose.formzy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dsc.form_builder.ChoiceState
import com.dsc.form_builder.SelectState
import com.dsc.form_builder.TextFieldState
import com.moose.formzy.ui.theme.FormzyTheme

class MainActivity : ComponentActivity() {
    private val viewmodel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun Content(){
        val formState = remember { viewmodel.formState }

        val ageState: TextFieldState = formState.getState("age")
        val emailState: TextFieldState = formState.getState("email")
        val hobbiesState: SelectState = formState.getState("hobbies")
        val androidLoverState: ChoiceState = formState.getState("androidLover")


        val options = setOf("Yes", "No")
        val hobbies = setOf("Swimming", "Reading", "Jogging", "Singing")

        FormzyTheme {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(text = "Your email")
                OutlinedTextField(
                    value = emailState.value,
                    isError = emailState.hasError,
                    onValueChange = { emailState.change(it) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.onPrimary,
                        unfocusedBorderColor = MaterialTheme.colors.onPrimary
                    )
                )
                if (emailState.hasError) {
                    Text(
                        color = Color.Red,
                        text = emailState.errorMessage,
                        style = MaterialTheme.typography.caption
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(text = "Your age")
                OutlinedTextField(
                    value = ageState.value,
                    isError = ageState.hasError,
                    onValueChange = { ageState.change(it) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.onPrimary,
                        unfocusedBorderColor = MaterialTheme.colors.onPrimary
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                if (ageState.hasError) {
                    Text(
                        color = Color.Red,
                        text = ageState.errorMessage,
                        style = MaterialTheme.typography.caption
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(text = "Your hobbies")
                Row {
                   hobbies.forEach {
                       Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                           Checkbox(
                               checked = hobbiesState.value.contains(it),
                               colors = CheckboxDefaults.colors(
                                   checkedColor = MaterialTheme.colors.onPrimary
                               ),
                               onCheckedChange = { unchecked ->
                                   if (!unchecked) hobbiesState.unselect(it)
                                   else hobbiesState.select(it)
                               },
                           )
                           Text(text = it, style = MaterialTheme.typography.caption)
                       }
                   }
                }
                if (hobbiesState.hasError) {
                    Text(
                        color = Color.Red,
                        text = hobbiesState.errorMessage,
                        style = MaterialTheme.typography.caption
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(text = "Do you love android?")
                Row {
                    options.forEach {
                        Column(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RadioButton(
                                selected = androidLoverState.value == it,
                                onClick = { androidLoverState.change(it) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colors.onPrimary
                                )
                            )
                            Text(text = it, style = MaterialTheme.typography.caption)
                        }
                    }
                }
                if (androidLoverState.hasError) {
                    Text(
                        color = Color.Red,
                        text = androidLoverState.errorMessage,
                        style = MaterialTheme.typography.caption
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Button(onClick = {
                    if (formState.validate()){
                        val details = formState.getData(Details::class)
                        Log.d("Details", "Content: details are $details")
                    }
                }) {
                    Text(text = "Submit")
                }

            }
        }
    }
}