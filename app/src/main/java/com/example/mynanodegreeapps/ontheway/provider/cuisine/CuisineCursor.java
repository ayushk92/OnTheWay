package com.example.mynanodegreeapps.ontheway.provider.cuisine;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code cuisine} table.
 */
public class CuisineCursor extends AbstractCursor implements CuisineModel {
    public CuisineCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CuisineColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * zomato cuisine name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCuisineName() {
        String res = getStringOrNull(CuisineColumns.CUISINE_NAME);
        if (res == null)
            throw new NullPointerException("The value of 'cuisine_name' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
