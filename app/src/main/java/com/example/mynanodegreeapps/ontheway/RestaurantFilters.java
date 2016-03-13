package com.example.mynanodegreeapps.ontheway;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by akhatri on 06/03/16.
 */
public class RestaurantFilters {
    private static RestaurantFilters instance = null;
    private RestaurantFilters() {
        // Exists only to defeat instantiation.
        cuisineIDs = new ArrayList<Integer>();
        selectedCuisines = new ArrayList<String>();
        currentCityId = null;
        selectedCostForTwo = null;
        hasOnlineDelivery = null;

    }
    public static RestaurantFilters getInstance() {
        if(instance == null) {
            instance = new RestaurantFilters();
        }
        return instance;
    }

    private ArrayList<RouteToRestaurant> currentRouteToRestaurants;

    @Nullable
    private Integer currentCityId;

    private ArrayList<Integer> cuisineIDs;

    private ArrayList<String> selectedCuisines;

    private Integer selectedCostForTwo;

    public Integer getCurrentCityId(){
        return currentCityId;
    }

    public Boolean hasOnlineDelivery;

    public void setCurrentCityId(int currentCityId){
        this.currentCityId = currentCityId;
    }

    public void setSelectedCuisines(ArrayList<String> selectedCuisines){
        this.selectedCuisines = selectedCuisines;
    }

    public void setSelectedCostForTwo(Integer selectedCostForTwo){
        this.selectedCostForTwo = selectedCostForTwo;
    }

    public void setHasOnlineDelivery(Boolean hasOnlineDelivery){
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public void setCurrentRouteToRestaurants(ArrayList<RouteToRestaurant> currentRouteToRestaurants){
        this.currentRouteToRestaurants = currentRouteToRestaurants;
    }

    public ArrayList<RouteToRestaurant> getCurrentRouteToRestaurants(){
        return currentRouteToRestaurants;
    }

    public Boolean getHasOnlineDelivery(){
        return hasOnlineDelivery;
    }

    public Integer getSelectedCostForTwo(){
        return selectedCostForTwo;
    }

    public ArrayList<String> getSelectedCuisines(){
        return selectedCuisines;
    }

    public ArrayList<Integer> getCuisineIDs(){
        return cuisineIDs;
    }

    public void setCuisineIDs(ArrayList<Integer> cuisineIDs){
        this.cuisineIDs = cuisineIDs;
    }
}
