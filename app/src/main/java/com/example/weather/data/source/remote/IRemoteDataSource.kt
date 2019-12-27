package com.example.weather.data.source.remote



import com.example.weather.data.source.IDataSource
import com.example.weather.data.source.remote.model.ResultResponse
import com.example.weather.data.source.remote.model.WeatherData

interface IRemoteDataSource : IDataSource {

    fun getSearchResult( request: HashMap<String, String>, callback: IDataSource.LoadDataCallback<ResultResponse>)

    fun getWeatherResult( request: HashMap<String, String>, callback: IDataSource.LoadDataCallback<WeatherData>)


}

