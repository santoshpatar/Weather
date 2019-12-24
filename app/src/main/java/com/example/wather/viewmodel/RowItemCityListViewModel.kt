package com.example.wather.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultsItem
import com.squareup.picasso.Picasso

class RowItemCityListViewModel : ViewModel(){

    private val mAreaName = MutableLiveData<String>()
    private val mRegion = MutableLiveData<String>()
    private val mWeatherUrl = MutableLiveData<String>()



    fun bind(city: City) {
        mAreaName.value = city.areaName;
        mRegion.value = city.region
        mWeatherUrl.value = city.weatherUrl

    }

    fun getAreaName() = mAreaName
    fun getRegion() = mRegion
    fun getCityWeatherUrl() = mWeatherUrl



    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView, imageUrl: String) {
            Picasso.get().load(imageUrl).into(view)
        }
    }

}