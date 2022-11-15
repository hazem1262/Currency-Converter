package com.hazem.androidmvistarter.presentation.meals.list.views

import androidx.compose.foundation.border
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(modifier: Modifier, initialValue:String, onValueChanged:(String) -> Unit) {
    TextField(
        modifier = modifier.border(1.dp, Color.Gray),
        value = initialValue,
        onValueChange = {
            // validate against non number strings
            if (
                it.contains(Regex(ONLY_DOUBLE_REGEX)) ||
                it.contains(Regex(END_WITH_DOT_REGEX)) ||
                it.contains(Regex(STARTS_WITH_DOT_REGEX)) ||
                it.isEmpty() // support deleting the entire number
            ) onValueChanged.invoke(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor =  Color.Transparent,
            unfocusedIndicatorColor =  Color.Transparent,
        )
    )
}

private const val ONLY_DOUBLE_REGEX = "^([1-9][0-9]*)(\\.[0-9]+)?\$"
private const val END_WITH_DOT_REGEX = "^([1-9][0-9]*)[.]?\$" // to validate numbers building (ex: 1. , 2.)
private const val STARTS_WITH_DOT_REGEX = "^[.](0|([1-9][0-9]*))?\$" // to validate numbers building (ex: .1 , .2)