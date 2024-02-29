package com.example.app_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.app_a1.data.DataManager
import com.example.app_a1.ui.MainScreenLayout
import com.example.app_a1.ui.StudentListView
import com.example.app_a1.ui.theme.App_A1Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssets1(applicationContext)
            DataManager.loadAssets2(applicationContext)
        }
        setContent {
            App_A1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    if (DataManager.isStudentDataLoaded.value && DataManager.isCompanyDataLoaded.value) {
        if(DataManager.currentPage.value == Pages.MAIN) {
            MainScreenLayout { DataManager.switchPages() }
        } else {
            StudentListView(studentList = DataManager.studentDataList)
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Loading ...", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

enum class Pages{
    MAIN,
    STUDENT_DETAILS
}