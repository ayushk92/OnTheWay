package com.example.mynanodegreeapps.ontheway.provider.cuisine;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code cuisine} table.
 */
public class CuisineContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CuisineColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CuisineSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CuisineSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * zomato cuisine name
     */
    public CuisineContentValues putCuisineName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("cuisineName must not be null");
        mContentValues.put(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

}
