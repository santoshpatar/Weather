package com.example.wather.data.source


import com.example.wather.data.source.remote.RemoteDataSource
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.data.source.remote.model.WeatherResponse
import com.primefocus.mobile.contentlive.data.source.local.LocalDataSource

object Repository : IRepository {

    private var remoteDataSource: RemoteDataSource? = null

    private var localDataSource: LocalDataSource? = null

    init {
        remoteDataSource = RemoteDataSource
        localDataSource = LocalDataSource
    }

    override fun getSearchResult(
        request: HashMap<String, String>,
        callback: IDataSource.LoadDataCallback<ResultResponse>) {
        remoteDataSource?.getSearchResult(request,
            object : IDataSource.LoadDataCallback<ResultResponse> {

                override fun onDataLoaded(data: ResultResponse) {
                    callback.onDataLoaded(data)
                }

                override fun onDataNotAvailable(t: Throwable) {
                    callback.onDataNotAvailable(t)
                }
            })
    }

    override fun getWeatherResult(
        request: HashMap<String, String>,
        callback: IDataSource.LoadDataCallback<WeatherData>
    ) {
        remoteDataSource?.getWeatherResult(request,
            object : IDataSource.LoadDataCallback<WeatherData> {

                override fun onDataLoaded(data: WeatherData) {
                    callback.onDataLoaded(data)
                }

                override fun onDataNotAvailable(t: Throwable) {
                    callback.onDataNotAvailable(t)
                }
            })

    }
}