package com.example.getweatherdata.viewmodel

import androidx.lifecycle.ViewModel
import com.example.getweatherdata.model.Weather

class WeatherShowViewModel : ViewModel() {
    private val weather = Weather()
    val weatherData = weather.weatherData
    fun callWeatherData() = weather.callWeatherAPI()
}