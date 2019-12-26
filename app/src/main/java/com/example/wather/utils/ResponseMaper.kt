package com.example.wather.utils

import com.example.wather.data.source.remote.model.City
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.data.source.remote.model.WeatherResponse
import com.example.wather.room.entity.CityTable

class ResponseMaper {

    companion object {

        public fun getCityList(resultResponse: ResultResponse): ArrayList<City> {
            val cityList = java.util.ArrayList<City>()
            if(null!= resultResponse && null!=resultResponse.search_api ) {
                for (items in resultResponse?.search_api?.result) {
                    val cityResponse = City()
                    cityResponse.areaName = items?.areaName[0]?.value
                    cityResponse.country = items?.country[0]?.value
                    cityResponse.region = items?.region[0]?.value
                    // cityResponse.weatherUrl = items?.weatherUrl[0]?.value
                    cityResponse.latitude = items?.latitude
                    cityResponse.longitude = items.longitude
                    cityList.add(cityResponse)
                }
            }

            return cityList
        }


        public fun getWeather(weatherResponse: WeatherResponse): WeatherData {
            val waterData = WeatherData()
            if(null!= weatherResponse.data.current_condition.get(0) && null!= weatherResponse.data.current_condition.get(0).weatherIconUrl.get(0) ) {
                waterData.weatherImage =
                    weatherResponse.data.current_condition.get(0).weatherIconUrl.get(0).value;
            }

            if(null!= weatherResponse.data.current_condition.get(0) && null!= weatherResponse.data.current_condition.get(0).weatherDesc.get(0) ) {

                waterData.weather =
                    weatherResponse.data.current_condition.get(0).weatherDesc.get(0).value;
            }

            if(null!= weatherResponse.data.current_condition.get(0)) {

                waterData.temperature =
                    weatherResponse.data.current_condition.get(0).temp_C + " " + "°C"
                waterData.humidity = weatherResponse.data.current_condition.get(0).humidity
            }

            return waterData
        }

        public fun getVistedCityList(array :Array<CityTable>): ArrayList<City> {
            val cityList = java.util.ArrayList<City>()
            for (items in array){
                val cityResponse = City()
                cityResponse.areaName = items?.areaName
                cityResponse.country = items?.country
                cityResponse.region = items?.region
                cityResponse.latitude = items?.latitude
                cityResponse.longitude = items.longitude
                cityResponse.timeStamp = items.timeStamp
                //cityResponse.population = item.p
                cityList.add(cityResponse)
            }
            return cityList
        }




    }




}