package com.example.app_a2.data

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson

object DataManager {

    var dataList = emptyArray<CompanyListItem>()
    var isDataLoaded = mutableStateOf(false)

    fun loadAssets(context: Context) {

        val inputStream = context.assets.open("companies.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        dataList = gson.fromJson(json, Array<CompanyListItem>::class.java)
        isDataLoaded.value = true
    }
}