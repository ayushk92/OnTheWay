package com.example.mynanodegreeapps.ontheway.provider.category;

import com.example.mynanodegreeapps.ontheway.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Categoris of restaurant.
 */
public interface CategoryModel extends BaseModel {

    /**
     * category name eg : dineout, dinner, nightlife etc
     * Cannot be {@code null}.
     */
    @NonNull
    String getCategoryName();
}
