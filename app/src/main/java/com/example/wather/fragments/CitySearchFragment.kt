package com.example.wather.fragments

import android.os.Bundle
import android.os.Parcelable
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
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.utils.ResponseMaper
import com.example.wather.viewmodel.CitySearchFragmentViewModel

/**
 * santosh patar
 */
class CitySearchFragment :Fragment(){

   // private lateinit var citySearchBinding: com.example.musictest.databinding.FragmentCitySearchBinding
   private lateinit var citySearchBinding: com.example.wather.databinding.FragmentCitySearchBinding
    private lateinit var mCitySearchFragmentViewModel: CitySearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCitySearchFragmentViewModel = ViewModelProviders.of(this).get(CitySearchFragmentViewModel::class.java)
        citySearchBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_city_search, container, false
        )
        citySearchBinding.citysearchviewmodel = mCitySearchFragmentViewModel
        citySearchBinding.rvCityList.adapter = mCitySearchFragmentViewModel.cityListAdapter
        mCitySearchFragmentViewModel.loadCityList( arguments!!.getParcelableArrayList("SEARCH_RESULT"))

        return citySearchBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCitySearchFragmentViewModel.getClickCity().observe(this, Observer {
            openWeatherFragment(it)
        })

    }

    private fun openWeatherFragment(city: City){
        fragmentManager?.popBackStack();
        var weatherFragment = WeatherFragment()
        var bundle = Bundle()
        bundle.putParcelable("SELECTED_CITY", city)
        weatherFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, weatherFragment)?.addToBackStack(null)?.commit()

    }

}