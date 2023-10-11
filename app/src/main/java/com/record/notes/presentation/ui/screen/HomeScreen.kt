package com.record.notes.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.BrowserUpdated
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.record.notes.R
import com.record.notes.presentation.ui.components.ButtonAppBar
import com.record.notes.presentation.ui.components.ConfirmationDialogBox
import com.record.notes.presentation.ui.components.TextView
import com.record.notes.presentation.ui.components.VectorIconView
import com.record.notes.presentation.viewmodel.HomeViewModel

@Composable
fun HomeViewScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val customerList = viewModel.customerList.value

    var showDialogBox by remember { mutableStateOf(false) }
    var isDelete by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = isDelete, block = {
        viewModel.getCustomerDetails()
    })

    if (customerList.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (customerList.isError.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TextView(text = customerList.isError)
        }
    }


//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = 50.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            ButtonAppBar(title = "Home Page")
//            if (customerList.isData == null) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    TextView(text = "Not Result...")
//                }
//            } else {
//
//            }
//        }
//    }

//    customerList.isData?.let {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonAppBar(title = "Home Page")
            if (customerList.isData.isNullOrEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextView(text = "No Result...")
                }
            }
            customerList.isData?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        this.items(it) { customer ->
                            val customerId = customer?.customerId
                            val dateAndTime = customer?.dateAndTime
                            val fullName = customer?.fullName
                            val work = customer?.work
                            val amount = customer?.amounts
                            val status = customer?.status
                            val descriptions = customer?.descriptions ?: "null"
                            val phoneNumber = customer?.phoneNumber ?: "null"
                            val nickname = customer?.nickname ?: "null"
                            val location = customer?.location ?: "null"
                            val emailAddress = customer?.emailAddress ?: "null"
                            CustomerListBox(
                                customerId = customerId,
                                dateAndTime = dateAndTime,
                                fullName = fullName,
                                work = work,
                                status = status,
                                nickname = nickname,
                                onAdd = {
                                    Toast.makeText(context, "Add success!", Toast.LENGTH_SHORT).show()
                                },
                                onUpdate = {
                                    navController.navigate("Update/${customerId}/${dateAndTime}/${fullName}/${work}/${amount}")
                                },
                                showDialogBox = showDialogBox,
                                onDeleteDialogBox = { showDialogBox = true },
                                onDismissDialogBox = { showDialogBox = false },
                                onDelete = {
                                    viewModel.deleteCustomer(customerId)
                                    showDialogBox = false
                                    isDelete = !isDelete
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomerListBox(
    customerId: Int?,
    dateAndTime: String?,
    fullName: String?,
    work: String?,
    status: String?,
    nickname: String?,
    onAdd : () -> Unit,
    onUpdate: () -> Unit,
    showDialogBox: Boolean = false,
    onDeleteDialogBox: () -> Unit,
    onDismissDialogBox: () -> Unit,
    onDelete: () -> Unit
) {
    var expandedSetting by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf(Color.Gray) }
    color = when (status) {
        "Due/बाँकी" -> colorResource(id = R.color.red)
        "Advance/अग्रिम भुक्तान" -> colorResource(id = R.color.teal_200)
        else -> Color.Gray
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                VectorIconView(
                    imageVector = Icons.Default.PersonOutline,
                    contentDescription = "profile image",
                    tint = color,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(5.dp)
                )
                TextView(text = fullName.toString(), modifier = Modifier.padding(start = 5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    SettingIconView(
                        expandedSetting = expandedSetting,
                        onExpandedSetting = { expandedSetting = true },
                        onDismissDropdown = { expandedSetting = false },
                        onAdd = { onAdd() },
                        onUpdate = { onUpdate() },
                        onDeleteDialogBox = {  onDeleteDialogBox() }
                    )
                }
            }
            Column(modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 15.dp, bottom = 15.dp)
            ) {
                TextView(text = "Id: " + customerId.toString())
                TextView(text = "Date: " + dateAndTime.toString())
                TextView(text = "Work: " + work.toString())
                TextView(text = "Status: " + status.toString())
                if (nickname.toString().isNotEmpty() && nickname != null) {
                    TextView(text = "NickName: $nickname")
                }
            }
        }
    }

    if (showDialogBox) {
        ConfirmationDialogBox(
            title = "Delete?",
            text = "Are you sure you want to Delete?",
            onDismiss = { onDismissDialogBox() },
            onConfirm = { onDelete() }
        )
    }
}


@Composable
fun SettingIconView(
    expandedSetting: Boolean = false,
    onExpandedSetting: () -> Unit,
    onDismissDropdown: () -> Unit,
    onAdd: () -> Unit,
    onUpdate: () -> Unit,
    onDeleteDialogBox: () -> Unit,
) {

    val optionList = listOf(
        ListOfSettingMenus.Add,
        ListOfSettingMenus.Update,
        ListOfSettingMenus.Delete
    )

    // top action icons
    Row(
        modifier = Modifier
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        // setting icon button
        IconButton(modifier = Modifier
            .size(32.dp, 32.dp)
            .padding(end = 8.dp, top = 12.dp),
            onClick = { onExpandedSetting() }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(35.dp)
            )
        }

        // Dropdown menu for selecting playback speed options
        if (expandedSetting) {
            Box(modifier = Modifier) {
                DropdownMenu(
                    modifier = Modifier.background(color = Color.White),
                    expanded = true, // expandedSetting
                    onDismissRequest = { onDismissDropdown() }
                ) {
                    optionList.forEach { option ->
                        DropdownMenuItem(
                            onClick = {
                                when (option.name) {
                                    "Add" -> {
                                        onAdd()
                                    }

                                    "Update" -> {
                                        onUpdate()
                                    }

                                    else -> {
                                        onDeleteDialogBox()

                                    }
                                }
                                onDismissDropdown()
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    modifier = Modifier.size(width = 24.dp, height = 24.dp),
                                    onClick = { }
                                ) {
                                    Icon(
                                        modifier = Modifier.fillMaxSize(),
                                        imageVector = option.icon,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }
                                Column(
                                    modifier = Modifier,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TextView(
                                        text = option.name,
                                        color = Color.Black,
                                        fontSize = 13.sp,
                                        style = TextStyle(lineHeight = 20.sp),
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

open class ListOfSettingMenus(var icon: ImageVector, val name: String) {
    object Add : ListOfSettingMenus(Icons.Default.Add, "Add")
    object Update : ListOfSettingMenus(Icons.Default.BrowserUpdated, "Update")
    object Delete : ListOfSettingMenus(Icons.Default.DeleteForever, "Delete")

}