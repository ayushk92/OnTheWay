package com.example.mynanodegreeapps.ontheway.provider.searchhistory;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code searchhistory} table.
 */
public class SearchhistoryCursor extends AbstractCursor implements SearchhistoryModel {
    public SearchhistoryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(SearchhistoryColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * source location
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSourceLocation() {
        String res = getStringOrNull(SearchhistoryColumns.SOURCE_LOCATION);
        if (res == null)
            throw new NullPointerException("The value of 'source_location' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * destination location
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDestinationLocation() {
        String res = getStringOrNull(SearchhistoryColumns.DESTINATION_LOCATION);
        if (res == null)
            throw new NullPointerException("The value of 'destination_location' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * source coordinates
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSourceCoordinates() {
        String res = getStringOrNull(SearchhistoryColumns.SOURCE_COORDINATES);
        if (res == null)
            throw new NullPointerException("The value of 'source_coordinates' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * destination coordinates
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDestinationCoordinates() {
        String res = getStringOrNull(SearchhistoryColumns.DESTINATION_COORDINATES);
        if (res == null)
            throw new NullPointerException("The value of 'destination_coordinates' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
