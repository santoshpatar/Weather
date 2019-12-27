package com.example.weather.room

import com.example.weather.data.source.remote.model.City
import com.example.weather.room.entity.CityTable

class DbUtils {
    companion object {

        fun getCityTableItemFromCity(city :City): CityTable {
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