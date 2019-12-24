package com.example.wather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.Adapter.MusicListAdapter
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.Repository
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.ResultsItem
import com.example.wather.data.source.remote.model.WeatherData
import java.util.HashMap

class WeatherViewModel :ViewModel(){

    private var mWeatherResult = MutableLiveData<WeatherData>()

    fun getWeatherData(requestBody: HashMap<String, String>){
        Repository.getWeatherResult(requestBody, object : IDataSource
        .LoadDataCallback<WeatherData> {
            override fun onDataLoaded(data: WeatherData) {
                mWeatherResult.value  = data
                Log.d("response",data.toString())
            }

            override fun onDataNotAvailable(t: Throwable) {
                Log.d("response","fails")

            }
        })
    }


    fun getWeatherResult()= mWeatherResult;
    fun resetResult() {
        mWeatherResult = MutableLiveData<WeatherData>()
    }

}