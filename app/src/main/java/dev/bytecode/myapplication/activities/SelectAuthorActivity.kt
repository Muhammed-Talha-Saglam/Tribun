package dev.bytecode.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import dev.bytecode.myapplication.Modals.Author
import dev.bytecode.myapplication.activities.HomeScreenActivity
import dev.bytecode.myapplication.activities.ui.MyApplicationTheme
import dev.bytecode.myapplication.utils.GlideImage
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel

class SelectAuthorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AuthorSelectPage(this)
                }
            }
        }
    }
}

@Composable
fun AuthorSelectPage(activity: Activity) {

    val db = viewModel(modelClass = DatabaseViewModel::class.java)

    db.getAuthors()

    makeSelectAuthorPageBody(db, activity)




}

@Composable
fun makeSelectAuthorPageBody(db: DatabaseViewModel, activity: Activity) {

    val allAuthors = db.authors.observeAsState()



    val openDialog = remember { mutableStateOf(false) }

    val twitterUserName by db.twitterUserName.observeAsState()

    if (openDialog.value) {

        makeDialogBox(openDialog, db, twitterUserName)

    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        // Top bar
        makeAuthorPageTopBar(activity, openDialog)

        Spacer(modifier = Modifier.height(33.dp))

        // Page header
        makeAuthorSelectHeader()

        Spacer(modifier = Modifier.height(40.dp))


        // list of authors
        ScrollableColumn(
            modifier = Modifier.fillMaxWidth().height(380.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            allAuthors.value?.forEach {

                makeAuthorItem(db, author = it)

                Spacer(modifier = Modifier.height(14.3.dp))

            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        makeSaveButton(db, activity)


    }
}


@Composable
fun makeAuthorPageTopBar(activity: Activity, openDialog: MutableState<Boolean>) {

    Box(
        modifier = Modifier.fillMaxWidth().height(63.dp).background(color = Color.Black),
        alignment = Alignment.Center,
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                asset = vectorResource(id = R.drawable.ic_back_arrow),
                modifier = Modifier
                    .clickable(onClick = {
                        val intent = Intent(activity, HomeScreenActivity::class.java)
                        activity.startActivity(intent)
                        activity.finish()
                    })
                    .padding(horizontal = 30.dp)
                    .height(18.dp)
                    .width(11.dp),
                tint = Color.White
            )
            Text(text = stringResource(id = R.string.following), style = kTopBarTextStyle)

            Icon(
                asset = vectorResource(id = R.drawable.ic_plus),
                modifier = Modifier
                    .clickable(onClick = {
                        openDialog.value = true
                    })
                    .padding(horizontal = 30.dp)
                    .height(16.dp)
                    .width(16.dp),
                tint = Color.White
            )
        }
    }

}


@Composable
fun makeAuthorSelectHeader() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lütfen takip etmek istediğiniz hesapları aşağıdaki",
            style = kSelectTeamTextStyle,
        )
        Text(
            text = "listeden seçiniz.  Sağ üstteki '+' işaretine basarak",
            style = kSelectTeamTextStyle,

            )

        Text(
            text = "başka hesaplar da ekleyebilirsiniz.",
            style = kSelectTeamTextStyle,

            )
    }

}


@Composable
fun makeAuthorItem(db: DatabaseViewModel, author: Author) {

    // When the user taps the row, toggle the check icon
    val isFollowing = remember { mutableStateOf(author.following) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = {
                if (author.following == true) {
                    author.following = false
                    db.updateAuthors(author)
                    isFollowing.value = false

                } else {
                    author.following = true
                    db.updateAuthors(author)
                    isFollowing.value = true

                }

            })
            .height(73.dp)
            .width(326.dp)
            .border(
                color = Color(203,201,201),
                width = 0.3.dp,
                shape = RoundedCornerShape(3.dp)
            )
            .drawShadow(elevation = 0.7.dp, shape =  RoundedCornerShape(3.dp))


    ) {

        Spacer(modifier = Modifier.width(30.dp))


        author.imageUrl?.let {

            GlideImage(
                model = it,
                modifier = Modifier
                    .size(40.dp)
            )


        }


        Spacer(modifier = Modifier.width(12.dp))

        Row(
            modifier = Modifier.width(210.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            if(author.name != null) {
                Text(text = author.name, style = kTeamNameTextStyle)
            } else {
                author.twitterUserName?.let { Text(text = it, style = kTeamNameTextStyle) }

            }


            if (isFollowing.value == true) {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_checked),
                    modifier = Modifier
                        .height(21.dp)
                        .width(28.dp),
                    tint = Color.Black
                )
            } else {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_unchecked),
                    modifier = Modifier
                        .height(21.dp)
                        .width(28.dp),
                    tint = Color.Black
                )
            }

        }


        Spacer(modifier = Modifier.width(14.3.dp))

    }


}



@Composable
fun makeSaveButton(db: DatabaseViewModel, activity: Activity) {

    TextButton(
        modifier = Modifier
            .height(45.7.dp)
            .width(266.7.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            ),
        onClick = {

            db.updateFollowingAuthors()

            val intent = Intent(activity, HomeScreenActivity::class.java)
            activity.startActivity(intent)
            activity.finish()

        }
    ) {

        Text(
            text = "KAYDET",
            style = TextStyle(
                fontSize = 11.7.sp,
                fontFamily = defaultFontFamily,
                lineHeight = 15.7.sp,
                textAlign = TextAlign.Left,
                color = Color.White
            ),
        )
    }

}

@Composable
fun makeDialogBox(
    openDialog: MutableState<Boolean>,
    db: DatabaseViewModel,
    twitterUserName: String?
) {
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog.value = false
        },
        text = {

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth().background(color = Color.Black),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        asset = imageResource(id = R.drawable.tribun_logo),
                        modifier = Modifier.padding(vertical = 20.dp)
                            .size(width = 96.dp, height = 17.dp)
                    )
                }

                TextField(
                    value = if (twitterUserName.isNullOrEmpty()) "" else twitterUserName!!,
                    textStyle = TextStyle(fontWeight = FontWeight.SemiBold, color = Color.Black),
                    modifier = Modifier.width(236.dp),
                    backgroundColor = Color.Transparent,
                    activeColor = Color.Black,
                    onValueChange = {
                        db.setTwitterUserName(it)
                    },
                    placeholder = {
                        Text("Twitter kullanıcı adı", style = kTwitterNameTextStyle)
                    }
                )
            }




        },
        confirmButton = {
            TextButton(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 14.dp, top = 30.dp)
                    .height(45.7.dp)
                    .width(266.7.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(5.dp)
                    ),
                onClick = {
                    db.addNewTwitterAuthor()
                    openDialog.value = false
                }
            ) {

                Text(
                    text = "EKLE",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = fontFamily(font(resId = R.font.neris)),
                        lineHeight = 20.3.sp,
                        textAlign = TextAlign.Left,
                        color = Color.White
                    )
                )
            }
        },
    )
}