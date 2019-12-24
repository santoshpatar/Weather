package com.example.wather.data.source

interface IDataSource {

    interface LoadDataCallback<T> {
        fun onDataLoaded(data: T)

        fun onDataNotAvailable(t: Throwable)
    }
}