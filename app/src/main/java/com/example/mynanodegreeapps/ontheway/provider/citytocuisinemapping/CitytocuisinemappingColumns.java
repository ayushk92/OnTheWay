package com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mynanodegreeapps.ontheway.provider.OnTheWayProvider;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.category.CategoryColumns;
import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.example.mynanodegreeapps.ontheway.provider.restaurant.RestaurantColumns;

/**
 * Zomato city to cuisine mapping.
 */
public class CitytocuisinemappingColumns implements BaseColumns {
    public static final String TABLE_NAME = "citytocuisinemapping";
    public static final Uri CONTENT_URI = Uri.parse(OnTheWayProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * zomato cuisine id
     */
    public static final String CUISINE_ID = "cuisine_id";

    /**
     * zomato city id
     */
    public static final String CITY_ID = "city_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CUISINE_ID,
            CITY_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CUISINE_ID) || c.contains("." + CUISINE_ID)) return true;
            if (c.equals(CITY_ID) || c.contains("." + CITY_ID)) return true;
        }
        return false;
    }

    public static Uri getCuisinesOfCityUri(){
        return CONTENT_URI.buildUpon().appendPath("city").build();
    }

}
