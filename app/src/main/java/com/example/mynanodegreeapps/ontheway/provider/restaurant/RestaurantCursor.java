package com.example.mynanodegreeapps.ontheway.provider.restaurant;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code restaurant} table.
 */
public class RestaurantCursor extends AbstractCursor implements RestaurantModel {
    public RestaurantCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(RestaurantColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * zomato restaurant name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getRestaurantName() {
        String res = getStringOrNull(RestaurantColumns.RESTAURANT_NAME);
        if (res == null)
            throw new NullPointerException("The value of 'restaurant_name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * zomato pin code
     * Cannot be {@code null}.
     */
    @NonNull
    public String getPincode() {
        String res = getStringOrNull(RestaurantColumns.PINCODE);
        if (res == null)
            throw new NullPointerException("The value of 'pincode' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * restaurant address
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAddress() {
        String res = getStringOrNull(RestaurantColumns.ADDRESS);
        if (res == null)
            throw new NullPointerException("The value of 'address' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * restaurant locality
     * Cannot be {@code null}.
     */
    @NonNull
    public String getLocality() {
        String res = getStringOrNull(RestaurantColumns.LOCALITY);
        if (res == null)
            throw new NullPointerException("The value of 'locality' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * restaurant city
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCity() {
        String res = getStringOrNull(RestaurantColumns.CITY);
        if (res == null)
            throw new NullPointerException("The value of 'city' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * restaurant latitude
     */
    public double getLatitude() {
        Double res = getDoubleOrNull(RestaurantColumns.LATITUDE);
        if (res == null)
            throw new NullPointerException("The value of 'latitude' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * restaurant longitude
     */
    public double getLongitude() {
        Double res = getDoubleOrNull(RestaurantColumns.LONGITUDE);
        if (res == null)
            throw new NullPointerException("The value of 'longitude' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * restaurant has online delivery
     */
    public boolean getHasonlinedelivery() {
        Boolean res = getBooleanOrNull(RestaurantColumns.HASONLINEDELIVERY);
        if (res == null)
            throw new NullPointerException("The value of 'hasonlinedelivery' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
