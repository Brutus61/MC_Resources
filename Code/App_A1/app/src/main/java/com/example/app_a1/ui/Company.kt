package com.example.app_a1.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_a1.data.CompanyDataItem
import com.example.app_a1.data.DataManager
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun MainScreenLayout(
    onClick: () -> Unit
) {

    val existingList: MutableList<CompanyDataItem> = DataManager.companyDataList
    val companyList = remember { mutableStateListOf(*existingList.toTypedArray()) }
    var newCompanyName by remember { mutableStateOf("") }
    var newDeadline by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize()
    ) {
        CompanyListView(
            companyList = companyList,
            onClick,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = newCompanyName,
                onValueChange = { newCompanyName = it },
                label = { Text("Company Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = newDeadline,
                onValueChange = { newDeadline = it },
                label = { Text("Deadline") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (newCompanyName.isNotBlank() && newDeadline.isNotBlank()) {
                        //companyList.add(CompanyDataItem(newCompanyName, newDeadline, 3))
                        if (isValidDateFormat(newDeadline)) {
                            companyList.add(CompanyDataItem(newCompanyName, newDeadline, 3))
                        }
                    }
                }
            ) {
                Text(text = "ADD")
            }
        }
    }
}

@Composable
fun CompanyListView(companyList: MutableList<CompanyDataItem>, onClick: () -> Unit, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(companyList) { e ->
            CompanyEntry(entry = e, onClick)
        }
    }
}

@Composable
fun CompanyEntry(
    entry: CompanyDataItem,
    onClick: () -> Unit
) {
    Column (
        Modifier.padding(5.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {


        Card (
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getTimeZone("UTC")
                val parsedDate = dateFormat.parse(entry.deadline)
                Text(
                    text = entry.name,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Deadline: ${
                        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(
                            parsedDate!!
                        )
                    }"
                )
                Text("Students Eligible: ${entry.eligibleStudents}")
            }
        }
    }
}

private fun isValidDateFormat(dateString: String): Boolean {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    dateFormat.isLenient = false
    return try {
        dateFormat.parse(dateString)
        true
    } catch (e: Exception) {
        false
    }
}
