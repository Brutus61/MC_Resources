package com.example.app_a2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_a2.data.CompanyListItem
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Composable
fun CompanyListView(dataList: Array<CompanyListItem>) {
    CompanyEntryList(dataList = dataList)
}

@Composable
fun CompanyEntryList(dataList: Array<CompanyListItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
        items(dataList){
            CompanyEntry(entry = it)
        }
    })
}

@Composable
fun CompanyEntry(entry: CompanyListItem) {
    Card (
        modifier = Modifier
            .padding(10.dp)
    ){
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
            Text(text = "Deadline: ${ SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(
                parsedDate!!
            ) }")
            Text("Status: Not Applied")
        }
    }
}

@Preview
@Composable
fun MyPreview() {
    CompanyEntry(entry = CompanyListItem("Google", "23rd March 2023"))
}