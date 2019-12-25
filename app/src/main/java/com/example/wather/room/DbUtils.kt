package com.example.wather.room

import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import java.util.*

class DbUtils {
    companion object {

        fun getCityTableItemFromCity(city :City):CityTable{
            var cityTable = CityTable()
            cityTable.areaName = city.areaName
            cityTable.country = city.country
            cityTable.latitude = city.latitude
            cityTable.longitude = city.longitude
            cityTable.region = city.region
            cityTable.timeStamp = System.currentTimeMillis()
            return cityTable;

        }
    }
}