package com.example.mynanodegreeapps.ontheway.provider.category;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code category} table.
 */
public class CategoryCursor extends AbstractCursor implements CategoryModel {
    public CategoryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CategoryColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * category name eg : dineout, dinner, nightlife etc
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCategoryName() {
        String res = getStringOrNull(CategoryColumns.CATEGORY_NAME);
        if (res == null)
            throw new NullPointerException("The value of 'category_name' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
