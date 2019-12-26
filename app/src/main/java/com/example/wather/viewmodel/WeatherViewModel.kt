package com.example.wather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.Repository
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.room.dao.CityDao
import com.example.wather.room.DbUtils
import com.example.wather.utils.AppUtils
import org.jetbrains.anko.doAsync
import java.util.HashMap

/**
 * this class is view model for Weather View
 * santosh
 */
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
                AppUtils.hideProgressDialog()
                Log.d("response","fails")

            }
        })
    }


    fun getWeatherResult()= mWeatherResult;
    fun resetResult() {
        mWeatherResult = MutableLiveData<WeatherData>()
    }

    /**
     *  this method to insert or update city to local db
     * @param cityDao  dao object for city_item table
     * @param city  selected city data from city list
     */

    fun insertCity(cityDao: CityDao, city: City){
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