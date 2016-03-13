package com.example.mynanodegreeapps.ontheway.provider.restaurant;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code restaurant} table.
 */
public class RestaurantContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return RestaurantColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable RestaurantSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable RestaurantSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * zomato restaurant name
     */
    public RestaurantContentValues putRestaurantName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("restaurantName must not be null");
        mContentValues.put(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }


    /**
     * zomato pin code
     */
    public RestaurantContentValues putPincode(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("pincode must not be null");
        mContentValues.put(RestaurantColumns.PINCODE, value);
        return this;
    }


    /**
     * restaurant address
     */
    public RestaurantContentValues putAddress(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("address must not be null");
        mContentValues.put(RestaurantColumns.ADDRESS, value);
        return this;
    }


    /**
     * restaurant locality
     */
    public RestaurantContentValues putLocality(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("locality must not be null");
        mContentValues.put(RestaurantColumns.LOCALITY, value);
        return this;
    }


    /**
     * restaurant city
     */
    public RestaurantContentValues putCity(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("city must not be null");
        mContentValues.put(RestaurantColumns.CITY, value);
        return this;
    }


    /**
     * restaurant latitude
     */
    public RestaurantContentValues putLatitude(double value) {
        mContentValues.put(RestaurantColumns.LATITUDE, value);
        return this;
    }


    /**
     * restaurant longitude
     */
    public RestaurantContentValues putLongitude(double value) {
        mContentValues.put(RestaurantColumns.LONGITUDE, value);
        return this;
    }


    /**
     * restaurant has online delivery
     */
    public RestaurantContentValues putHasonlinedelivery(boolean value) {
        mContentValues.put(RestaurantColumns.HASONLINEDELIVERY, value);
        return this;
    }

}
