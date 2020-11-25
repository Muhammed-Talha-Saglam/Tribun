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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.Modals.Author
import dev.bytecode.myapplication.activities.HomeScreenActivity
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.utils.loadLogoFromDrawable
import dev.bytecode.myapplication.utils.newUserAuthors
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

class SelectAuthorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AuthorSelectPage({ goToMainScreen() })
                }
            }
        }
    }

    // When the sign-in is successful, navigate the user to home screen
    private fun goToMainScreen() {
        val intent = Intent(this, HomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}

@Composable
fun AuthorSelectPage(goToMainScreen: () -> Unit) {

    val db = viewModel(modelClass = DatabaseViewModel::class.java)

    var state = db.authors.observeAsState()

    val authors = remember { state }

    var selectedAuthors = mutableListOf<Author>()



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(
            modifier = Modifier.fillMaxWidth().height(63.dp),
            alignment = Alignment.Center,
        ) {
            Text(text = stringResource(id = R.string.following), style = kTopBarTextStyle)
        }

        Text(
            text = stringResource(id = R.string.choose_author),
            style = kSelectTeamTextStyle,
            modifier = Modifier.padding(vertical = 33.dp)
        )

        ScrollableColumn(
            modifier = Modifier.fillMaxWidth().height(550.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            authors.value?.forEach{

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

                            selectedAuthors.add(it)

                        })
                ) {

                    Box(modifier = Modifier
                        .size(40.dp).padding(start = 30.dp, end = 12.dp)
                        .background(color = Color.Black,shape = CircleShape)
                    )

                    Text(text = it.name!!, style = kTeamNameTextStyle)

                    Spacer(modifier = Modifier.width(143.dp))

                    loadLogoFromDrawable(
                        resId = R.drawable.unchecked_icon,
                        height = 21.dp,
                        width = 28.dp
                    )

                }
            }
        }

        TextButton(

            modifier = Modifier
                .height(45.7.dp)
                .width(266.7.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp)
                ),
            onClick = {
                newUserAuthors = selectedAuthors
                db.addNewUserToDatabase()
                goToMainScreen()
            }
        ) {

            Text(
                text = "KAYDET",
                style = kButtonTextStyle.copy(color = Color.White),

                )
        }
    }

}

