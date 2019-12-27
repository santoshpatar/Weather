package com.example.weather.data.source

interface IDataSource {

    interface LoadDataCallback<T> {
        fun onDataLoaded(data: T)

        fun onDataNotAvailable(t: Throwable)
    }
}