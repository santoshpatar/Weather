package com.primefocus.mobile.contentlive.data.source.local

import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse


interface ILocalDataSource {

    fun getVisitedCity(callback: IDataSource.LoadDataCallback<ArrayList<City>>)
}