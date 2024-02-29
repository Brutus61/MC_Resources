package com.example.app_a1.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_a1.data.DataManager
import com.example.app_a1.data.StudentDataItem

@Composable
fun StudentListView(studentList: MutableList<StudentDataItem>) {

    BackHandler {
        DataManager.switchPages()
    }

    LazyColumn {
        items(studentList) { e ->
            StudentEntry(entry = e)
        }
    }
}

@Composable
fun StudentEntry(entry: StudentDataItem) {
    Card (
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = entry.rollNo,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = if (entry.eligible) {
                    "Eligible"
                } else {
                    "Data Not Received"
                }
            )
        }
    }
}