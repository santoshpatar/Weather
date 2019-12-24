package com.example.wather.Activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.abc.ui.main.MainFragment
import com.example.wather.R

/**
 *
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }


//    private lateinit var mListViewModel: CityListViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        var binding = DataBindingUtil.setContentView<com.example.wather.databinding.ActivityMainBinding>(
//            this@MainActivity,
//            com.example.wather.R.layout.activity_main
//        )
//        mListViewModel = ViewModelProviders.of(this).get(CityListViewModel::class.java)
//        binding.recyclerView.adapter = mListViewModel.musicListAdapter
//        mListViewModel.getSearchData(getSearchRequest("jack+johnson"))
//        mListViewModel.getSearchResult().observe(this, Observer {
//            mListViewModel.loadMusic(it)
//        })
//
//
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (binding.searchView.query.isNotEmpty()) {
//
//                    var citySearchFragment = CitySearchFragment()
//                    supportFragmentManager.beginTransaction().add(R.id.container, citySearchFragment).addToBackStack(null).commit()
//
//                   // mListViewModel.getSearchData(getSearchRequest(binding.searchView.query.toString()))
//                }
//                return false
//            }
//        })
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
//
//    }
//
//    /**
//     *  to get search query request
//     */
//    private fun getSearchRequest(key: String): HashMap<String, String> {
//        val request = java.util.HashMap<String, String>()
//        request.put("term", key)
//
//        return request
//    }
}
