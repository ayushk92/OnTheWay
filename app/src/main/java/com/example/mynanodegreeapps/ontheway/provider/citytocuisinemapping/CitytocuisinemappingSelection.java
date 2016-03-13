package com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractSelection;

/**
 * Selection for the {@code citytocuisinemapping} table.
 */
public class CitytocuisinemappingSelection extends AbstractSelection<CitytocuisinemappingSelection> {
    @Override
    protected Uri baseUri() {
        return CitytocuisinemappingColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CitytocuisinemappingCursor} object, which is positioned before the first entry, or null.
     */
    public CitytocuisinemappingCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CitytocuisinemappingCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CitytocuisinemappingCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CitytocuisinemappingCursor} object, which is positioned before the first entry, or null.
     */
    public CitytocuisinemappingCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CitytocuisinemappingCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CitytocuisinemappingCursor query(Context context) {
        return query(context, null);
    }


    public CitytocuisinemappingSelection id(long... value) {
        addEquals("citytocuisinemapping." + CitytocuisinemappingColumns._ID, toObjectArray(value));
        return this;
    }

    public CitytocuisinemappingSelection idNot(long... value) {
        addNotEquals("citytocuisinemapping." + CitytocuisinemappingColumns._ID, toObjectArray(value));
        return this;
    }

    public CitytocuisinemappingSelection orderById(boolean desc) {
        orderBy("citytocuisinemapping." + CitytocuisinemappingColumns._ID, desc);
        return this;
    }

    public CitytocuisinemappingSelection orderById() {
        return orderById(false);
    }

    public CitytocuisinemappingSelection cuisineId(int... value) {
        addEquals(CitytocuisinemappingColumns.CUISINE_ID, toObjectArray(value));
        return this;
    }

    public CitytocuisinemappingSelection cuisineIdNot(int... value) {
        addNotEquals(CitytocuisinemappingColumns.CUISINE_ID, toObjectArray(value));
        return this;
    }

    public CitytocuisinemappingSelection cuisineIdGt(int value) {
        addGreaterThan(CitytocuisinemappingColumns.CUISINE_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection cuisineIdGtEq(int value) {
        addGreaterThanOrEquals(CitytocuisinemappingColumns.CUISINE_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection cuisineIdLt(int value) {
        addLessThan(CitytocuisinemappingColumns.CUISINE_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection cuisineIdLtEq(int value) {
        addLessThanOrEquals(CitytocuisinemappingColumns.CUISINE_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection orderByCuisineId(boolean desc) {
        orderBy(CitytocuisinemappingColumns.CUISINE_ID, desc);
        return this;
    }

    public CitytocuisinemappingSelection orderByCuisineId() {
        orderBy(CitytocuisinemappingColumns.CUISINE_ID, false);
        return this;
    }

    public CitytocuisinemappingSelection cityId(int... value) {
        addEquals(CitytocuisinemappingColumns.CITY_ID, toObjectArray(value));
        return this;
    }

    public CitytocuisinemappingSelection cityIdNot(int... value) {
        addNotEquals(CitytocuisinemappingColumns.CITY_ID, toObjectArray(value));
        return this;
    }

    public CitytocuisinemappingSelection cityIdGt(int value) {
        addGreaterThan(CitytocuisinemappingColumns.CITY_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection cityIdGtEq(int value) {
        addGreaterThanOrEquals(CitytocuisinemappingColumns.CITY_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection cityIdLt(int value) {
        addLessThan(CitytocuisinemappingColumns.CITY_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection cityIdLtEq(int value) {
        addLessThanOrEquals(CitytocuisinemappingColumns.CITY_ID, value);
        return this;
    }

    public CitytocuisinemappingSelection orderByCityId(boolean desc) {
        orderBy(CitytocuisinemappingColumns.CITY_ID, desc);
        return this;
    }

    public CitytocuisinemappingSelection orderByCityId() {
        orderBy(CitytocuisinemappingColumns.CITY_ID, false);
        return this;
    }
}
