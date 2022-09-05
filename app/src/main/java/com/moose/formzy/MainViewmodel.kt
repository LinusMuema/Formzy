package com.moose.formzy

import androidx.lifecycle.ViewModel

class MainViewmodel: ViewModel() {

    fun submitData(email: String, age: Int, hobbies: List<String>, androidLover: Boolean){
        val details = Details(age, email, hobbies, androidLover)
        // TODO: send data to wherever
    }
}