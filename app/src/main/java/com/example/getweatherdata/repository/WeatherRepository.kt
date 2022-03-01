package com.example.getweatherdata.repository

import android.util.Log
import com.example.getweatherdata.model.City
import com.example.getweatherdata.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(search:String): Flow<City> = flow {
        if (search == "osaka") {
            Log.d("main", "検索しているのは" + search + "の天気です")
        }
        val response= apiServiceImp.getCity(
            "",
            "",
            "ja",
            "metric",
            ""
        )
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}