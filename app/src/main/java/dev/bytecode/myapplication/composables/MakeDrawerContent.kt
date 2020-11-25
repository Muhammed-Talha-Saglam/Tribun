package dev.bytecode.myapplication.composables

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.activities.BettingsActivity
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

@Composable
fun makeDrawerContent(activity: Activity) {

    val viewModel = viewModel(modelClass = DatabaseViewModel::class.java)
    viewModel.getCurrentUser()

    val nameSurname by viewModel.nameSurname.observeAsState()

    Column(
        modifier = Modifier.background(Color.Black).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        Card(
            modifier = Modifier
                .preferredSize(48.dp).clip(shape = CircleShape)
        ) {

            Image(asset = Icons.Default.Person)
        }


        Text(
            color = Color.White,
            text = if(nameSurname.isNullOrEmpty()) "" else  nameSurname!!,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))


        makeDrawerItem(
            icon = Icons.Default.Person,
            name = "Takımım",
            onClick = {
//                val intent = Intent(activity, MyTeamActivity::class.java)
//                activity.startActivity(intent)
        }
        )

        makeDrawerItem(
            icon = Icons.Default.Person,
            name = "Puan Durumu",
            onClick = {
//                val intent = Intent(activity, RankingActivity::class.java)
//                activity.startActivity(intent)
            }
        )

        makeDrawerItem(
            icon = Icons.Default.Check,
            name = "İddaa Bülteni",
            onClick = {
                val intent = Intent(activity, BettingsActivity::class.java)
                activity.startActivity(intent)
            }
        )

        makeDrawerItem(
            icon = Icons.Default.Notifications,
            name = "Canlı Sonuçlar",
            onClick = {
//                val intent = Intent(activity, LiveResultsActivity::class.java)
//                activity.startActivity(intent)
            }
        )

        makeDrawerItem(
            icon = Icons.Default.Info,
            name = "TV' de Bugün",
            onClick = {
//                val intent = Intent(activity, WhatIsOnTvActivity::class.java)
//                activity.startActivity(intent)
            }
        )

        makeDrawerItem(
            icon = Icons.Default.Email,
            name = "İletişim",
            onClick = {
                // User sends mail to the developer
                val intent = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SENDTO
                    data = Uri.parse("mailto:bytecode20@gmail.com")
                    putExtra(Intent.EXTRA_SUBJECT, "TRİBÜN")
                }, null)
                if (intent.resolveActivity(activity.packageManager) != null) {
                    activity.startActivity(intent)
                }
            }
        )


        Text(
            color = Color.White,
            text = "Tribün®  2018",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

    }


}



@Composable
fun makeDrawerItem(icon: VectorAsset, name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(asset = icon, tint = Color.White, modifier = Modifier.padding(start = 20.dp, end = 10.dp))

        Text(color = Color.White, text = name)

        Spacer(modifier = Modifier.fillMaxWidth())
    }
    Divider(
        color = Color.White,
        modifier = Modifier.padding(horizontal = 15.dp).padding(bottom = 20.dp),
        thickness = 3.dp
    )
}