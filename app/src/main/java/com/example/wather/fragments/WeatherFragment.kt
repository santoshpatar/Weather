package com.example.wather.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.wather.R
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.databinding.FragmentWeatherBinding
import com.example.wather.room.AppDatabase
import com.example.wather.room.CityDao
import com.example.wather.utils.AppConstant
import com.example.wather.viewmodel.CitySearchFragmentViewModel
import com.example.wather.viewmodel.WeatherViewModel
import com.squareup.picasso.Picasso

class WeatherFragment :Fragment(){

    // private lateinit var citySearchBinding: com.example.musictest.databinding.FragmentCitySearchBinding
    private lateinit var fragmentWeatherBinding: FragmentWeatherBinding;
    private lateinit var mWeatherViewModel: WeatherViewModel
    private var mDb: AppDatabase? = null
    private var mCityDao: CityDao ? = null
    private var mCity: City ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mWeatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        fragmentWeatherBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather, container, false
        )
        fragmentWeatherBinding.watherviewmodel = mWeatherViewModel

        var city:City = arguments!!.getParcelable("SELECTED_CITY")
        mWeatherViewModel.getWeatherData(getWeatherRequest(city))
        mDb = AppDatabase.getAppDataBase(context!!)
//        mDb = context?.let {
//            AppDatabase.getAppDataBase(it)
//        }
        mCityDao = mDb?.cityDao()
        mWeatherViewModel.insertCity(mCityDao!!,city)
        return fragmentWeatherBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mWeatherViewModel.getWeatherResult().observe(this, Observer {
            bindWeatherData(it)
            //mWeatherViewModel.resetResult()
        })

    }

    private fun bindWeatherData(weatherData: WeatherData){
        Picasso.get().load(weatherData.weatherImage).into(fragmentWeatherBinding.fragmentWeatherImg)
        fragmentWeatherBinding.weatherDesc.setText(weatherData.weather)
        fragmentWeatherBinding.temperature.setText(weatherData.temperature)
        fragmentWeatherBinding.humidity.setText(weatherData.humidity)
    }

    /**
     *  Weather data API request
     *
     */
    private fun getWeatherRequest(city:City ): HashMap<String, String> {
        val request = java.util.HashMap<String, String>()
        request.put("q", city.latitude+","+city.longitude)
        request.put("num_of_days", "1")
        request.put("format", "json")
        request.put("tp", "2")
        request.put("key", AppConstant.Key)
        return request
    }





}