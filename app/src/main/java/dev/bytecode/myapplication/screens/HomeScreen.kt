package dev.bytecode.myapplication

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import dev.bytecode.myapplication.pages.MakeNewsPage
import dev.bytecode.myapplication.pages.MakeProfilePage
import dev.bytecode.myapplication.pages.MakeTwitterPage


// There are three pages on the main screen for the user to navigate
sealed class Screen(val route: String, @StringRes val resoureId: Int) {
    object Haberler: Screen("haberler", R.string.NEWS)
    object Twitter: Screen("twitter", R.string.TWITTER)
    object Profile: Screen("profile", R.string.PROFILE)
}


@Composable
fun MakeHomeScreen() {

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

            // TODO: CREATE DRAWER CONTENT
            //  This is temporary
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "DRAWER CONTENT")
            }
        },
        topBar = {

            // TODO: CREATE TOP BAR CONTENT
            //  This is temporary
            TopAppBar(
                backgroundColor = Color.Black,
                elevation = 0.dp,
                title = {
                    Text(
                        text = "TOP BAR TITLE ",
                        style = TextStyle(fontFamily = FontFamily.Monospace, color = Color.White),
                        textAlign = TextAlign.Center,
                    )
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
                }
            )
        },
        bottomBar = {

            // TODO: CREATE BOTTOM NAV BAR CONTENT
            //  This is temporary
            BottomNavigation(
                backgroundColor = Color.White,
                elevation = 3.dp
            ) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(asset = Icons.Default.AccountBox) },
                        label = { Text(text = stringResource(id = screen.resoureId)) },
                        selected = currentRoute == screen.route,
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Gray,
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
            composable("profile") { MakeProfilePage() }
        }
    }

}