package com.example.getweatherdata.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getweatherdata.model.City
import com.example.getweatherdata.model.Weather
import com.example.getweatherdata.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherShowViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    val weatherResponse: MutableLiveData<City> = MutableLiveData()
    @ExperimentalCoroutinesApi
    private val searchChannel = ConflatedBroadcastChannel<String>()

    @ExperimentalCoroutinesApi
    fun setSearchQuery(search:String)
    {
        searchChannel.offer(search)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getCityData()
    {
        viewModelScope.launch {
            searchChannel.asFlow()
                .flatMapLatest {
                    weatherRepository.getCityData()
                }.catch {e->
                    Log.d("main", "${e.message}")
                }.collectLatest { response->
                    weatherResponse.value=response
                }
        }
    }


}