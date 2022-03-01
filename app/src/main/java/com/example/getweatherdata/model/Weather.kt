package com.example.getweatherdata.model

import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

data class Weather(
    val description:String,
    val icon:String
) {

}
//class Weather {
//    private var weather = ""
//    // ArrayList<String>()のMutableLiveData版
//    private var personalData = mutableListOf<String>()
//
//    var weatherData = MutableLiveData("")
//    fun callWeatherAPI() {
//        // 天気情報を取得するのに必要な情報を持ってくる
//        callPersonalData()
//
//        // 天気情報を取得
//        val url =
//            URL("https://api.openweathermap.org/data/2.5/forecast?lat=${personalData[0]}&lon=${personalData[1]}&lang=ja&units=metric&APPID=${personalData[2]}")
//        val readData = BufferedReader(InputStreamReader(url.openStream()))
//        val json = JSONObject(readData.readText())
//        val list = json.getJSONArray("list")
//        for (i in 0 until list.length()) {
//            val weatherList = list.getJSONObject(i)
//            val weatherObject = weatherList.getJSONArray("weather").getJSONObject(0)
//            // 日時を取得
//            val dtText = weatherList.getString("dt_txt")
//            // 天気を取得
//            val descriptionText = weatherObject.getString("description")
//            weather += "$dtText〜$descriptionText\n"
//            weatherData.postValue(weather)
//        }
//    }
//
//    private fun callPersonalData() {
//        // [0]:取得したい都道府県の緯度を入力
//        personalData.add("")
//        // [1]:取得したい都道府県の経度を入力
//        personalData.add("")
//        // [2]:取得した自分のAPIキーを入力
//        personalData.add("")
//    }
//}