package com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping;

import com.example.mynanodegreeapps.ontheway.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Zomato city to cuisine mapping.
 */
public interface CitytocuisinemappingModel extends BaseModel {

    /**
     * zomato cuisine id
     */
    int getCuisineId();

    /**
     * zomato city id
     */
    int getCityId();
}
