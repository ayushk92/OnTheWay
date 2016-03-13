package com.example.mynanodegreeapps.ontheway.provider.cuisine;

import com.example.mynanodegreeapps.ontheway.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Zomato cuisine data.
 */
public interface CuisineModel extends BaseModel {

    /**
     * zomato cuisine name
     * Cannot be {@code null}.
     */
    @NonNull
    String getCuisineName();
}
