package com.example.wather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.Adapter.MusicListAdapter
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.Repository
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.ResultsItem
import com.example.wather.room.AppDatabase
import java.util.HashMap

class CityListViewModel :ViewModel(){

    lateinit var allMusicList : ArrayList<ResultsItem>
     var musicListAdapter = MusicListAdapter()
    private var mSearchResult = MutableLiveData<ResultResponse>()
    private var mVisitedCity = MutableLiveData<ArrayList<City>>()

    fun loadMusic(data: ResultResponse?) {
        allMusicList = ArrayList()
        //allMusicList.addAll(data?.search_api!!)
        musicListAdapter.updateMusicList(allMusicList)
    }

    fun getSearchData(requestBody: HashMap<String, String>){
        Repository.getSearchResult(requestBody, object : IDataSource
        .LoadDataCallback<ResultResponse> {
            override fun onDataLoaded(data: ResultResponse) {
                mSearchResult.value  = data
                Log.d("response",data.toString())
            }

            override fun onDataNotAvailable(t: Throwable) {
                Log.d("response","fails")

            }
        })
    }

    fun getVisitedCity(mDb: AppDatabase){
        Repository.getVisitedCity(mDb, object : IDataSource
        .LoadDataCallback<ArrayList<City>> {
            override fun onDataLoaded(data: ArrayList<City>) {
                mVisitedCity.value  = data
                Log.d("response",data.toString())
            }
            override fun onDataNotAvailable(t: Throwable) {
                Log.d("response","fails")

            }
        })
    }


    fun getSearchResult()= mSearchResult;
    fun getVisitedCity()= mVisitedCity;
    fun setSearchResult(){
       mSearchResult = MutableLiveData<ResultResponse>()
    }


}