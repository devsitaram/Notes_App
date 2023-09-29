package com.record.notes.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.record.notes.R
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun RecordViewScreen(navController: NavHostController) {
    val context = LocalContext.current

    // get data time
    val latestDate = SimpleDateFormat("dd-MM-yyyy")
    val currentDate = latestDate.format(Date())

    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(currentDate) }
    var work by remember { mutableStateOf("") }
    var amounts by remember { mutableStateOf("") }

    // clear the text filed
    val onClearAction: () -> Unit = {
        name = ""
        work = ""
        amounts = ""
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_record), contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(130.dp)
            )

            HeadingTextComponent(
                value = stringResource(id = R.string.login_your_details),
                color = colorResource(id = R.color.black)
            )

            Spacer(modifier = Modifier.padding(top = 30.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.White)
            ) {
                // customer name
                InputTextField(
                    date,
                    onValueChange = { date = it },
                    label = stringResource(id = R.string.date),
                    painterResource = painterResource(R.drawable.ic_date_time),
                    isEmptyMessage = isEmptyMessage
                )

                // customer name
                InputTextField(
                    name,
                    onValueChange = { name = it },
                    label = stringResource(id = R.string.customer_name),
                    painterResource = painterResource(R.drawable.ic_person),
                    isEmptyMessage = isEmptyMessage
                )

                // work
                InputTextField(
                    work,
                    onValueChange = { work = it },
                    label = stringResource(id = R.string.work),
                    painterResource = painterResource(R.drawable.ic_work),
                    isEmptyMessage = isEmptyMessage
                )

                // amounts
                InputTextField(
                    amounts,
                    onValueChange = { amounts = it },
                    label = stringResource(id = R.string.amounts),
                    isEmptyMessage = isEmptyMessage
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))

                // login button
                SaveButton(
                    value = stringResource(id = R.string.save),
                    onClickAction = onClickAction
                )

                CancelButton(
                    value = stringResource(id = R.string.clear),
                    onClickAction = onClearAction
                )
            }
        }
    }
}


////    val context = (LocalContext.current as Activity)
//
//    val subjects = viewModel.subjectList.value
//
////    LaunchedEffect(key1 = Unit, block = {
////        viewModel.getSubject()
////    })
//
//    if (subjects.isLoading) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    }
//
//    if (subjects.isError.isNotBlank()) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 15.dp, vertical = 15.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            TextView(text = subjects.isError)
//        }
//    }
//
////    subjects.isData?.let {
////        Box(modifier = Modifier.fillMaxSize()) {
////            Column(
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(bottom = 50.dp),
////                horizontalAlignment = Alignment.CenterHorizontally
////            ) {
////                ButtonAppBar(title = "", navController = navController)
////                TextView(
////                    text = "Subjects",
////                    style = TextStyle(
////                        fontSize = 15.sp,
////                        color = Color.Gray,
////                        fontWeight = FontWeight.SemiBold,
////                        textAlign = TextAlign.Start
////                    ),
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(15.dp)
////                )
////
////                LazyColumn(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(start = 15.dp, end = 15.dp)
////                ) {
////                    items(it) { subject ->
////                        val subjectId = subject?.subjectId
////                        val yearlyPrice = subject?.yearlyPrice
////                        val studentSubject = subject?.studentSubject
////                        val validityStartDate = subject?.validityStartDate
////                        val level = subject?.level
////                        val packageId = subject?.packageId
////                        val packageTag = subject?.packageTag
////                        val monthlyPrice = subject?.monthlyPrice
////                        val validityEndDate = subject?.validityEndDate
////                        val halfYearlyPrice = subject?.halfYearlyPrice
////                        val assetType = subject?.assetType
////                        val photoUrl = subject?.photoUrl.toString()
////                        val isComingSoon = subject?.isComingSoon
////                        val name = subject?.name
////                        val planEndDate = subject?.planEndDate
////                        val packageGrade = subject?.packageGrade
////                        val isStudentPremium = subject?.isStudentPremium
////                        val order = subject?.order
////
////                        SubjectCardView(
////                            imageUrl = HTTPS_IMAGE_BASE_URL + photoUrl,
////                            name = name,
////                            studSubjName = subject?.studentSubject?.subjectName,
////                            onClickable = {
////                                 navController.navigate("VideoListScreen/${subjectId}/${name}")
////                            },
////                            onDelete = {
//////                                viewModel.deleteSubject(id = subjectId.toInt())
//////                                // Trigger recomposition by updating the state
//////                                viewModel.updateSubjectListAfterDelete(subjectId.toInt())
////                            }
////                        )
////
////                        // insert the data in local server if any change the remote server
////                        LaunchedEffect(key1 = subjectId, block = {
////                            val listOfSubjects = listOf(
////                                SubjectEntity(
////                                    subjectId = subjectId,
////                                    yearlyPrice = yearlyPrice,
////                                    studentSubject = studentSubject,
////                                    validityStartDate = validityStartDate,
////                                    level = level,
////                                    packageId = packageId,
////                                    packageTag = packageTag,
////                                    monthlyPrice = monthlyPrice,
////                                    validityEndDate = validityEndDate,
////                                    halfYearlyPrice = halfYearlyPrice,
////                                    assetType = assetType,
////                                    photoUrl = photoUrl,
////                                    isComingSoon = isComingSoon,
////                                    name = name,
////                                    planEndDate = planEndDate,
////                                    packageGrade = packageGrade,
////                                    isStudentPremium = isStudentPremium,
////                                    order = order
////                                )
////                            )
////                            viewModel.insertSubject(listOfSubjects)
////                        })
////                    }
////                }
////            }
////        }
////    }
//}
//
//@Composable
//fun SubjectCardView(
//    imageUrl: String,
//    name: String?,
//    studSubjName: String?,
//    onClickable: () -> Unit,
//    onDelete: (Int) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 15.dp)
//            .border(1.dp, Color.LightGray)
//            .clickable { onClickable() },
//        shape = ShapeDefaults.Medium
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            AsyncImageView(
//                model = imageUrl,
//                modifier = Modifier
//                    .size(120.dp)
//                    .padding(
//                        start = 10.dp,
//                        end = 10.dp
//                    )
//            )
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .border(1.dp, Color.LightGray),
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(15.dp)
//                ) {
//                    TextView(
//                        text = name.toString(),
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            color = Color.DarkGray,
//                            fontWeight = FontWeight.Bold,
//                        ),
//                        modifier = Modifier
//                    )
//                    TextView(
//                        text = studSubjName.toString(),
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Normal,
//                            lineHeight = 20.sp,
//                            color = Color.Gray
//                        ),
//                        modifier = Modifier.padding(top = 5.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.padding(top = 20.dp))
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//                            onDelete
//                        }
//                        .border(1.dp, Color.LightGray)
//                        .padding(10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    VectorIconView(
//                        imageVector = Icons.Default.Newspaper,
//                        contentDescription = null,
//                        tint = skyBlue,
//                        modifier = Modifier.padding(start = 5.dp)
//                    )
//                    TextView(
//                        text = "View Package Detail", style = TextStyle(
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = skyBlue
//                        ),
//                        modifier = Modifier.padding(start = 5.dp)
//                    )
//                }
//            }
//        }
//    }
//}