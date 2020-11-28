package dev.bytecode.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.Modals.Team

import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.utils.GlideImage
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

class SelectTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SelectTeamPage(this)
                }
            }
        }
    }


}

@Composable
fun SelectTeamPage( activity: Activity ) {

    val db = viewModel(modelClass = DatabaseViewModel::class.java)
    db.getTeams()

    val teams = db.teams.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        // Top Bar
        makeSelectTeamTopBar()


        Spacer(modifier = Modifier.height(33.dp))

        // Info text
        Text(
            text = "Lütfen desteklediğiniz takımı aşağıdaki ",
            style = kSelectTeamTextStyle,
        )
        Text(
            text = "listeden seçiniz.",
            style = kSelectTeamTextStyle,
        )

        Spacer(modifier = Modifier.height(47.dp))


        // Teams list
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            teams.value?.forEach{

                // Make row for each item
                makeTeamItem(team = it, db = db, activity = activity)


                Spacer(modifier = Modifier.height(14.3.dp))
            }
        }


    }

}


@Composable
fun makeSelectTeamTopBar() {
    Box(
        modifier = Modifier.fillMaxWidth().height(63.dp).background(color = Color.Black),
        alignment = Alignment.Center,
    ) {
        Text(text = stringResource(id = R.string.my_team_topbar), style = kTopBarTextStyle)
    }
}


@Composable
fun makeTeamItem(team : Team, db: DatabaseViewModel, activity: Activity) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(73.dp)
            .width(326.dp)
            .border(
                color = Color.Black,
                width = 0.3.dp,
                shape = RoundedCornerShape(3.dp)
            )
            .clickable(onClick = {

                db.addNewUserToDatabase(team)
                val intent = Intent(activity, SelectAuthorActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            })
    ) {

        Spacer(modifier = Modifier.width(30.dp))

        team.imgUrl?.let { GlideImage(model = it, modifier = Modifier.size(40.dp), contentScale = ContentScale.Inside) }

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = team.name!!, style = kTeamNameTextStyle)


    }


}
