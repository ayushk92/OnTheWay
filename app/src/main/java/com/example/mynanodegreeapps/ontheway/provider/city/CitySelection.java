package com.example.mynanodegreeapps.ontheway.provider.city;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractSelection;

/**
 * Selection for the {@code city} table.
 */
public class CitySelection extends AbstractSelection<CitySelection> {
    @Override
    protected Uri baseUri() {
        return CityColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CityCursor} object, which is positioned before the first entry, or null.
     */
    public CityCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CityCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CityCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CityCursor} object, which is positioned before the first entry, or null.
     */
    public CityCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CityCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CityCursor query(Context context) {
        return query(context, null);
    }


    public CitySelection id(long... value) {
        addEquals("city." + CityColumns._ID, toObjectArray(value));
        return this;
    }

    public CitySelection idNot(long... value) {
        addNotEquals("city." + CityColumns._ID, toObjectArray(value));
        return this;
    }

    public CitySelection orderById(boolean desc) {
        orderBy("city." + CityColumns._ID, desc);
        return this;
    }

    public CitySelection orderById() {
        return orderById(false);
    }

    public CitySelection name(String... value) {
        addEquals(CityColumns.NAME, value);
        return this;
    }

    public CitySelection nameNot(String... value) {
        addNotEquals(CityColumns.NAME, value);
        return this;
    }

    public CitySelection nameLike(String... value) {
        addLike(CityColumns.NAME, value);
        return this;
    }

    public CitySelection nameContains(String... value) {
        addContains(CityColumns.NAME, value);
        return this;
    }

    public CitySelection nameStartsWith(String... value) {
        addStartsWith(CityColumns.NAME, value);
        return this;
    }

    public CitySelection nameEndsWith(String... value) {
        addEndsWith(CityColumns.NAME, value);
        return this;
    }

    public CitySelection orderByName(boolean desc) {
        orderBy(CityColumns.NAME, desc);
        return this;
    }

    public CitySelection orderByName() {
        orderBy(CityColumns.NAME, false);
        return this;
    }
}
