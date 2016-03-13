package com.example.mynanodegreeapps.ontheway.provider.cuisine;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractSelection;

/**
 * Selection for the {@code cuisine} table.
 */
public class CuisineSelection extends AbstractSelection<CuisineSelection> {
    @Override
    protected Uri baseUri() {
        return CuisineColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CuisineCursor} object, which is positioned before the first entry, or null.
     */
    public CuisineCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CuisineCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CuisineCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CuisineCursor} object, which is positioned before the first entry, or null.
     */
    public CuisineCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CuisineCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CuisineCursor query(Context context) {
        return query(context, null);
    }


    public CuisineSelection id(long... value) {
        addEquals("cuisine." + CuisineColumns._ID, toObjectArray(value));
        return this;
    }

    public CuisineSelection idNot(long... value) {
        addNotEquals("cuisine." + CuisineColumns._ID, toObjectArray(value));
        return this;
    }

    public CuisineSelection orderById(boolean desc) {
        orderBy("cuisine." + CuisineColumns._ID, desc);
        return this;
    }

    public CuisineSelection orderById() {
        return orderById(false);
    }

    public CuisineSelection cuisineName(String... value) {
        addEquals(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

    public CuisineSelection cuisineNameNot(String... value) {
        addNotEquals(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

    public CuisineSelection cuisineNameLike(String... value) {
        addLike(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

    public CuisineSelection cuisineNameContains(String... value) {
        addContains(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

    public CuisineSelection cuisineNameStartsWith(String... value) {
        addStartsWith(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

    public CuisineSelection cuisineNameEndsWith(String... value) {
        addEndsWith(CuisineColumns.CUISINE_NAME, value);
        return this;
    }

    public CuisineSelection orderByCuisineName(boolean desc) {
        orderBy(CuisineColumns.CUISINE_NAME, desc);
        return this;
    }

    public CuisineSelection orderByCuisineName() {
        orderBy(CuisineColumns.CUISINE_NAME, false);
        return this;
    }
}
