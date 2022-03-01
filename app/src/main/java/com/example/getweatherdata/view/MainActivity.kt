package com.example.getweatherdata.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.getweatherdata.R
import com.example.getweatherdata.databinding.ActivityMainBinding
import com.example.getweatherdata.viewmodel.WeatherShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherShowViewModel: WeatherShowViewModel by viewModels()
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        weatherShowViewModel.getCityData()
        initListener()
        weatherShowViewModel.weatherResponse.observe(this, { response->

//            if(response.weather[0].description == "clear sky" || response.weather[0].description == "mist"){
//                Glide.with(this)
//                    .load(R.drawable.ic_launcher_background)
//                    .into(binding.image)
//            }else
//                if(response.weather[0].description == "haze" || response.weather[0].description == "overcast clouds" || response.weather[0].description == "fog" ){
//                    Glide.with(this)
//                        .load(R.drawable.ic_launcher_background)
//                        .into(binding.image)
//                }else
//                    if(response.weather[0].description == "rain"){
//                        Glide.with(this)
//                            .load(R.drawable.ic_launcher_background)
//                            .into(binding.image)
//                    }
            binding.description.text=response.list[0].weather[0].description
            binding.name.text= response.list[0].dt_txt
            binding.degree.text=response.list[0].wind.deg.toString()
            binding.speed.text=response.list[0].wind.speed.toString()
            binding.temp.text=response.list[0].main.temp.toString()
            binding.humidity.text=response.list[0].main.humidity.toString()

        })
    }

    @ExperimentalCoroutinesApi
    private fun initListener()
    {
        weatherShowViewModel.setSearchQuery("osaka")
    }
}