package com.example.wather.data.source

import android.content.Context
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.data.source.remote.model.WeatherResponse
import com.example.wather.room.AppDatabase

interface IRepository {

    fun getSearchResult( request: HashMap<String, String>,
                         callback: IDataSource.LoadDataCallback<ResultResponse>)

    fun getWeatherResult( request: HashMap<String, String>,
                         callback: IDataSource.LoadDataCallback<WeatherData>)

    fun getVisitedCity(mDb: AppDatabase, callback: IDataSource.LoadDataCallback<ArrayList<City>>)

}