package dev.bytecode.myapplication



import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import dev.bytecode.myapplication.styles.kTextFieldPlaceHolderStyle

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
        modifier = Modifier.padding(horizontal =  20.dp),
        value = if(fieldValue.isNullOrEmpty()) "" else  fieldValue!!,
        textStyle = kTextFieldPlaceHolderStyle,
        onValueChange = onChange,
        placeholder = { Text(text = placeholder,style = kTextFieldPlaceHolderStyle)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,imeAction = ImeAction.Done),
        onImeActionPerformed = { ime, controler ->
            controler?.hideSoftwareKeyboard()
        },
        visualTransformation = visualTransformation,
        backgroundColor = Color.Transparent,
        activeColor = Color.White,
        inactiveColor = Color.Transparent,

    )

}