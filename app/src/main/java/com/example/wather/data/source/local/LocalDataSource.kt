package com.primefocus.mobile.contentlive.data.source.local

import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.City
import com.example.wather.room.AppDatabase
import com.example.wather.room.entity.CityTable
import com.example.wather.utils.ResponseMaper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.ArrayList

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
                 array = mCityDao?.loadAllCity()!!
                   if (null!= array && array?.size!!>0){
                       cityList = ResponseMaper.getVistedCityList(array!!)
                      // cityList.sortWith(comparator)
                   }
                   uiThread {
                       callback.onDataLoaded(cityList)
            }


        }


    }

    // A comparator to compare last names of Name
    class CityComparator: Comparator<City>{
        override fun compare(o1: City?, o2: City?): Int {
            if(o1 == null || o2 == null)
                return 0
            return -o1.timeStamp.compareTo(o2.timeStamp)
        }
    }
}