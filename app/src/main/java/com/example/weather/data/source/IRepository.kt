package com.example.weather.data.source

import com.example.weather.data.source.remote.model.City
import com.example.weather.data.source.remote.model.ResultResponse
import com.example.weather.data.source.remote.model.WeatherData
import com.example.weather.room.AppDatabase

interface IRepository {

    fun getSearchResult( request: HashMap<String, String>,
                         callback: IDataSource.LoadDataCallback<ResultResponse>)

    fun getWeatherResult( request: HashMap<String, String>,
                         callback: IDataSource.LoadDataCallback<WeatherData>)

    fun getVisitedCity(mDb: AppDatabase, callback: IDataSource.LoadDataCallback<ArrayList<City>>)

}