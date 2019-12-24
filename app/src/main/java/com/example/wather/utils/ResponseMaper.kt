package com.example.wather.utils

import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse

 class ResponseMaper {

    companion object {

        public fun getCityList(resultResponse: ResultResponse): ArrayList<City> {
            val cityList = java.util.ArrayList<City>()
            for (items in resultResponse?.search_api?.result){
                val cityResponse = City()
                cityResponse.areaName = items?.areaName[0]?.value
                cityResponse.country = items?.country[0]?.value
                cityResponse.region = items?.region[0]?.value
               // cityResponse.weatherUrl = items?.weatherUrl[0]?.value
                cityResponse.latitude = items?.latitude
                cityResponse.longitude = items.longitude
                cityList.add(cityResponse)
            }

            return cityList
        }


    }




}