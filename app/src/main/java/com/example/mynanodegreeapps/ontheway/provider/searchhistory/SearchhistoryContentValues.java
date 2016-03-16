package com.example.mynanodegreeapps.ontheway.provider.searchhistory;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code searchhistory} table.
 */
public class SearchhistoryContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return SearchhistoryColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable SearchhistorySelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable SearchhistorySelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * source location
     */
    public SearchhistoryContentValues putSourceLocation(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("sourceLocation must not be null");
        mContentValues.put(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }


    /**
     * destination location
     */
    public SearchhistoryContentValues putDestinationLocation(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("destinationLocation must not be null");
        mContentValues.put(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }


    /**
     * source coordinates
     */
    public SearchhistoryContentValues putSourceCoordinates(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("sourceCoordinates must not be null");
        mContentValues.put(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }


    /**
     * destination coordinates
     */
    public SearchhistoryContentValues putDestinationCoordinates(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("destinationCoordinates must not be null");
        mContentValues.put(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

}
