package com.example.wather.data.source

import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.data.source.remote.model.WeatherResponse

interface IRepository {

    fun getSearchResult( request: HashMap<String, String>,
                         callback: IDataSource.LoadDataCallback<ResultResponse>)

    fun getWeatherResult( request: HashMap<String, String>,
                         callback: IDataSource.LoadDataCallback<WeatherData>)

}