package com.example.getweatherdata.network

import com.example.getweatherdata.model.City
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {

    suspend fun getCity(lat:String,lon:String,lang:String,units:String,APPID:String): City = apiService.getCityData(lat,lon,lang,units,APPID)
}