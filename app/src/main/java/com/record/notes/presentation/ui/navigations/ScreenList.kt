package com.record.notes.presentation.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.record.notes.data.common.Constants.SUBJECT_ID
import com.record.notes.data.common.Constants.SUBJECT_NAME_KEY
import com.record.notes.data.common.Constants.VIDEO_ID_KEY

sealed class ScreenList(val route: String) {
    object MainScreen: ScreenList("MainScreen")
    object VideoListScreen : ScreenList("VideoListScreen/{$SUBJECT_ID}/{$SUBJECT_NAME_KEY}")
    object VideoScreen : ScreenList("VideoPlayScreen/{$VIDEO_ID_KEY}")
}

sealed class BtnNavScreen(var icon: ImageVector, val route: String) {
    object HomeScreen: BtnNavScreen(icon = Icons.Default.Home,"Home")
    object RecordScreen : BtnNavScreen(icon = Icons.Default.MenuBook,"RecordScreen")
    object SearchScreen: BtnNavScreen(icon = Icons.Default.Search,"Search")
    object ProfileScreen: BtnNavScreen(icon = Icons.Default.PersonOutline,"Profile")
}