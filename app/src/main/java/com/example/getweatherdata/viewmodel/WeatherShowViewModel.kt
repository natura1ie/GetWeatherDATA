package com.example.getweatherdata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherShowViewModel : ViewModel() {
    var weatherData = MutableLiveData("")

    fun setWeatherData(getData: String) {
        weatherData.postValue(getData)
    }

    fun getWeatherData(): String {
        return weatherData.value!!
    }
}