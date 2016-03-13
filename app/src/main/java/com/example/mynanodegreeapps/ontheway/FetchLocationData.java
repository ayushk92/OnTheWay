package com.example.mynanodegreeapps.ontheway;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by akhatri on 20/02/16.
 */
public class FetchLocationData extends AsyncTaskLoader<ArrayList<RouteToRestaurant>> {

    private final String LOG_TAG = FetchLocationData.class.getSimpleName();

    private String mfromLocation;
    private String mtoLocation;
    private Context mContext;

    private final double DEFAULT_RADIUS = 500;

    public FetchLocationData(Context context,String fromLocation,String toLocation){
        super(context);
        mContext = context;
        mfromLocation = fromLocation;
        mtoLocation = toLocation;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<RouteToRestaurant> loadInBackground() {

        ArrayList<RouteToRestaurant> routesToRestaurants = new ArrayList<RouteToRestaurant>();
        String jsonDataFromURL = "";
        JSONObject jsonObject = new JSONObject();
        try{

            URL url = Global.getDirectionsUrl(mContext,mfromLocation,mtoLocation);
            jsonDataFromURL = Global.getJSONDataFromURL(url,null);
            jsonObject = new JSONObject(jsonDataFromURL);
        List<List<HashMap<String,String>>> routeData =  DirectionsJSONParser.parse(jsonObject);
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;

        // Traversing through all the routes
        for(int i=0;i<routeData.size();i++){
            points = new ArrayList<LatLng>();
            RouteToRestaurant routeToRestaurant = new RouteToRestaurant();

            // Fetching i-th route
            List<HashMap<String, String>> path = routeData.get(i);

            // Fetching all the points in i-th route
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                routeToRestaurant.addRoutePoint(position);
            }
            routesToRestaurants.add(routeToRestaurant);
        }

        LatLng prevLatlng = null;
        ArrayList<Restaurant> tempRestaurants;
        int count = 0;
        for (RouteToRestaurant rtr : routesToRestaurants){
            for (LatLng ll : rtr.getRoutePoints()){
                double previousDistance = 0;
                if(prevLatlng != null)
                    previousDistance = Global.getDistanceFromLatLonInKm(ll.latitude,ll.longitude,prevLatlng.latitude,prevLatlng.longitude);

                if((prevLatlng == null) || previousDistance  > DEFAULT_RADIUS) {
                    URL restaurantURL = Global.getRestaurantUrl(ll.latitude, ll.longitude);
                    jsonDataFromURL = Global.getJSONDataFromURL(restaurantURL, mContext.getString(R.string.zomato_api_key));
                    count++;
                    if(jsonDataFromURL != "") {
                        jsonObject = new JSONObject(jsonDataFromURL);
                        tempRestaurants = ZomatoJSONParser.parseRestaurantJSON(jsonObject);
                        if(tempRestaurants != null && tempRestaurants.size() > 0) {
                            rtr.setRestaurant(tempRestaurants);
                            prevLatlng = ll;
                        }
                    }

                }

            }
        }
        Log.d(LOG_TAG,"Zomato API pinged for " + count + " times.");
        }
        catch (JSONException ex){
            Log.e(LOG_TAG, ex.getMessage());
        }
        return routesToRestaurants;
    }
}
