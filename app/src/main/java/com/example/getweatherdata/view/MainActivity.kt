package com.example.getweatherdata.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.getweatherdata.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val weatherShowFragment = WeatherShowFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // パラメータを設定
        fragmentTransaction.replace(R.id.container, weatherShowFragment)
        fragmentTransaction.commit()
    }
}