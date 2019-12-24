package com.example.wather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wather.Adapter.CityListAdapter
import com.example.wather.Adapter.MusicListAdapter
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse

class CitySearchFragmentViewModel : ViewModel(){

    var cityListAdapter = CityListAdapter()

    fun loadCityList(cityList: ArrayList<City>) {
        cityListAdapter.updateCityList(cityList)
    }

}