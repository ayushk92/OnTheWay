package com.example.mynanodegreeapps.ontheway.provider.cuisine;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mynanodegreeapps.ontheway.provider.OnTheWayProvider;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.category.CategoryColumns;
import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.example.mynanodegreeapps.ontheway.provider.restaurant.RestaurantColumns;

/**
 * Zomato cuisine data.
 */
public class CuisineColumns implements BaseColumns {
    public static final String TABLE_NAME = "cuisine";
    public static final Uri CONTENT_URI = Uri.parse(OnTheWayProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * zomato cuisine name
     */
    public static final String CUISINE_NAME = "cuisine_name";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            CUISINE_NAME
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(CUISINE_NAME) || c.contains("." + CUISINE_NAME)) return true;
        }
        return false;
    }

}
