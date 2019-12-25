package com.primefocus.mobile.contentlive.data.source.local

import android.content.Context
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.room.AppDatabase


interface ILocalDataSource {

    fun getVisitedCity(mDb: AppDatabase, callback: IDataSource.LoadDataCallback<ArrayList<City>>)
}