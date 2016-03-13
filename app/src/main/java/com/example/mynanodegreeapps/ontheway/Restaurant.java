package com.example.mynanodegreeapps.ontheway;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by akhatri on 21/02/16.
 */
public class Restaurant {

    private String Id;
    private String name;
    private LatLng location;
    private ArrayList<String> cuisines;
    private int costForTwoCategory;
    private boolean hasOnlineDelivery;

    public Restaurant(){

    }
    public Restaurant(String Id,String name,LatLng location,int costForTwoCategory,ArrayList<String> cuisines,boolean hasOnlineDelivery){
        this.Id = Id;
        this.name = name;
        this.location = location;
        this.costForTwoCategory = costForTwoCategory;
        this.cuisines = cuisines;
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public String getName(){
        return  name;
    }
    public LatLng getLocation(){
        return  location;
    }
    public String getId(){
        return Id;
    }
    public int getCostForTwoCategory(){ return  costForTwoCategory; }
    public boolean isHasOnlineDelivery(){ return  hasOnlineDelivery;}

    public ArrayList<String> getCuisines(){
        return cuisines;
    }
}
