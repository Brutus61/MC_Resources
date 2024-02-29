package com.example.app_a1.data

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.app_a1.Pages
import com.google.gson.Gson

object DataManager {

    var companyDataList = mutableListOf<CompanyDataItem>()
    var studentDataList = mutableListOf<StudentDataItem>()

    var isCompanyDataLoaded = mutableStateOf(false)
    var isStudentDataLoaded = mutableStateOf(false)

    var currentPage = mutableStateOf(Pages.MAIN)

    fun loadAssets1(context: Context) {

        val inputStream = context.assets.open("companies.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        companyDataList = gson.fromJson(json, Array<CompanyDataItem>::class.java).toMutableList()
        isCompanyDataLoaded.value = true
    }

    fun loadAssets2(context: Context) {

        val inputStream = context.assets.open("students.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        studentDataList = gson.fromJson(json, Array<StudentDataItem>::class.java).toMutableList()
        isStudentDataLoaded.value = true
    }

    fun switchPages() {
        if(currentPage.value == Pages.MAIN) {
            currentPage.value = Pages.STUDENT_DETAILS
        } else {
            currentPage.value = Pages.MAIN
        }
    }
}