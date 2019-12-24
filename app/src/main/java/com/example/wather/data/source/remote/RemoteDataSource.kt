package com.example.wather.data.source.remote

import android.util.Log
import com.example.wather.data.source.ApiClient
import com.example.wather.data.source.IDataSource
import com.example.wather.data.source.remote.model.ResultResponse
import com.example.wather.data.source.remote.model.WeatherData
import com.example.wather.data.source.remote.model.WeatherResponse
import com.google.gson.Gson


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object RemoteDataSource : IRemoteDataSource {

    var service: ApiService? = null

    init {
        service = ApiClient.getRetrofitService()
    }


    override fun getSearchResult(
        request: HashMap<String, String>,
        callback: IDataSource.LoadDataCallback<ResultResponse>
    ) {

        val call = service?.getSearchResult(request)
        call?.enqueue(object : Callback<ResultResponse> {

            override fun onResponse(call: Call<ResultResponse>,
                                    response: Response<ResultResponse>) {
                Log.d("API Request url  ",  Gson().toJson(response.raw().request().url()))

                Log.d("API Request " , Gson().toJson(response
                    .body
                        ()))

                if (response.isSuccessful && response.body() != null) {
                    callback.onDataLoaded(response.body() as  ResultResponse)
                } else {
                    onFailure(call, Throwable("Error", IOException("Response Error")))
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                Log.d("API fail" , Gson().toJson(t.cause))
                callback.onDataNotAvailable(t)
            }
        })
    }

    override fun getWeatherResult(
        request: HashMap<String, String>,
        callback: IDataSource.LoadDataCallback<WeatherData>
    ) {
        val call = service?.getWeatherResult(request)
        call?.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>,
                                    response: Response<WeatherResponse>) {
                Log.d("API Request url  ",  Gson().toJson(response.raw().request().url()))

                Log.d("API Request " , Gson().toJson(response
                    .body
                        ()))

                if (response.isSuccessful && response.body() != null) {
                    callback.onDataLoaded(response.body() as  WeatherData)
                } else {
                    onFailure(call, Throwable("Error", IOException("Response Error")))
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("API fail" , Gson().toJson(t.cause))
                callback.onDataNotAvailable(t)
            }
        })
    }
}