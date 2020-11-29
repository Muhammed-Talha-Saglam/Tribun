package dev.bytecode.myapplication

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.Modals.Team
import dev.bytecode.myapplication.activities.MainActivity
import dev.bytecode.myapplication.utils.GlideImage
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

@Composable
fun MakeProfilePage(activity: Activity) {

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


        // Show user image
        userImg?.let { GlideImage(
            model = it,
            modifier = Modifier.size(77.dp).background(Color.White,shape = CircleShape),
        )
        }
        Spacer(modifier = Modifier.height(18.dp))


        // Show user name
        Text(
            text = if (nameSurname.isNullOrEmpty()) "" else nameSurname!!,
            style = kNameSurnameTextStyle
        )
        Spacer(modifier = Modifier.height(19.dp))


        // Show the team the user supports
        supportingTeam?.let { showSupportingTeam(activity, it) }
        Spacer(modifier = Modifier.height(39.dp))


        // Show the authors the user follows
        showFollowingAuthors(activity)
        Spacer(modifier = Modifier.height(70.dp))


        // Show log out button.
        makeLogOutButton(activity, viewModel)

    }

}







@Composable
fun showSupportingTeam(activity: Activity, supportingTeam: Team) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().clickable(onClick = {
            val intent = Intent(activity, UpdateTeamActivity::class.java)
            activity.startActivity(intent)

        }),
        verticalAlignment = Alignment.CenterVertically
    ) {


        supportingTeam.let {

            it.imageUrl?.let { it1 -> GlideImage(
                model = it1,
                modifier = Modifier.size(30.dp),
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
                .size(17.dp),
            tint = Color.Black
        )

    }

}

@Composable
fun showFollowingAuthors(activity: Activity, ) {
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

}


@Composable
fun makeLogOutButton(activity: Activity, viewModel: DatabaseViewModel) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Çıkış",
            style = kNameSurnameTextStyle,
            modifier = Modifier
                .clickable(onClick = {
                    viewModel.signOut()
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()

                })
                .padding(start = 5.dp, top = 5.dp, end = 5.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))


        Box(
            modifier = Modifier
                .size(height = 4.3.dp, width = 33.7.dp)
                .background(Color.Black)
        )
    }
}