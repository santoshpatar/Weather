package com.example.wather.data.source.remote.model

import com.google.gson.annotations.SerializedName
data class ResultResponse(
    val search_api: SearchApi
)

data class SearchApi(
    val result: List<Result>
)

data class Result(
    val areaName: List<AreaName>,
    val country: List<Country>,
    val latitude: String,
    val longitude: String,
    val population: String,
    val region: List<Region>,
    val weatherUrl: List<WeatherUrl>
)

data class AreaName(
    val value: String
)

data class Country(
    val value: String
)

data class Region(
    val value: String
)

data class WeatherUrl(
    val value: String
)
data class CountryName(
	@field:SerializedName("value")
	val  areaName:String?= null
)

