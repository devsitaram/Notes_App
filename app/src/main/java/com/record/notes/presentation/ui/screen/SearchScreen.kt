package com.record.notes.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionResult
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.record.notes.presentation.ui.components.TextView
import com.record.notes.presentation.ui.components.VectorIconView
import com.record.notes.R
import com.record.notes.presentation.viewmodel.HomeViewModel
import com.record.notes.presentation.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val customerDetails = searchViewModel.searchCustomer.value

    var queryText by remember { mutableStateOf("") }
    var isPlaying by remember { mutableStateOf(true) }

    val compositionResult: LottieCompositionResult =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.search_animation))
    val progress by animateLottieCompositionAsState(
        composition = compositionResult.value,
        isPlaying = isPlaying,
        iterations = LottieConstants.IterateForever,
        speed = 0.75f
    )

    if (customerDetails.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (customerDetails.isError.isNotBlank()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            TextView(text = customerDetails.isError)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp), //.padding(vertical = 5.dp, horizontal = 8.dp)
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = queryText,
                    onValueChange = {
                        queryText = it
                        searchViewModel.searchQuery(query = queryText)
                    },
                    placeholder = { TextView(text = "Search...") },
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        VectorIconView(imageVector = Icons.Default.Search)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                )
            }

            customerDetails.isData?.let {
                val customerId = customerDetails.isData?.customerId
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (queryText.isNotEmpty()) {
                        isPlaying = false
                        CustomerDetailsBox(
                            dateAndTime = customerDetails.isData?.dateAndTime.toString(),
                            fullName = customerDetails.isData?.fullName.toString(),
                            work = customerDetails.isData?.status.toString(),
                            status = customerDetails.isData?.dateAndTime.toString(),
                            amounts = customerDetails.isData?.amounts.toString(),
                            descriptions = customerDetails.isData?.descriptions.toString(),
                            phoneNumber = customerDetails.isData?.phoneNumber.toString(),
                            nickname = customerDetails.isData?.dateAndTime.toString(),
                            location = customerDetails.isData?.location.toString(),
                            emailAddress = customerDetails.isData?.emailAddress.toString()
                        ){
                            homeViewModel.deleteCustomer(customerId)
                            queryText = ""
                        }
                    } else {
                        isPlaying = true
                    }
                }
            }
            // search lottie animation
            if (isPlaying) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimation(
                        composition = compositionResult.value,
                        progress = progress,
                        modifier = Modifier.size(120.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomerDetailsBox(
    dateAndTime: String?,
    fullName: String?,
    work: String?,
    status: String?,
    nickname: String?,
    amounts: String?,
    descriptions: String?,
    phoneNumber: String?,
    location: String?,
    emailAddress: String?,
    onDelete: ()-> Unit
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
            if (amounts.toString().isNotEmpty()) {
                TextView(text = "Amounts: " + amounts.toString())
            }
            if (descriptions.toString().isNotEmpty()) {
                TextView(text = "Descriptions: " + descriptions.toString())
            }
            if (phoneNumber.toString().isNotEmpty()) {
                TextView(text = "Phone No: " + phoneNumber.toString())
            }
            if (location.toString().isNotEmpty()) {
                TextView(text = "Location: " + location.toString())
            }
            if (emailAddress.toString().isNotEmpty()) {
                TextView(text = "Email: " + emailAddress.toString())
            }
        }
    }
}