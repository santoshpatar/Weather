package com.example.wather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.Adapter.CityListAdapter
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.Repository
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.room.AppDatabase
import com.example.wather.utils.AppConstant
import com.example.wather.utils.AppUtils
import java.util.HashMap

/**
 *  This class is view model for Main/Visited city fragment
 */
class CityListViewModel :ViewModel(),CityListAdapter.ItemClickListener{

    private var mSearchResult = MutableLiveData<ResultResponse>()
    private var mVisitedCity = MutableLiveData<ArrayList<City>>()

    var cityListAdapter = CityListAdapter(this)
    private var mClickCity = MutableLiveData<City>()

    fun loadVisitedCity(cityList: ArrayList<City>) {
        cityListAdapter.updateCityList(cityList)
    }

    /**
     * to get list of city as per query text
     * @param requestBody : search parameter
     *
     */
    fun getSearchData(requestBody: HashMap<String, String>){
        Repository.getSearchResult(requestBody, object : IDataSource
        .LoadDataCallback<ResultResponse> {
            override fun onDataLoaded(data: ResultResponse) {
                AppUtils.hideProgressDialog()
                mSearchResult.value  = data
                Log.d("response",data.toString())
            }

            override fun onDataNotAvailable(t: Throwable) {
                AppUtils.hideProgressDialog()
                Log.d("response","fails")

            }
        })
    }

    /**
     * to get visited city from local data base
     * @param mDb : database object
     *
     */
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

    /**
     * This methods Returns an object for  Search api
     * @param searchText   searchText enter by user on search box
     *
     */
     fun getSearchRequest(searchText: String): HashMap<String, String> {
        val request = java.util.HashMap<String, String>()
        request.put("query", searchText)
        request.put("num_of_results", "5")
        request.put("format", "json")
        request.put("num_of_results", "5")
        request.put("key", AppConstant.Key)
        return request
    }


    fun getSearchResult()= mSearchResult;
    fun getVisitedCityResult()= mVisitedCity;
    fun setSearchResult(){
       mSearchResult = MutableLiveData<ResultResponse>()
    }

    fun reSetVisitedCity(){
        mClickCity = MutableLiveData<City>()
    }

    override fun onItemClick(city: City) {
        mClickCity.value =  city
    }

    fun getClickCity() = mClickCity



}