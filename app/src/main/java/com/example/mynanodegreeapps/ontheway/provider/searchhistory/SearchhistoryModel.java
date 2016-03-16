package com.example.mynanodegreeapps.ontheway.provider.searchhistory;

import com.example.mynanodegreeapps.ontheway.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Search history.
 */
public interface SearchhistoryModel extends BaseModel {

    /**
     * source location
     * Cannot be {@code null}.
     */
    @NonNull
    String getSourceLocation();

    /**
     * destination location
     * Cannot be {@code null}.
     */
    @NonNull
    String getDestinationLocation();

    /**
     * source coordinates
     * Cannot be {@code null}.
     */
    @NonNull
    String getSourceCoordinates();

    /**
     * destination coordinates
     * Cannot be {@code null}.
     */
    @NonNull
    String getDestinationCoordinates();
}
