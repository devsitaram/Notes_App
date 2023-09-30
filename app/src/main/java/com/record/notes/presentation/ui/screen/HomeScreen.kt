package com.record.notes.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.record.notes.R
import com.record.notes.presentation.ui.components.ButtonAppBar
import com.record.notes.presentation.ui.components.TextView
import com.record.notes.presentation.ui.components.VectorIconView
import com.record.notes.presentation.ui.navigations.BtnNavScreen
import com.record.notes.presentation.viewmodel.HomeViewModel

@Composable
fun HomeViewScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val customerList = viewModel.customerList.value

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

    customerList.isData?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonAppBar(title = "Home Page")
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
                        val descriptions = customer?.descriptions
                        val phoneNumber = customer?.phoneNumber
                        val nickname = customer?.nickname
                        val location = customer?.location
                        val emailAddress = customer?.emailAddress
                        CustomerListBox(
                            dateAndTime = dateAndTime,
                            fullName = fullName,
                            work = work,
//                            amount = amount,
                            status = status,
//                            descriptions = descriptions,
//                            phoneNumber = phoneNumber,
                            nickname = nickname,
//                            location = location,
//                            emailAddress = emailAddress,
//                            customerId = customerId,
                            onDelete = {
                                    viewModel.deleteCustomer(customerId)
                                    isDelete = !isDelete

                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomerListBox(
    dateAndTime: String?,
    fullName: String?,
    work: String?,
    status: String?,
    nickname: String?,
    onDelete: () -> Unit
) {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp), verticalAlignment = Alignment.CenterVertically
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { onDelete() }) {
                    VectorIconView(imageVector = Icons.Default.Delete, tint = Color.Gray)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, bottom = 15.dp)
        ) {
            TextView(text = "Date: " + dateAndTime.toString())
            TextView(text = "Work: " + work.toString())
            TextView(text = "Status: " + status.toString())
            if (nickname.toString().isNotEmpty()) {
                TextView(text = "NickName: " + nickname.toString())
            }
        }
    }
}