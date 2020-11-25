package dev.bytecode.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel

import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.utils.newUserTeam
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

class SelectTeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SelectTeamPage({ goToSelectAuthorScreen() })
                }
            }
        }
    }

    private fun goToSelectAuthorScreen() {
        val intent = Intent(this, SelectAuthorActivity::class.java)
        startActivity(intent)
        finish()
    }
}

@Composable
fun SelectTeamPage( navigation: () -> Unit ) {

    val db = viewModel(modelClass = DatabaseViewModel::class.java)

    val teams = db.teams.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(
            modifier = Modifier.fillMaxWidth().height(63.dp),
            alignment = Alignment.Center,
        ) {
            Text(text = stringResource(id = R.string.my_team_topbar), style = kTopBarTextStyle)
        }

        Text(
            text = stringResource(id = R.string.choose_team),
            style = kSelectTeamTextStyle,
            modifier = Modifier.padding(vertical = 33.dp)
        )

        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            teams.value?.forEach{

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(326.dp)
                        .width(73.dp)
                        .border(
                            color = Color(0f, 0f, 0.3f),
                            width = 0.7.dp,
                            shape = RoundedCornerShape(3.dp)
                        )
                        .clickable(onClick = {
                            newUserTeam = it
                            navigation()
                        })
                ) {

                    Box(modifier = Modifier
                        .size(40.dp).padding(start = 30.dp, end = 12.dp)
                        .background(color = Color.Black,shape = CircleShape)
                    )

                    Text(text = it.name!!, style = kTeamNameTextStyle)


                }
            }
        }


    }

}



