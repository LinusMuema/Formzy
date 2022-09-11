package com.moose.formzy

import androidx.lifecycle.ViewModel
import com.dsc.form_builder.*

class MainViewmodel: ViewModel() {

    val formState = FormState(
        fields = listOf(
            TextFieldState(
                name = "email",
                validators = listOf(
                    Validators.Email(),
                    Validators.Required()
                ),
            ),
            TextFieldState(
                name = "age",
                transform = { it.toInt() },
                validators = listOf(Validators.Required())
            ),
            SelectState(
                name = "hobbies",
                validators = listOf(Validators.Required())
            ),
            ChoiceState(
                name = "androidLover",
                transform = { it == "Yes" },
                validators = listOf(Validators.Required())
            )
        )
    )


    fun submitData(details: Details){
        // TODO: send data to wherever
    }
}