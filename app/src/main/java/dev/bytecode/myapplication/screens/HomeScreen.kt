package dev.bytecode.myapplication

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dev.bytecode.myapplication.pages.MakeNewsPage
import dev.bytecode.myapplication.pages.MakeTwitterPage
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel


// There are three pages on the main screen for the user to navigate
sealed class Page(val route: String, @StringRes val resoureId: Int) {
    object Haberler : Page("haberler", R.string.NEWS)
    object Twitter : Page("twitter", R.string.TWITTER)
    object Profile : Page("profile", R.string.PROFILE)
}


@Composable
fun MakeHomeScreen(activity: Activity, viewModel: DatabaseViewModel) {

    viewModel.getCurrentUser()


    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()


    val pages = listOf(
        Page.Haberler,
        Page.Twitter,
        Page.Profile
    )


    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = false,
        drawerContent = {


            makeDrawerContent(activity, viewModel)

        },
        topBar = {

            // Top bar with a TRIBUN logo and a menu icon to open the drawer
            makeHomeScreenTopBar(scaffoldState)

        },
        bottomBar = {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

            // Bottom navigation bar for user to navigate between NEWS, TWITTER, and PROFILE page
            makeHomeScreenBottomNavBar(navBackStackEntry, navController,currentRoute, pages,)

        },

        ) {

        // Show the page the user has chosen from the bottom navigation bar
        NavHost(navController = navController, startDestination = Page.Twitter.route) {
            composable("haberler") { MakeNewsPage() }
            composable("twitter") { MakeTwitterPage() }
            composable("profile") { MakeProfilePage(activity, viewModel) }
        }
    }

}





@Composable
fun makeHomeScreenTopBar(scaffoldState: ScaffoldState) {
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
}

@Composable
fun makeHomeScreenBottomNavBar(
    navBackStackEntry: NavBackStackEntry?,
    navController: NavHostController,
    currentRoute: String?,
    pages: List<Page>
) {


    Surface(
        contentColor = Color(0xFFFFFFFF),
        modifier = Modifier.fillMaxWidth().preferredHeight(67.dp),
        shape = RoundedCornerShape(topRight = 35.dp, topLeft = 35.dp),
        elevation = 12.dp
    ) {

        Row(
            modifier = Modifier
                .height(67.dp)
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topLeft = 35.dp, topRight = 35.dp)
                )
                .padding(horizontal = 55.dp, vertical = 23.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            pages.forEach { screen ->

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
}