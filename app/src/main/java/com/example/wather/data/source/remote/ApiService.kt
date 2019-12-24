package com.example.wather.data.source.remote

import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiService {
    @GET("http://api.worldweatheronline.com/premium/v1/search.ashx?")
    fun getSearchResult(@QueryMap request: HashMap<String, String>): Call<ResultResponse>

    @GET("http://api.worldweatheronline.com/premium/v1/weather.ashx?")
    fun getWeatherResult(@QueryMap request: HashMap<String, String>): Call<WeatherResponse>


}

