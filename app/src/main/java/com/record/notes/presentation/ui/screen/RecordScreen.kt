package com.record.notes.presentation.ui.screen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.EditLocationAlt
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.TextIncrease
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.record.notes.R
import com.record.notes.data.source.local.CustomerEntity
import com.record.notes.presentation.ui.components.ButtonView
import com.record.notes.presentation.ui.components.DateTimePickerView
import com.record.notes.presentation.ui.components.InputTextFieldView
import com.record.notes.presentation.ui.components.TextView
import com.record.notes.presentation.ui.components.VectorIconView
import com.record.notes.presentation.ui.navigations.BtnNavScreen
import com.record.notes.presentation.viewmodel.RecordViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun RecordViewScreen(
    navController: NavHostController,
    viewModel: RecordViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    // Initializing a Calendar and Fetching current year, month and day
    val mCalendar = Calendar.getInstance()
    val year = mCalendar.get(Calendar.YEAR)
    val month = mCalendar.get(Calendar.MONTH)
    val day = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    // store date in string format
    var inputDate by remember { mutableStateOf("") }

    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, mDayOfMonth: Int ->
            inputDate = "$mDayOfMonth/${month + 1}/$year"
        }, year, month, day
    )

    // get data time
    val latestDate = SimpleDateFormat("dd-MM-yyyy")
    val currentDate = latestDate.format(Date())

    // data store in variable
    var dateAndTime by remember { mutableStateOf(currentDate) }
    var fullName by remember { mutableStateOf("") }
    var work by remember { mutableStateOf("") }
    var amounts by remember { mutableStateOf("") }
    // status menus
    val statusList = arrayOf("Due/बाँकी", "Advance/अग्रिम भुक्तान", "paid/भुक्तान गरियो")
    var selectedStatusItem by remember { mutableStateOf(statusList[0]) }
    var descriptions by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }

    // validation variable
    var isFullNameEmpty by remember { mutableStateOf(false) }
    var isWorkEmpty by remember { mutableStateOf(false) }
    var isAmountEmpty by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = inputDate, block = {
        dateAndTime = inputDate.ifEmpty {
            dateAndTime
        }
    })

    // clear the text filed
    val onClearAction: () -> Unit = {
        fullName = ""
        work = ""
        amounts = ""
        descriptions = ""
        phoneNumber = ""
        emailAddress = ""
        location = ""
        nickname = ""
    }

    val onRegister: () -> Unit = {
        isFullNameEmpty = fullName.isEmpty()
        isWorkEmpty = work.isEmpty()
        isAmountEmpty = amounts.isEmpty()

        if (dateAndTime.isNotEmpty() && fullName.isNotEmpty() && work.isNotEmpty() && amounts.isNotEmpty() && selectedStatusItem.isNotEmpty()) {
            viewModel.registerDetails(
                customerEntity = CustomerEntity(
                    dateAndTime = dateAndTime,
                    fullName = fullName,
                    work = work,
                    amounts = amounts,
                    status = selectedStatusItem,
                    descriptions = descriptions,
                    phoneNumber = phoneNumber,
                    nickname = nickname,
                    location = location,
                    emailAddress = emailAddress
                )
            )
            navController.navigate(BtnNavScreen.HomeScreen.route){
                popUpTo(BtnNavScreen.HomeScreen.route){
                    inclusive = true
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 25.dp))
            TextView(
                text = "Note your details",
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            TextView(
                text = "आफ्नो विवरण नोट गर्नुहोस्|",
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { onClearAction() },
                    modifier = Modifier.padding(end = 5.dp)
                ) {
                    Column(
                        modifier = Modifier.wrapContentWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        VectorIconView(
                            imageVector = Icons.Default.CleaningServices,
                            tint = colorResource(id = R.color.teal_200)
                        )
                        Spacer(modifier = Modifier.padding(top = 2.dp))
                        TextView(text = "Clear", color = colorResource(id = R.color.teal_200))
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .background(Color.White)
            ) {
                // record date
                DateTimePickerView(
                    value = dateAndTime,
                    onValueChange = { dateAndTime = it },
                    label = "Date/मिति",
                    modifier = Modifier
                        .fillMaxWidth(),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.CalendarMonth,
                            tint = Color.Gray
                        )
                    },
                    trailingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            tint = Color.Gray,
                            modifier = Modifier.clickable {
                                mDatePickerDialog.show()
                            }
                        )
                    }
                )
                // record name
                InputTextFieldView(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Name/नाम",
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.PersonOutline,
                            tint = Color.Gray,
                        )
                    },
                    isEmpty = isFullNameEmpty,
                    errorColor = Color.Red,
                    errorMessage = "Name is empty!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )
                // work
                InputTextFieldView(
                    value = work,
                    onValueChange = { work = it },
                    label = "Work/काम",
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.DriveFileRenameOutline,
                            tint = Color.Gray
                        )
                    },
                    isEmpty = isWorkEmpty,
                    errorColor = Color.Red,
                    errorMessage = "Work is empty!",
                    maxLines = Int.MAX_VALUE,
                    singleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )
                // amounts
                InputTextFieldView(
                    value = amounts,
                    onValueChange = { amounts = it },
                    label = "Amounts/रकम",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.CurrencyRupee,
                            tint = Color.Gray,
                        )
                    },
                    isEmpty = isAmountEmpty,
                    errorColor = Color.Red,
                    errorMessage = "Amounts is empty!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // status
                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    // text field
                    OutlinedTextField(
                        value = selectedStatusItem,
                        onValueChange = { selectedStatusItem = it },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        textStyle = TextStyle(fontSize = 14.sp),
                        label = { TextView(text = "Status/स्थिति", color = Color.Gray) },
                        readOnly = true,
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        // all the items are added vertically
                        statusList.forEach { selectedOption ->
                            // menu item
                            DropdownMenuItem(
                                onClick = {
                                    selectedStatusItem = selectedOption
                                    expanded = false
                                }
                            ) {
                                TextView(
                                    text = selectedOption,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black,
                                        lineHeight = 20.sp,
                                    ),
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }

                // descriptions
                InputTextFieldView(
                    value = descriptions,
                    onValueChange = { descriptions = it },
                    label = "Description/विवरण",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.ContentPaste,
                            tint = Color.Gray,
                        )
                    },
                    maxLines = Int.MAX_VALUE,
                    singleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // phoneNumber
                InputTextFieldView(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = "Number/फोन न.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.Phone,
                            tint = Color.Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // nickname
                InputTextFieldView(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = "Nickname/उपनाम",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.TextIncrease,
                            tint = Color.Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // location
                InputTextFieldView(
                    value = location,
                    onValueChange = { location = it },
                    label = "Location/स्थान",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.EditLocationAlt,
                            tint = Color.Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // emailAddress
                InputTextFieldView(
                    value = emailAddress,
                    onValueChange = { emailAddress = it },
                    label = "Email/इमेल",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.MailOutline,
                            tint = Color.Gray,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))

                // login button
                ButtonView(
                    onClick = { onRegister() },
                    text = "Save",
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                )

                Spacer(modifier = Modifier.padding(top = 50.dp))

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