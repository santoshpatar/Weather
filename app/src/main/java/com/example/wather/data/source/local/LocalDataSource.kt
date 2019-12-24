package com.primefocus.mobile.contentlive.data.source.local

import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.google.gson.Gson
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object LocalDataSource : ILocalDataSource {

    override fun getVisitedCity(
        callback: IDataSource.LoadDataCallback<ArrayList<City>>
    ) {
        // get dta from db
        var cityList:ArrayList<City> = ArrayList()
        callback.onDataLoaded(cityList)
    }
}