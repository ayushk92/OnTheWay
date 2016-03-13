package com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code citytocuisinemapping} table.
 */
public class CitytocuisinemappingContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CitytocuisinemappingColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CitytocuisinemappingSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CitytocuisinemappingSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * zomato cuisine id
     */
    public CitytocuisinemappingContentValues putCuisineId(int value) {
        mContentValues.put(CitytocuisinemappingColumns.CUISINE_ID, value);
        return this;
    }


    /**
     * zomato city id
     */
    public CitytocuisinemappingContentValues putCityId(int value) {
        mContentValues.put(CitytocuisinemappingColumns.CITY_ID, value);
        return this;
    }

}
