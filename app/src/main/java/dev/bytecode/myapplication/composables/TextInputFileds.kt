package dev.bytecode.myapplication.composables


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.LiveData

@Composable
fun makeTextInputField(
    value: LiveData<String>,
    placeholder: String,
    keyboardType: KeyboardType,
    onChange: (String) -> Unit
) {

    val fieldValue by value.observeAsState()

    TextField(
        value = if(fieldValue.isNullOrEmpty()) "" else  fieldValue!!,
        onValueChange = onChange,
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,imeAction = ImeAction.Done)
    )

}