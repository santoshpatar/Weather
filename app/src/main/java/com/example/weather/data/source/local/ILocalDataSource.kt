package com.primefocus.mobile.contentlive.data.source.local

import com.example.weather.data.source.IDataSource
import com.example.weather.data.source.remote.model.City
import com.example.weather.room.AppDatabase


interface ILocalDataSource {

    fun getVisitedCity(mDb: AppDatabase, callback: IDataSource.LoadDataCallback<ArrayList<City>>)
}