package com.example.getweatherdata.network

import com.example.getweatherdata.model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getCityData(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("lang") lang:String,
        @Query("units") units:String,
        @Query("APPID") appId:String
    ): City
}