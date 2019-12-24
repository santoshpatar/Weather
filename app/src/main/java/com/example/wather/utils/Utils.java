package com.example.wather.utils;

import com.example.wather.data.source.remote.model.City;
import com.example.wather.data.source.remote.model.ResultResponse;

import java.util.ArrayList;

public class Utils {

    public static City getCityData1(ResultResponse resultResponse){

       ArrayList <City> cityList = new  ArrayList<City>();
        City cityResponse =  new City();
        cityResponse.setAreaName(resultResponse.getSearch_api().getResult().get(0).getAreaName().get(0).getValue());
                return cityResponse;

    }



}
