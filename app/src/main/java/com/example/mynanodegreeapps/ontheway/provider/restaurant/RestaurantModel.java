package com.example.mynanodegreeapps.ontheway.provider.restaurant;

import com.example.mynanodegreeapps.ontheway.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Zomato restaurant data.
 */
public interface RestaurantModel extends BaseModel {

    /**
     * zomato restaurant name
     * Cannot be {@code null}.
     */
    @NonNull
    String getRestaurantName();

    /**
     * zomato pin code
     * Cannot be {@code null}.
     */
    @NonNull
    String getPincode();

    /**
     * restaurant address
     * Cannot be {@code null}.
     */
    @NonNull
    String getAddress();

    /**
     * restaurant locality
     * Cannot be {@code null}.
     */
    @NonNull
    String getLocality();

    /**
     * restaurant city
     * Cannot be {@code null}.
     */
    @NonNull
    String getCity();

    /**
     * restaurant latitude
     */
    double getLatitude();

    /**
     * restaurant longitude
     */
    double getLongitude();

    /**
     * restaurant has online delivery
     */
    boolean getHasonlinedelivery();
}
