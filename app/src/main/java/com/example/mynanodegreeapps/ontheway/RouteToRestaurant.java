package com.example.mynanodegreeapps.ontheway;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akhatri on 21/02/16.
 */
public class RouteToRestaurant implements Parcelable {

    private ArrayList<LatLng> routePoints;
    private HashMap<String,Restaurant> restaurants;

    public RouteToRestaurant(){
        routePoints = new ArrayList<LatLng>();
        restaurants = new HashMap<>();
    }

    protected RouteToRestaurant(Parcel in){
        restaurants =(HashMap<String,Restaurant>)in.readSerializable();
        routePoints = (ArrayList<LatLng>) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(restaurants);
        dest.writeSerializable(routePoints);
    }

    public static final Parcelable.Creator<RouteToRestaurant> CREATOR = new Parcelable.Creator<RouteToRestaurant>() {
        public RouteToRestaurant createFromParcel(Parcel in) {
            return new RouteToRestaurant(in);
        }

        public RouteToRestaurant[] newArray(int size) {
            return new RouteToRestaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public RouteToRestaurant(ArrayList<LatLng> routePoints,HashMap<String,Restaurant> restaurantPoints){
        this.routePoints = routePoints;
        this.restaurants = restaurantPoints;
    }
    public void addRoutePoint(LatLng point){
        routePoints.add(point);
    }
    public void addRestaurant(Restaurant resto){
        restaurants.put(resto.getId(),resto);
    }
    public void setRestaurant(ArrayList<Restaurant> restaurants){
        for (Restaurant resto: restaurants) {
            if(!this.restaurants.containsKey(resto.getId()))
                this.restaurants.put(resto.getId(), resto);
        }
    }

    public ArrayList<LatLng> getRoutePoints(){
        return routePoints;
    }
    public HashMap<String,Restaurant> getRestaurants(){
        return restaurants;
    }
}
