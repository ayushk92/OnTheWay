package com.example.mynanodegreeapps.ontheway.provider.city;

import com.example.mynanodegreeapps.ontheway.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Zomato city data.
 */
public interface CityModel extends BaseModel {

    /**
     * city name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();
}
