package com.example.wather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.Adapter.MusicListAdapter
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.Repository
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.ResultsItem
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.room.CityDao
import com.example.wather.room.DbUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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

    fun insertCity(cityDao: CityDao,city: City){
        doAsync {
            var _city = cityDao.findCityByLatitude(city.latitude,city.longitude)
            if(_city!= null){
                city.timeStamp = System.currentTimeMillis()
                cityDao.updateCitybyLatLong(_city.latitude,_city.longitude,System.currentTimeMillis())
                //cityDao.updateCity(DbUtils.getCityTableItemFromCity(city))
            }else{
                cityDao.insert(DbUtils.getCityTableItemFromCity(city))
            }

        }

    }

}