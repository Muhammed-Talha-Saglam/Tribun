package dev.bytecode.myapplication.composables

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.bytecode.myapplication.R
import dev.bytecode.myapplication.viewModelClasses.UserType

@Composable
fun makeWebViewTopBar(@StringRes resId: Int, activity: Activity) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Icon(
            asset = vectorResource(id = R.drawable.ic_back_arrow),
            modifier = Modifier
                .clickable(onClick = { activity.finish() })
                .padding(horizontal = 20.dp)
                .height(16.dp)
                .width(10.dp),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(70.dp))
        Text(
            text = stringResource(id = resId),
            color = Color.White
        )
    }

}