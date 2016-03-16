package com.example.mynanodegreeapps.ontheway.provider.searchhistory;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mynanodegreeapps.ontheway.provider.OnTheWayProvider;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.category.CategoryColumns;
import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.example.mynanodegreeapps.ontheway.provider.restaurant.RestaurantColumns;
import com.example.mynanodegreeapps.ontheway.provider.searchhistory.SearchhistoryColumns;

/**
 * Search history.
 */
public class SearchhistoryColumns implements BaseColumns {
    public static final String TABLE_NAME = "searchhistory";
    public static final Uri CONTENT_URI = Uri.parse(OnTheWayProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * source location
     */
    public static final String SOURCE_LOCATION = "source_location";

    /**
     * destination location
     */
    public static final String DESTINATION_LOCATION = "destination_location";

    /**
     * source coordinates
     */
    public static final String SOURCE_COORDINATES = "source_coordinates";

    /**
     * destination coordinates
     */
    public static final String DESTINATION_COORDINATES = "destination_coordinates";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            SOURCE_LOCATION,
            DESTINATION_LOCATION,
            SOURCE_COORDINATES,
            DESTINATION_COORDINATES
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(SOURCE_LOCATION) || c.contains("." + SOURCE_LOCATION)) return true;
            if (c.equals(DESTINATION_LOCATION) || c.contains("." + DESTINATION_LOCATION)) return true;
            if (c.equals(SOURCE_COORDINATES) || c.contains("." + SOURCE_COORDINATES)) return true;
            if (c.equals(DESTINATION_COORDINATES) || c.contains("." + DESTINATION_COORDINATES)) return true;
        }
        return false;
    }

}
