package com.record.notes.presentation.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.record.notes.data.common.Constants.ADDRESS
import com.record.notes.data.common.Constants.AMOUNT
import com.record.notes.data.common.Constants.DATE
import com.record.notes.data.common.Constants.DESCRIPTION
import com.record.notes.data.common.Constants.EMAIL
import com.record.notes.data.common.Constants.ID
import com.record.notes.data.common.Constants.NAME
import com.record.notes.data.common.Constants.NICK_NAME
import com.record.notes.data.common.Constants.PHONE_NUM
import com.record.notes.data.common.Constants.WORK

sealed class ScreenList(val route: String) {
    object UpdateScreen: ScreenList("Update/{$ID}/{$DATE}/{$NAME}/{$WORK}/{$AMOUNT}")
}

sealed class BtnNavScreen(var icon: ImageVector, val route: String) {
    object HomeScreen: BtnNavScreen(icon = Icons.Default.Home,"Home")
    object RecordScreen : BtnNavScreen(icon = Icons.Default.EditNote,"Note")
    object SearchScreen: BtnNavScreen(icon = Icons.Default.Search,"Search")
    object ProfileScreen: BtnNavScreen(icon = Icons.Default.PersonOutline,"Profile")
}