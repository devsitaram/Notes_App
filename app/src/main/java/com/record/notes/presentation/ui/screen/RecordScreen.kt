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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.filled.Money
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
import com.record.notes.data.source.local.NewInfo
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
                    newInfo = listOf(NewInfo(newDate = "23-01-2023", work = "Jogi", amounts = "10,000")),
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
            Spacer(modifier = Modifier.fillMaxWidth().height(2.dp))
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
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
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
                            modifier = Modifier.size(20.dp)
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
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
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
                            modifier = Modifier.size(20.dp)
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
                        leadingIcon = {
                            VectorIconView(
                                imageVector = Icons.Default.Money,
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                        },
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
                    value = descriptions.toString(),
                    onValueChange = { descriptions = it },
                    label = "Description/विवरण",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.ContentPaste,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
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
                    value = phoneNumber.toString(),
                    onValueChange = { phoneNumber = it },
                    label = "Number/फोन न.",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.Phone,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // nickname
                InputTextFieldView(
                    value = nickname.toString(),
                    onValueChange = { nickname = it },
                    label = "Nickname/उपनाम",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.TextIncrease,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // location
                InputTextFieldView(
                    value = location.toString(),
                    onValueChange = { location = it },
                    label = "Location/स्थान",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.EditLocationAlt,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                )

                // emailAddress
                InputTextFieldView(
                    value = emailAddress.toString(),
                    onValueChange = { emailAddress = it },
                    label = "Email/इमेल",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = {
                        VectorIconView(
                            imageVector = Icons.Default.MailOutline,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
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
                        fontSize = 15.sp,
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