package com.example.wather.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wather.R
import com.example.wather.data.source.remote.model.City

import com.example.wather.databinding.RowItemCityBinding
import java.util.ArrayList

class CityListAdapter(listner: CityListAdapter.ItemClickListener) : RecyclerView.Adapter<CityListAdapter.CityListViewHolder>() {


    private lateinit var mCityList: ArrayList<City>
    private val listener: CityListAdapter.ItemClickListener = listner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        val binding: RowItemCityBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            (R.layout.row_item_city),
            parent,
            false
        );
        return CityListViewHolder(binding)
    }

    fun updateCityList(cityList: List<City>?) {
        mCityList = cityList!! as ArrayList<City>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (::mCityList.isInitialized) mCityList.size else 0
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        val city = mCityList.get(position)
        holder.bind(city)
        holder.rowIteCityListViewModel
    }


    inner class CityListViewHolder(val item: RowItemCityBinding) : RecyclerView.ViewHolder(item.root) {
        val rowIteCityListViewModel = com.example.wather.viewmodel.RowItemCityListViewModel()
        fun bind(city:City) {
            item.cityItem.setOnClickListener{ listener.onItemClick(city) }
            rowIteCityListViewModel.bind(city)
            item.rowitem = rowIteCityListViewModel

        }
    }

    interface ItemClickListener {
        fun onItemClick(city:City)

    }
}