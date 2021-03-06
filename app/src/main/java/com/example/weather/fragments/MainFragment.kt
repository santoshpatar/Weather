package com.example.abc.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.weather.R
import com.example.weather.viewmodel.CityListViewModel
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.weather.data.source.remote.model.City
import com.example.weather.data.source.remote.model.ResultResponse
import com.example.weather.fragments.CitySearchFragment
import com.example.weather.fragments.WeatherFragment
import com.example.weather.room.AppDatabase
import com.example.weather.utils.AppUtils
import com.example.weather.utils.ResponseMaper

/**
 * this is main fragment, which is hold  visited city list recycle view and Search bar
 */
class MainFragment : Fragment() {
    private var mDb: AppDatabase? = null
    private var mCitySearchFragment: CitySearchFragment? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mCityListViewModel: CityListViewModel

    private lateinit var fragmentmainBinding: com.example.weather.databinding.FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mCityListViewModel = ViewModelProviders.of(this).get(CityListViewModel::class.java)
        fragmentmainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )
        fragmentmainBinding.listviewmodel = mCityListViewModel
        fragmentmainBinding.recyclerView.adapter = mCityListViewModel.cityListAdapter
        mCitySearchFragment = null
        fragmentmainBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (fragmentmainBinding.searchView.query.isNotEmpty()) {
                    AppUtils.showProgressDialog(context, "Loading...", false)
                    mCityListViewModel.getSearchData(
                        mCityListViewModel.getSearchRequest(
                            fragmentmainBinding.searchView.query.toString()
                        )
                    )
                }
                return false
            }
        })

        mDb = AppDatabase.getInstance(context!!)
        mCityListViewModel.getVisitedCity(mDb!!)

        return fragmentmainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCityListViewModel.getSearchResult().observe(this, Observer {
            AppUtils.hideProgressDialog()
            if (null != it && null != it.search_api) {
                openSearchResultFragment(it)
            } else {
                Toast.makeText(
                    context,
                    "City not found, please change your query",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        mCityListViewModel.getVisitedCityResult().observe(this, Observer {
            if (it != null) {
                mCityListViewModel.loadVisitedCity(it)
            }
        })

        mCityListViewModel.getClickCity().observe(this, Observer {
            if (it != null) {
                openWeatherFragment(it)
            }
        })

    }

    /**
     * this method to open SearchResultFragment
     * @param result : city serach response
     *
     */
    private fun openSearchResultFragment(result: ResultResponse) {
        if (null == mCitySearchFragment) {
            mCitySearchFragment = CitySearchFragment()
            var bundle = Bundle()
            bundle.putParcelableArrayList("SEARCH_RESULT", ResponseMaper.getCityList(result))
            mCitySearchFragment?.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, mCitySearchFragment!!)?.addToBackStack(null)?.commit()
            mCityListViewModel.setSearchResult()
        }
    }

    /**
     * this method to open Weather Fragment as per user selected city
     * @param city : user selected city form Visited city list
     *
     */
    private fun openWeatherFragment(city: City) {
        fragmentManager?.popBackStack();
        var weatherFragment = WeatherFragment()
        var bundle = Bundle()
        bundle.putParcelable("SELECTED_CITY", city)
        weatherFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, weatherFragment)?.addToBackStack(null)?.commit()
        mCityListViewModel.reSetVisitedCity()

    }


}
