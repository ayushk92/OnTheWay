package com.example.mynanodegreeapps.ontheway.provider.restaurant;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mynanodegreeapps.ontheway.provider.OnTheWayProvider;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.category.CategoryColumns;
import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.example.mynanodegreeapps.ontheway.provider.restaurant.RestaurantColumns;

/**
 * Zomato restaurant data.
 */
public class RestaurantColumns implements BaseColumns {
    public static final String TABLE_NAME = "restaurant";
    public static final Uri CONTENT_URI = Uri.parse(OnTheWayProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * zomato restaurant name
     */
    public static final String RESTAURANT_NAME = "restaurant_name";

    /**
     * zomato pin code
     */
    public static final String PINCODE = "pincode";

    /**
     * restaurant address
     */
    public static final String ADDRESS = "address";

    /**
     * restaurant locality
     */
    public static final String LOCALITY = "locality";

    /**
     * restaurant city
     */
    public static final String CITY = "city";

    /**
     * restaurant latitude
     */
    public static final String LATITUDE = "latitude";

    /**
     * restaurant longitude
     */
    public static final String LONGITUDE = "longitude";

    /**
     * restaurant has online delivery
     */
    public static final String HASONLINEDELIVERY = "hasOnlineDelivery";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            RESTAURANT_NAME,
            PINCODE,
            ADDRESS,
            LOCALITY,
            CITY,
            LATITUDE,
            LONGITUDE,
            HASONLINEDELIVERY
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(RESTAURANT_NAME) || c.contains("." + RESTAURANT_NAME)) return true;
            if (c.equals(PINCODE) || c.contains("." + PINCODE)) return true;
            if (c.equals(ADDRESS) || c.contains("." + ADDRESS)) return true;
            if (c.equals(LOCALITY) || c.contains("." + LOCALITY)) return true;
            if (c.equals(CITY) || c.contains("." + CITY)) return true;
            if (c.equals(LATITUDE) || c.contains("." + LATITUDE)) return true;
            if (c.equals(LONGITUDE) || c.contains("." + LONGITUDE)) return true;
            if (c.equals(HASONLINEDELIVERY) || c.contains("." + HASONLINEDELIVERY)) return true;
        }
        return false;
    }

}
