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

open class WeatherShowFragment : Fragment() {
    private val weatherShowViewModel: WeatherShowViewModel by viewModels()
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
            binding.viewModel!!.callWeatherData()
        }
        handler.post {
            // 画面を更新する
            binding.weatherData.text = binding.viewModel!!.weatherData.value
        }
    }
}