package com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code citytocuisinemapping} table.
 */
public class CitytocuisinemappingCursor extends AbstractCursor implements CitytocuisinemappingModel {
    public CitytocuisinemappingCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CitytocuisinemappingColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * zomato cuisine id
     */
    public int getCuisineId() {
        Integer res = getIntegerOrNull(CitytocuisinemappingColumns.CUISINE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'cuisine_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * zomato city id
     */
    public int getCityId() {
        Integer res = getIntegerOrNull(CitytocuisinemappingColumns.CITY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'city_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
