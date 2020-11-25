package dev.bytecode.myapplication

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.*
import dev.bytecode.myapplication.composables.makeDrawerContent
import dev.bytecode.myapplication.pages.MakeNewsPage
import dev.bytecode.myapplication.pages.MakeTwitterPage
import dev.bytecode.myapplication.utils.loadBottomNavIcon
import dev.bytecode.myapplication.utils.loadLogoFromDrawable
import dev.bytecode.myapplication.viewModelClasses.DatabaseViewModel


// There are three pages on the main screen for the user to navigate
sealed class Screen(val route: String, @StringRes val resoureId: Int) {
    object Haberler: Screen("haberler", R.string.NEWS)
    object Twitter: Screen("twitter", R.string.TWITTER)
    object Profile: Screen("profile", R.string.PROFILE)
}


@Composable
fun MakeHomeScreen(activity: Activity, goToLoginScreen: () -> Unit) {

    val db = viewModel(modelClass = DatabaseViewModel::class.java)

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
                        loadLogoFromDrawable(
                            resId = R.drawable.tribun_logo,
                            height = 16.dp,
                            width = 96.dp
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
                backgroundColor = Color.White,
                elevation = 7.dp
            ) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                items.forEach { screen ->

                    val logo = when (screen.route) {
                        "haberler" -> R.drawable.new_icon
                        "twitter" -> R.drawable.twitter_icon
                        else -> R.drawable.profile_icon
                    }
                    var alpha = 1.0f
                    var isSelected = currentRoute == screen.route
                    if (!isSelected) {
                        alpha = 0.3f
                    }

                    BottomNavigationItem(
                        icon = {
                            loadBottomNavIcon(
                                resId = logo,
                                height = 19.dp,
                                width = 21.8.dp,
                                alpha = alpha
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = screen.resoureId),
                                fontSize = 10.sp
                            )
                        },
                        selected = isSelected,
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color(203, 201, 201),
                        onClick = {
                            navController.popBackStack(navController.graph.startDestination, false)

                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )

                }
            }
        },

        ) {

        //TODO: CREATE THE SCAFFOLD CONTENT
        // This is temporary
        NavHost(navController = navController, startDestination = Screen.Haberler.route ) {
            composable("haberler") { MakeNewsPage() }
            composable("twitter") { MakeTwitterPage() }
            composable("profile") { MakeProfilePage({goToLoginScreen() }) }
        }
    }

}