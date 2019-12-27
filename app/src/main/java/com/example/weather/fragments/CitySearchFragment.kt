package com.example.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.R
import com.example.weather.data.source.remote.model.City
import com.example.weather.viewmodel.CitySearchFragmentViewModel

/**
 * This Fragment to show city search result
 * santosh patar
 */
class CitySearchFragment :Fragment(){
   private lateinit var citySearchBinding: com.example.weather.databinding.FragmentCitySearchBinding
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

    /**
     * this method to open Weather Fragment as per user selected city
     * @param city : user selected city form Visited city list
     *
     */
    private fun openWeatherFragment(city: City){
        fragmentManager?.popBackStack();
        var weatherFragment = WeatherFragment()
        var bundle = Bundle()
        bundle.putParcelable("SELECTED_CITY", city)
        weatherFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, weatherFragment)?.addToBackStack(null)?.commit()

    }

}