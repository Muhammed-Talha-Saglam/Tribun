package dev.bytecode.myapplication



import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData

@Composable
fun makeTextInputField(
    value: LiveData<String>,
    placeholder: String,
    visualTransformation: VisualTransformation,
    keyboardType: KeyboardType,
    onChange: (String) -> Unit
) {

    val fieldValue by value.observeAsState()

    TextField(
        modifier = Modifier.width(236.dp).paddingFromBaseline(bottom = 9.5.dp),
        value = if(fieldValue.isNullOrEmpty()) "" else  fieldValue!!,
        textStyle = kTextFieldPlaceHolderStyle,
        onValueChange = onChange,
        placeholder = { Text(text = placeholder,style = kTextFieldPlaceHolderStyle)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,imeAction = ImeAction.Next),
        onImeActionPerformed = { ime, controler ->
            controler?.hideSoftwareKeyboard()
        },
        visualTransformation = visualTransformation,
        backgroundColor = Color.Transparent,
        activeColor = Color.White,
        inactiveColor = Color.Transparent,
    )

}