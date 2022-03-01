package com.example.getweatherdata.network

import com.example.getweatherdata.model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    suspend fun getCityData(
    ): City
//    @GET("weather/")
//    suspend fun getCityData(
//        @Query("q") q:String,
//        @Query("appid") appId:String
//    ): City
}