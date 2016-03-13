package com.example.mynanodegreeapps.ontheway;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingModel;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

/**
 * Created by akhatri on 05/03/16.
 */
public class FetchReferenceDataIntentService extends IntentService {

    public FetchReferenceDataIntentService(){
        super("FetchReferenceDataIntentService");
    }

    private final String TAG = FetchReferenceDataIntentService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra(
                Constants.LOCATION_DATA_EXTRA);

        if(RestaurantFilters.getInstance().getCurrentCityId() == null)
            addCityReferenceData(location.getLatitude(), location.getLongitude());
        //

    }

    void addCityReferenceData(double lat,double lng){

        try {
            URL cityURL = Global.getCityUrl(lat, lng);

            City city = ZomatoJSONParser.parseCityJSON(new JSONObject(Global.getJSONDataFromURL(cityURL, getString(R.string.zomato_api_key))));

            if(city != null) {
                Cursor cityCursor = getBaseContext().getContentResolver().query(
                        CityColumns.CONTENT_URI,
                        new String[]{CityColumns._ID},
                        CityColumns._ID + " = ?",
                        new String[]{String.valueOf(city.getCityID())},
                        null);
                if (!cityCursor.moveToFirst()) {
                    // Now that the content provider is set up, inserting rows of data is pretty simple.
                    // First create a ContentValues object to hold the data you want to insert.
                    ContentValues cityValues = new ContentValues();

                    // Then add the data, along with the corresponding name of the data type,
                    // so the content provider knows what kind of value is being inserted.
                    cityValues.put(CityColumns._ID, city.getCityID());
                    cityValues.put(CityColumns.NAME, city.getCityName());

                    // Finally, insert location data into the database.
                    Uri insertedUri = getContentResolver().insert(
                            CityColumns.CONTENT_URI,
                            cityValues
                    );

                    addCusineReferenceData(city.getCityID());

                }
                RestaurantFilters.getInstance().setCurrentCityId(city.getCityID());
                cityCursor.close();

                return;

            }
        }
        catch (JSONException ex){
            Log.e(TAG,ex.getMessage());
        }
    }

    void addCusineReferenceData(int cityId){
        try {
            URL cuisineURL = Global.getCuisineUrl(cityId);

            ArrayList<Cuisine> cuisines = ZomatoJSONParser.parseCuisineJSON(new JSONObject(Global.getJSONDataFromURL(cuisineURL, getString(R.string.zomato_api_key))));

            Vector<ContentValues> cVVector = new Vector<ContentValues>(cuisines.size());
            Vector<ContentValues> cVVector1 = new Vector<ContentValues>(cuisines.size());

            for(int i = 0; i < cuisines.size(); i++){
                ContentValues contentValues = new ContentValues();

                contentValues.put(CuisineColumns._ID, cuisines.get(i).getCuisineID());
                contentValues.put(CuisineColumns.CUISINE_NAME, cuisines.get(i).getCuisineName());

                cVVector.add(contentValues);

                ContentValues cityToCuisineValue = new ContentValues();

                cityToCuisineValue.put(CitytocuisinemappingColumns.CITY_ID,cityId);
                cityToCuisineValue.put(CitytocuisinemappingColumns.CUISINE_ID,cuisines.get(i).getCuisineID());

                cVVector1.add(cityToCuisineValue);
            }
            if ( cVVector.size() > 0 ) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);
                getContentResolver().bulkInsert(CuisineColumns.CONTENT_URI, cvArray);
            }

            if ( cVVector1.size() > 0 ) {
                ContentValues[] cvArray = new ContentValues[cVVector1.size()];
                cVVector1.toArray(cvArray);
                getContentResolver().bulkInsert(CitytocuisinemappingColumns.CONTENT_URI, cvArray);
            }



        }
        catch (JSONException ex){
            Log.e(TAG,ex.getMessage());
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    public FetchReferenceDataIntentService(String name) {
        super(name);
    }
}

