package com.example.mynanodegreeapps.ontheway;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akhatri on 21/02/16.
 */
public class ZomatoJSONParser {

    private static final String LOG_TAG = ZomatoJSONParser.class.getSimpleName();
    private static final String RESTAURANT_KEY = "restaurant";
    private static final String RESTAURANTS_KEY = "restaurants";
    private static final String LOCATION_KEY = "location";
    private static final String LATITUTE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    private static final String NAME_KEY = "name";
    private static final String ID_KEY = "id";
    private static final String CUISINES_KEY = "cuisines";
    private static final String CUISINE_KEY = "cuisine";
    private static final String COST_FOR_TWO_CATEGORY_KEY = "price_range";
    private static final String HAS_ONLINE_DELIVERY = "has_online_delivery";

    private static final String CITY_SUGGESTION = "location_suggestions";
    private static final String CITY_ID = "id";
    private static final String CITY_NAME = "name";
    private static final String CUISINE_ID = "cuisine_id";
    private static final String CUISINE_NAME = "cuisine_name";

    public static ArrayList<Restaurant> parseRestaurantJSON(JSONObject jsonObject){
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        try {
            JSONArray jsonArrayRestaurants = jsonObject.getJSONArray(RESTAURANTS_KEY);

            for(int i = 0; i < jsonArrayRestaurants.length(); i++){
                JSONObject jsonObject1 = (JSONObject)((JSONObject) jsonArrayRestaurants.get(i)).get(RESTAURANT_KEY);
                double lat = Double.parseDouble(((JSONObject) jsonObject1.get(LOCATION_KEY)).get(LATITUTE_KEY).toString());
                double lng = Double.parseDouble(((JSONObject) jsonObject1.get(LOCATION_KEY)).get(LONGITUDE_KEY).toString());
                String name = (jsonObject1.get(NAME_KEY)).toString();
                String Id = (jsonObject1.get(ID_KEY)).toString();
                LatLng position = new LatLng(lat, lng);

                ArrayList<String> cuisines = new ArrayList<String>();
                for(String cuisine : jsonObject1.getString(CUISINES_KEY).toString().split(",")){
                    cuisines.add(cuisine);
                }
                int costForTwoCategory = Integer.parseInt(jsonObject1.get(COST_FOR_TWO_CATEGORY_KEY).toString());

                boolean hasOnlineDelivery = "1".equals(jsonObject1.getString(HAS_ONLINE_DELIVERY));

                restaurants.add(new Restaurant(Id,name,position,costForTwoCategory,cuisines,hasOnlineDelivery));
            }
        }
        catch (JSONException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
        return restaurants;
    }
    public static City parseCityJSON(JSONObject jsonObject){
        try {
            JSONArray jsonArrayCity = jsonObject.getJSONArray(CITY_SUGGESTION);

            JSONObject jsonObject1 = ((JSONObject) jsonArrayCity.get(0));
            int cityId = Integer.parseInt(jsonObject1.get(CITY_ID).toString());
            String cityName = jsonObject1.get(CITY_NAME).toString();
            return  new City(cityId,cityName);

        }
        catch (JSONException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
        return null;
    }

    public static ArrayList<Cuisine> parseCuisineJSON(JSONObject jsonObject){
        ArrayList<Cuisine> cuisines = new ArrayList<Cuisine>();
        try {
            JSONArray jsonArrayCuisines = jsonObject.getJSONArray(CUISINES_KEY);

            for(int i = 0; i < jsonArrayCuisines.length(); i++){
                JSONObject jsonObject1 = (JSONObject)((JSONObject) jsonArrayCuisines.get(i)).get(CUISINE_KEY);
                int cuisineID = Integer.parseInt(jsonObject1.get(CUISINE_ID).toString());
                String cuisineName = jsonObject1.getString(CUISINE_NAME).toString();


                cuisines.add(new Cuisine(cuisineID,cuisineName));
            }
        }
        catch (JSONException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
        return cuisines;
    }
}
