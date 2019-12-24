package com.example.wather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.Adapter.CityListAdapter
import com.example.wather.Adapter.MusicListAdapter
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse

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