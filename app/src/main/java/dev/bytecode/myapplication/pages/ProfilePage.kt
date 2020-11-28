package dev.bytecode.myapplication

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.utils.GlideImage
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

@Composable
fun MakeProfilePage(activity: Activity, goToLoginScreen: () -> Unit) {

    val viewModel = viewModel(modelClass = DatabaseViewModel::class.java)


    val nameSurname by viewModel.nameSurname.observeAsState()
    val supportingTeam by viewModel.supportingTeam.observeAsState()
    val userImg by viewModel.userImg.observeAsState()
    viewModel.getCurrentUser()




    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(70.dp))


        val userPhoto = remember { userImg }

        userPhoto?.let { GlideImage(
            model = it,
            modifier = Modifier.size(77.dp),
            contentScale =  ContentScale.Inside
        )
        }



        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = if (nameSurname.isNullOrEmpty()) "" else nameSurname!!,
            style = kNameSurnameTextStyle
        )

        Spacer(modifier = Modifier.height(19.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().clickable(onClick = {
                val intent = Intent(activity, UpdateTeamActivity::class.java)
                activity.startActivity(intent)

            }),
            verticalAlignment = Alignment.CenterVertically
        ) {


            supportingTeam?.let {

                it.imgUrl?.let { it1 -> GlideImage(
                    model = it1,
                    modifier = Modifier.size(19.dp),
                    contentScale =  ContentScale.Inside

                    )
                }
            }



            Spacer(modifier = Modifier.width(10.3.dp))

            supportingTeam?.let {
                Text(
                    text = if (it.name.isNullOrEmpty()) "" else it.name,
                    style = kTeamNameTextStyle
                )
            }


            Spacer(modifier = Modifier.width(13.dp))

            Icon(
                asset = vectorResource(id = R.drawable.ic_pencil),
                modifier = Modifier
                    .size(11.7.dp),
                tint = Color.Black
            )

        }

        Spacer(modifier = Modifier.height(39.dp))

        Box(
            alignment = Alignment.Center,
            modifier = Modifier
                .size(height = 74.7.dp, width = 315.7.dp)
                .border(
                    width = 0.7.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(13.7.dp)
                )
                .clickable(onClick = {
                    val intent = Intent(activity, SelectAuthorActivity::class.java)
                    activity.startActivity(intent)

                })
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(22.dp))

                Text(
                    text = stringResource(id = R.string.following),
                    style = kTeamNameTextStyle,
                )

                Spacer(modifier = Modifier.width(40.dp))


                Image(asset = imageResource(id = R.drawable.ic_demirkol), modifier = Modifier.size(32.dp))

                Spacer(modifier = Modifier.width(11.dp))

                Image(asset = imageResource(id = R.drawable.ic_banu), modifier = Modifier.size(32.dp))

                Spacer(modifier = Modifier.width(11.dp))

                Image(asset = imageResource(id = R.drawable.ic_ali_ece), modifier = Modifier.size(32.dp))


                Spacer(modifier = Modifier.width(20.dp))


                Icon(
                    asset = vectorResource(id = R.drawable.ic_next_arrow),
                    modifier = Modifier
                        .height(16.2.dp)
                        .width(8.7.dp),
                    tint = Color.Black
                )

            }

        }

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "Çıkış",
            style = kNameSurnameTextStyle,
            modifier = Modifier.clickable(onClick = {
                viewModel.signOut()
                goToLoginScreen()
            })
        )

        Spacer(modifier = Modifier.height(5.dp))


        Box(
            modifier = Modifier
                .size(height = 4.3.dp, width = 33.7.dp)
                .background(Color.Black)
        )


    }


}