package com.primefocus.mobile.contentlive.data.source.local

import android.content.Context
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.room.AppDatabase
import com.example.wather.room.CityDao
import com.example.wather.room.CityTable
import com.example.wather.utils.ResponseMaper
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object LocalDataSource : ILocalDataSource {

    override fun getVisitedCity(
        mDb: AppDatabase, callback: IDataSource.LoadDataCallback<ArrayList<City>>
    ) {
        // get dta from db
        var cityList:ArrayList<City> = ArrayList()
        var array :Array<CityTable>?= null;
         var mCityDao= mDb.cityDao()

               doAsync {
                   // Put the student in database
             array = mCityDao?.loadAllUsers()!!

        }

        if (null!= array && array?.size!!>0){
            cityList = ResponseMaper.getVistedCityList(array!!)
        }
        callback.onDataLoaded(cityList)
    }
}