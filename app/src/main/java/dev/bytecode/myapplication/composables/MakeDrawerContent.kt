package dev.bytecode.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.bytecode.myapplication.activities.*
import dev.bytecode.myapplication.utils.GlideImage
import dev.bytecode.myapplication.utils.loadLogoFromDrawable
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

@Composable
fun makeDrawerContent(activity: Activity, viewModel: DatabaseViewModel) {


    val nameSurname by viewModel.nameSurname.observeAsState()
    val userImg by viewModel.userImg.observeAsState()



    ScrollableColumn(
        modifier = Modifier.background(color = Color.Black).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Spacer(modifier = Modifier.height(28.dp))

        // Show user image
        userImg?.let { GlideImage(
            model = it,
            modifier = Modifier.size(77.dp).clip(shape = CircleShape),
            contentScale = ContentScale.Inside
        )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Show user name
        Text(
            color = Color(0xffffff),
            text = if(nameSurname.isNullOrEmpty()) "" else  nameSurname!!,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

       Spacer(modifier = Modifier.height(30.dp))


        makeDrawerItem(
            resID = R.drawable.ic_user,
            name = "Takımım",
            onClick = {
                val intent = Intent(activity, MyTeamActivity::class.java)
                activity.startActivity(intent)

        }
        )

//        Spacer(modifier = Modifier.height(30.dp))


        makeDrawerItem(
            resID = R.drawable.ic_search,
            name = "Puan Durumu",
            onClick = {
                val intent = Intent(activity, RankingActivity::class.java)
                activity.startActivity(intent)
            }
        )
  //      Spacer(modifier = Modifier.height(30.dp))


        makeDrawerItem(
            resID = R.drawable.ic_bettings,
            name = "İddaa Bülteni",
            onClick = {
                val intent = Intent(activity, BettingsActivity::class.java)
                activity.startActivity(intent)
            }
        )
    //    Spacer(modifier = Modifier.height(30.dp))


        makeDrawerItem(
            resID = R.drawable.ic_stopwatch,
            name = "Canlı Sonuçlar",
            onClick = {
                val intent = Intent(activity, LiveResultsActivity::class.java)
                activity.startActivity(intent)
            }
        )
//        Spacer(modifier = Modifier.height(30.dp))


        makeDrawerItem(
            resID = R.drawable.ic_tv,
            name = "TV' de Bugün",
            onClick = {
                val intent = Intent(activity, WhatIsOnTvActivity::class.java)
                activity.startActivity(intent)
            }
        )
  //      Spacer(modifier = Modifier.height(30.dp))


        makeDrawerItem(
            resID = R.drawable.ic_email,
            name = "İletişim",
            onClick = {
                // User sends mail to the developer
                val intent = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SENDTO
                    data = Uri.parse("mailto:info@deniseict.com")
                }, null)
                if (intent.resolveActivity(activity.packageManager) != null) {
                    activity.startActivity(intent)
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))


        Text(
            color = Color.White,
            text = "Tribün®  2018",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(25.dp))

    }


}





@Composable
fun makeDrawerItem(@DrawableRes resID: Int, name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        
        Spacer(modifier = Modifier.width(63.5.dp))
        
        loadLogoFromDrawable(resId = resID, height = 22.8.dp, width = 19.dp)

        Spacer(modifier = Modifier.width(23.dp))

        Text(text = name, style = kDrawerItemTextStyle)

    }

    Spacer(modifier = Modifier.height(9.5.dp))

}