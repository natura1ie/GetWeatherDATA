package com.example.getweatherdata.model

data class WeatherList(
    val dt_txt:String,
    val main:Main,
    val weather: ArrayList<Weather>,
    val wind:Wind
) {

}
