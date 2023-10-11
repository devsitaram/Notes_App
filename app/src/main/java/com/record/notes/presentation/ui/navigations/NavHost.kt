package com.record.notes.presentation.ui.navigations

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.record.notes.data.common.Constants.AMOUNT
import com.record.notes.data.common.Constants.DATE
import com.record.notes.data.common.Constants.ID
import com.record.notes.data.common.Constants.NAME
import com.record.notes.data.common.Constants.WORK
import com.record.notes.presentation.ui.components.TextView
import com.record.notes.presentation.ui.components.VectorIconView
import com.record.notes.presentation.ui.screen.HomeViewScreen
import com.record.notes.presentation.ui.screen.RecordViewScreen
import com.record.notes.presentation.ui.screen.SearchViewScreen
import com.record.notes.presentation.ui.screen.UpdateViewScreen
import com.record.notes.ui.theme.pink

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainViewScreen() {
    val navController = rememberNavController()
    val pages = listOf(
        BtnNavScreen.HomeScreen,
        BtnNavScreen.SearchScreen,
        BtnNavScreen.RecordScreen,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                pages.forEach { screen ->
                    BottomNavigationItem(
                        modifier = Modifier
                            .background(color = Color.White),
                        icon = {
                            VectorIconView(imageVector = screen.icon,
                                tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                    pink // Change to your desired color
                                } else {
                                    Color.Gray
                                }
                            )
                        },
                        label = {
                            TextView(
                                text = screen.route,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                ),
                                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                    pink // Change to your desired color
                                } else {
                                    Color.Gray
                                }
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        ButtonNavigationViewScreen(navController)
    }
}

@Composable
fun ButtonNavigationViewScreen(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BtnNavScreen.HomeScreen.route) {
        composable(BtnNavScreen.HomeScreen.route) {
            HomeViewScreen(navController)
        }
        composable(BtnNavScreen.SearchScreen.route) {
            SearchViewScreen()
        }
        composable(BtnNavScreen.RecordScreen.route) {
            RecordViewScreen(navController)
        }
        composable(
            route = ScreenList.UpdateScreen.route,
            arguments = listOf(
                navArgument(name = ID) {
                    type = NavType.StringType
                },
                navArgument(name = DATE) {
                    type = NavType.StringType
                },
                navArgument(name = NAME) {
                    type = NavType.StringType
                },
                navArgument(name = WORK) {
                    type = NavType.StringType
                },
                navArgument(name = AMOUNT) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val customerId = navBackStackEntry.arguments?.getString(ID)?.toInt()
            val date = navBackStackEntry.arguments?.getString(DATE).toString()
            val name = navBackStackEntry.arguments?.getString(NAME).toString()
            val work = navBackStackEntry.arguments?.getString(WORK).toString()
            val amounts = navBackStackEntry.arguments?.getString(AMOUNT).toString()
            UpdateViewScreen(
                customerId = customerId,
                date = date,
                name = name,
                worked = work,
                amount = amounts,
                navController = navController
            )
        }
        // other screen
//        composable(
//            route = Screen.VideoListScreen.route,
//            arguments = listOf(
//                navArgument(name = SUBJECT_NAME_KEY) {
//                    type = NavType.StringType
//                },
//                navArgument(name = SUBJECT_ID) {
//                    type = NavType.StringType
//                },
//            )
//        ) { navBackStackEntry ->
//            val subjectId = navBackStackEntry.arguments?.getString(SUBJECT_ID)?.toInt()
//            val subject = navBackStackEntry.arguments?.getString(SUBJECT_NAME_KEY)
//            VideoListViewScreen(subjectId, subject, navController)
//        }
//        composable(
//            route = Screen.VideoScreen.route,
//            arguments = listOf(
//                navArgument(name = VIDEO_ID_KEY) {
//                    type = NavType.StringType
//                }
//            )
//        ) { navBackStackEntry ->
//            val videoId = navBackStackEntry.arguments?.getString(VIDEO_ID_KEY)
////            VideoPlayViewScreen(videoId = videoId)
//            VideoViewScreen(videoId)
//        }
    }
}
