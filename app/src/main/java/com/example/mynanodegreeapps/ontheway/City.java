package com.example.mynanodegreeapps.ontheway;

/**
 * Created by akhatri on 05/03/16.
 */
public class City {

    public City(int cityID,String cityName){
        this.cityID = cityID;
        this.cityName = cityName;
    }

    private int cityID;
    private String cityName;

    public int getCityID(){
        return cityID;
    }

    public String getCityName(){
        return cityName;
    }

}
