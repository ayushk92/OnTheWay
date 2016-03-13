package com.example.mynanodegreeapps.ontheway;

/**
 * Created by akhatri on 05/03/16.
 */
public class Cuisine {

    private int cuisineID;
    private String cuisineName;

    public Cuisine(int cuisineID,String cuisineName){
        this.cuisineID = cuisineID;
        this.cuisineName = cuisineName;
    }

    public int getCuisineID(){
        return cuisineID;
    }

    public String getCuisineName(){
        return cuisineName;
    }
}
