package dev.bytecode.myapplication

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import dev.bytecode.myapplication.pages.MakeNewsPage
import dev.bytecode.myapplication.pages.MakeTwitterPage


// There are three pages on the main screen for the user to navigate
sealed class Screen(val route: String, @StringRes val resoureId: Int) {
    object Haberler : Screen("haberler", R.string.NEWS)
    object Twitter : Screen("twitter", R.string.TWITTER)
    object Profile : Screen("profile", R.string.PROFILE)
}


@Composable
fun MakeHomeScreen(activity: Activity, goToLoginScreen: () -> Unit) {


    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()




    val items = listOf(
        Screen.Haberler,
        Screen.Twitter,
        Screen.Profile
    )



    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {

            makeDrawerContent(activity)

        },
        topBar = {

            TopAppBar(
                backgroundColor = Color.Black,
                modifier = Modifier.height(63.3.dp),
                elevation = 0.dp,
                title = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.width(65.dp))

                        Image(
                            asset = imageResource(id = R.drawable.tribun_logo),
                            modifier = Modifier.height(16.7.dp).width(96.dp)
                        )

                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // Open the drawer when clicked
                            scaffoldState.drawerState.open()
                        }
                    ) {
                        Icon(asset = Icons.Default.Menu, tint = Color.White)
                    }
                },
            )
        },
        bottomBar = {

            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(67.dp)
                    .border(
                        color = Color.Black,
                        shape = RoundedCornerShape(topLeft = 35.dp, topRight = 35.dp),
                        width = 0.5.dp
                    ),
                backgroundColor = Color.White,
            ) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 55.dp, vertical = 23.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items.forEach { screen ->

                        val logo = when (screen.route) {
                            "haberler" -> R.drawable.ic_home
                            "twitter" -> R.drawable.ic_twitter
                            else -> R.drawable.ic_profile
                        }
                        var isSelected = currentRoute == screen.route


                        Box(
                            modifier = Modifier
                                .size(50.dp).background(
                                    color = if (isSelected) Color.Black else Color.White,
                                    shape = CircleShape
                                )
                                .clickable(onClick = {
                                    navController.popBackStack(
                                        navController.graph.startDestination,
                                        false
                                    )
                                    if (currentRoute != screen.route) {
                                        navController.navigate(screen.route)
                                    }

                                }),
                            alignment = Alignment.Center,

                            ) {
                            Icon(
                                asset = vectorResource(id = logo),
                                modifier = Modifier.size(20.dp),
                                tint = if (isSelected) Color.White else Color.Black
                            )
                        }

                    }
                }
            }
        },

        ) {

        NavHost(navController = navController, startDestination = Screen.Haberler.route) {
            composable("haberler") { MakeNewsPage() }
            composable("twitter") { MakeTwitterPage() }
            composable("profile") { MakeProfilePage(activity, { goToLoginScreen() }) }
        }
    }

}