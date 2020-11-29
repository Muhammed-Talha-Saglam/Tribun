package dev.bytecode.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.Modals.Team
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.utils.GlideImage

import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

class UpdateTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    UpdateTeamPage(this)
                }
            }
        }
    }
}

@Composable
fun UpdateTeamPage(activity: Activity) {

    val db = viewModel(modelClass = DatabaseViewModel::class.java)

    val teams = db.teams.observeAsState()
    val supportingTeam = db.supportingTeam.observeAsState()
    db.getCurrentUser()



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        // Top Bar
        makeUpdateTeamTopBar(activity)
        Spacer(modifier = Modifier.height(33.dp))


        // Page header
        updateTeamHeader()
        Spacer(modifier = Modifier.height(47.dp))


        // Teams list
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            teams.value?.forEach{

                makeUpdateTeamItems(db, activity, it, supportingTeam)
                Spacer(modifier = Modifier.height(14.3.dp))

            }
        }

    }

}



@Composable
fun makeUpdateTeamTopBar(activity: Activity) {
    Box(
        modifier = Modifier.fillMaxWidth().height(63.dp).background(color = Color.Black),
        alignment = Alignment.Center,
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                asset = vectorResource(id = R.drawable.ic_back_arrow),
                modifier = Modifier
                    .clickable(onClick = {
                        activity.finish()
                    })
                    .padding(horizontal =  30.dp)
                    .height(18.dp)
                    .width(11.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(80.dp))

            androidx.compose.material.Text(text = "Takımım", style = kTopBarTextStyle)
        }

    }
}

@Composable
fun updateTeamHeader() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material.Text(
            text = "Lütfen desteklediğiniz takımı aşağıdaki ",
            style = kSelectTeamTextStyle,
        )
        androidx.compose.material.Text(
            text = "listeden seçiniz.",
            style = kSelectTeamTextStyle,
        )
    }
}

@Composable
fun makeUpdateTeamItems(
    db: DatabaseViewModel,
    activity: Activity,
    team: Team,
    supportingTeam: State<Team?>
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(73.dp)
            .width(326.dp)
            .border(
                color = Color(203,201,201),
                width = 0.3.dp,
                shape = RoundedCornerShape(3.dp)
            )
            .clickable(onClick = {

                db.updateSupportingTeam(team)
                activity.finish()
            })
    ) {

        Spacer(modifier = Modifier.width(30.dp))

        team.imageUrl?.let { it1 -> GlideImage(model = it1, modifier = Modifier.size(40.dp), ContentScale.Inside) }

        Spacer(modifier = Modifier.width(12.dp))

        Row(
            modifier = Modifier.width(210.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            androidx.compose.material.Text(text = team.name!!, style = kTeamNameTextStyle)

            val isSupporting = remember { supportingTeam }

            if(isSupporting.value?.id == team.id) {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_checked),
                    modifier = Modifier
                        .height(21.dp)
                        .width(28.dp),
                )
            }


        }



    }
}