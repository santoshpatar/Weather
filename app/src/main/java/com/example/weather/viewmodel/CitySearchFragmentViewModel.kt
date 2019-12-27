package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.Adapter.CityListAdapter
import com.example.weather.data.source.remote.model.City
/**
 *  This class is view model Search result fragment
 */
class CitySearchFragmentViewModel : ViewModel(),CityListAdapter.ItemClickListener{

    var cityListAdapter = CityListAdapter(this)
    private val mClickCity = MutableLiveData<City>()

    fun loadCityList(cityList: ArrayList<City>) {
        cityListAdapter.updateCityList(cityList)
    }

    override fun onItemClick(city: City) {
        mClickCity.value =  city
    }

    fun getClickCity() = mClickCity
}