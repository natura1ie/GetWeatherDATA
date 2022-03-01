package com.example.getweatherdata.repository

import com.example.getweatherdata.model.City
import com.example.getweatherdata.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(): Flow<City> = flow {
        val response= apiServiceImp.getCity()
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}