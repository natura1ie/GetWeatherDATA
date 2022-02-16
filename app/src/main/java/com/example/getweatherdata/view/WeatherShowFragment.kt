package com.example.getweatherdata.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.getweatherdata.R
import com.example.getweatherdata.databinding.WeatherShowMainBinding
import com.example.getweatherdata.viewmodel.WeatherShowViewModel
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

open class WeatherShowFragment : Fragment() {
    private val weatherShowViewModel: WeatherShowViewModel by viewModels()
    private var weather = ""
    open lateinit var binding: WeatherShowMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_show_main, container, false)
        binding.viewModel = weatherShowViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBlocking {
            // withContextをしないと画面を更新する前にアプリが終わってしまう
            withContext(Dispatchers.Default) {
                getWeatherData()
            }
        }
    }

    private fun getWeatherData() = runBlocking {
        // 非同期処理が終わるのを待ってUIスレッドで画面更新を行う必要があるためハンドラーを用意
        val handler = Handler(Looper.getMainLooper())
        withContext(Dispatchers.Default) {
            // 取得した自分のAPIキーを入力
            val apiKEY = "(TODO:APIキーを入力)"
            // 取得したい都道府県の緯度と経度を入力
            val lat = "(TODO:緯度を入力)"
            val lon = "(TODO:経度を入力)"

            // 天気情報を取得
            val url =
                URL("https://api.openweathermap.org/data/2.5/forecast?lat=$lat&lon=$lon&lang=ja&units=metric&APPID=$apiKEY")
            val readData = BufferedReader(InputStreamReader(url.openStream()))
            val json = JSONObject(readData.readText())
            val list = json.getJSONArray("list")
            for (i in 0 until list.length()) {
                val weatherList = list.getJSONObject(i)
                val weatherObject = weatherList.getJSONArray("weather").getJSONObject(0)
                // 日時を取得
                val dtText = weatherList.getString("dt_txt")
                // 天気を取得
                val descriptionText = weatherObject.getString("description")
                weather += "$dtText〜$descriptionText\n"
                withContext(Dispatchers.Default) {
                    binding.viewModel!!.setWeatherData(weather)
                }
            }
        }
        handler.post {
            // 画面を更新する
            binding.weatherData.text = binding.viewModel!!.getWeatherData()
        }
    }
}