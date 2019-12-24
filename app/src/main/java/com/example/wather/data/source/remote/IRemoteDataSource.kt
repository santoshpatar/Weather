package com.example.wather.data.source.remote



import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.data.source.remote.model.WeatherResponse

interface IRemoteDataSource : IDataSource {

    fun getSearchResult( request: HashMap<String, String>, callback: IDataSource.LoadDataCallback<ResultResponse>)

    fun getWeatherResult( request: HashMap<String, String>, callback: IDataSource.LoadDataCallback<WeatherData>)


}

