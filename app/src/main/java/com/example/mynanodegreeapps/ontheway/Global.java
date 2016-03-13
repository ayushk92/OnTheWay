package com.example.mynanodegreeapps.ontheway;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by akhatri on 03/01/16.
 */
public class Global {

    private Global(){

    }
    private static final String BASE_DIRECTIONS_URI = "https://maps.googleapis.com/maps/api/directions/json";
    private static final String from_PARAM = "origin";
    private static final String to_PARAM = "destination";
    private static final String KEY_PARAM = "key";
    private static final String CITY_PARAM = "q";
    private static final String CITY_COUNT_PARAM = "count";
    private static final String CITY_ID_PARAM = "city_id";

    private static final String BASE_ZOMATO_URI = "https://developers.zomato.com/api/v2.1/";
    private static final String ZOMATO_SEARCH_URI = "search";
    private static final String ZOMATO_CITY_URI = "cities";
    private static final String ZOMATO_CUISINE_URI  = "cuisines";
    private static final String LAT_PARAM = "lat";
    private static final String LNG_PARAM = "lon";
    private static final String ENTITY_TYPE_PARAM = "entity_type";
    private static final String RADIUS_PARAM = "radius";

    public static URL getDirectionsUrl(Context context,String fromLocation,String toLocation){
        try {
            Uri getDirectionsURI = Uri.parse(BASE_DIRECTIONS_URI).buildUpon()
                    .appendQueryParameter(from_PARAM, fromLocation)
                    .appendQueryParameter(to_PARAM, toLocation)
                    .appendQueryParameter(KEY_PARAM, context.getString(R.string.google_maps_key))
                    .build();
            return new URL(getDirectionsURI.toString());
        }
        catch (MalformedURLException ex){
            Log.e("getDirectionsUrl",ex.getMessage());
        }
        return  null;
    }

    public static URL getCityUrl(double lat,double lng){
        try {
            Uri getCityURI = Uri.parse(BASE_ZOMATO_URI + ZOMATO_CITY_URI).buildUpon()
                    .appendQueryParameter(LAT_PARAM, Double.toString(lat))
                    .appendQueryParameter(LNG_PARAM, Double.toString(lng))
                    .appendQueryParameter(CITY_COUNT_PARAM, "1")
                    .build();
            return new URL(getCityURI.toString());
        }
        catch (MalformedURLException ex){
            Log.e("getCityUrl",ex.getMessage());
        }
        return  null;
    }

    public static URL getCuisineUrl(int cityID){
        try {
            Uri getCuisineURI = Uri.parse(BASE_ZOMATO_URI + ZOMATO_CUISINE_URI).buildUpon()
                    .appendQueryParameter(CITY_ID_PARAM, Integer.toString(cityID))
                    .build();
            return new URL(getCuisineURI.toString());
        }
        catch (MalformedURLException ex){
            Log.e("getCuisineUrl",ex.getMessage());
        }
        return  null;
    }


    public static URL getRestaurantUrl(double lat,double lng){
        try {
            Uri getDirectionsURI = Uri.parse(BASE_ZOMATO_URI + ZOMATO_SEARCH_URI).buildUpon()
                    .appendQueryParameter(ENTITY_TYPE_PARAM,"city")
                    .appendQueryParameter(LAT_PARAM, Double.toString(lat))
                    .appendQueryParameter(LNG_PARAM, Double.toString(lng))
                    .appendQueryParameter(RADIUS_PARAM,"1000")
                    .build();
            return new URL(getDirectionsURI.toString());
        }
        catch (MalformedURLException ex){
            Log.e("getDirectionsUrl",ex.getMessage());
        }
        return  null;
    }

    public static String getJSONDataFromURL(URL url,String userKey){
        HttpURLConnection urlConnection = null;
        String jsonDAta;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            if(userKey != null)
                urlConnection.setRequestProperty("user-key",userKey);
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                // Nothing to do.
                jsonDAta = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
        }
        catch (Exception ex){
            Log.e("MapsActivity", ex.getMessage());
        }
        finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return  buffer.toString();

    }

    public static double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d*1000;
    }

    public static double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }
}
