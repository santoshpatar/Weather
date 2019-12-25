package com.example.abc.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.wather.R
import com.example.wather.viewmodel.CityListViewModel
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.fragments.CitySearchFragment
import com.example.wather.fragments.WeatherFragment
import com.example.wather.room.AppDatabase
import com.example.wather.room.CityDao
import com.example.wather.room.CityTable
import com.example.wather.utils.AppConstant
import com.example.wather.utils.ResponseMaper
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainFragment : Fragment() {
    private var mCityDao: CityDao? = null
    private var mDb: AppDatabase? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mCityListViewModel: CityListViewModel

    private lateinit var fragmentmainBinding: com.example.wather.databinding.FragmentMainBinding

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

        fragmentmainBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                if ( fragmentmainBinding.searchView.query.isNotEmpty()) {

                    mCityListViewModel.getSearchData(getSearchRequest(fragmentmainBinding.searchView.query.toString()))
                }
                return false
            }
        })



        mDb = AppDatabase.getInstance(context!!)
        mCityListViewModel.getVisitedCity(mDb!!)
//        mCityDao = mDb?.cityDao()
//
//        doAsync {
//            // Put the student in database
//            var array :Array<CityTable> = mCityDao?.loadAllUsers()!!
//
//            uiThread {
//                Log.d("abc","aaaaa"+array)
//            }
//        }


        return fragmentmainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCityListViewModel.getSearchResult().observe(this, Observer {
            openSearchResultFragment(it)
        })

        mCityListViewModel.getVisitedCityResult().observe(this, Observer {
            if(it!= null){
                mCityListViewModel.loadVisitedCity(it)
            }
        })

        mCityListViewModel.getClickCity().observe(this, Observer {
            openWeatherFragment(it)
        })

    }


    /**
     *  to get search query request
     */
    private fun getSearchRequest(searchText: String): HashMap<String, String> {
        val request = java.util.HashMap<String, String>()
        request.put("query", searchText)
        request.put("num_of_results", "5")
        request.put("format", "json")
        request.put("num_of_results", "5")
        request.put("key", AppConstant.Key)
        return request
    }

    private fun openSearchResultFragment(result: ResultResponse){
      var citySearchFragment = CitySearchFragment()
        var bundle = Bundle()
        bundle.putParcelableArrayList("SEARCH_RESULT",ResponseMaper.getCityList(result))
        citySearchFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, citySearchFragment)?.addToBackStack(null)?.commit()
        mCityListViewModel.setSearchResult()
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
