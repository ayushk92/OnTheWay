package com.example.mynanodegreeapps.ontheway.provider.searchhistory;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractSelection;

/**
 * Selection for the {@code searchhistory} table.
 */
public class SearchhistorySelection extends AbstractSelection<SearchhistorySelection> {
    @Override
    protected Uri baseUri() {
        return SearchhistoryColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code SearchhistoryCursor} object, which is positioned before the first entry, or null.
     */
    public SearchhistoryCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new SearchhistoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public SearchhistoryCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code SearchhistoryCursor} object, which is positioned before the first entry, or null.
     */
    public SearchhistoryCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new SearchhistoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public SearchhistoryCursor query(Context context) {
        return query(context, null);
    }


    public SearchhistorySelection id(long... value) {
        addEquals("searchhistory." + SearchhistoryColumns._ID, toObjectArray(value));
        return this;
    }

    public SearchhistorySelection idNot(long... value) {
        addNotEquals("searchhistory." + SearchhistoryColumns._ID, toObjectArray(value));
        return this;
    }

    public SearchhistorySelection orderById(boolean desc) {
        orderBy("searchhistory." + SearchhistoryColumns._ID, desc);
        return this;
    }

    public SearchhistorySelection orderById() {
        return orderById(false);
    }

    public SearchhistorySelection sourceLocation(String... value) {
        addEquals(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }

    public SearchhistorySelection sourceLocationNot(String... value) {
        addNotEquals(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }

    public SearchhistorySelection sourceLocationLike(String... value) {
        addLike(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }

    public SearchhistorySelection sourceLocationContains(String... value) {
        addContains(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }

    public SearchhistorySelection sourceLocationStartsWith(String... value) {
        addStartsWith(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }

    public SearchhistorySelection sourceLocationEndsWith(String... value) {
        addEndsWith(SearchhistoryColumns.SOURCE_LOCATION, value);
        return this;
    }

    public SearchhistorySelection orderBySourceLocation(boolean desc) {
        orderBy(SearchhistoryColumns.SOURCE_LOCATION, desc);
        return this;
    }

    public SearchhistorySelection orderBySourceLocation() {
        orderBy(SearchhistoryColumns.SOURCE_LOCATION, false);
        return this;
    }

    public SearchhistorySelection destinationLocation(String... value) {
        addEquals(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }

    public SearchhistorySelection destinationLocationNot(String... value) {
        addNotEquals(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }

    public SearchhistorySelection destinationLocationLike(String... value) {
        addLike(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }

    public SearchhistorySelection destinationLocationContains(String... value) {
        addContains(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }

    public SearchhistorySelection destinationLocationStartsWith(String... value) {
        addStartsWith(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }

    public SearchhistorySelection destinationLocationEndsWith(String... value) {
        addEndsWith(SearchhistoryColumns.DESTINATION_LOCATION, value);
        return this;
    }

    public SearchhistorySelection orderByDestinationLocation(boolean desc) {
        orderBy(SearchhistoryColumns.DESTINATION_LOCATION, desc);
        return this;
    }

    public SearchhistorySelection orderByDestinationLocation() {
        orderBy(SearchhistoryColumns.DESTINATION_LOCATION, false);
        return this;
    }

    public SearchhistorySelection sourceCoordinates(String... value) {
        addEquals(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection sourceCoordinatesNot(String... value) {
        addNotEquals(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection sourceCoordinatesLike(String... value) {
        addLike(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection sourceCoordinatesContains(String... value) {
        addContains(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection sourceCoordinatesStartsWith(String... value) {
        addStartsWith(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection sourceCoordinatesEndsWith(String... value) {
        addEndsWith(SearchhistoryColumns.SOURCE_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection orderBySourceCoordinates(boolean desc) {
        orderBy(SearchhistoryColumns.SOURCE_COORDINATES, desc);
        return this;
    }

    public SearchhistorySelection orderBySourceCoordinates() {
        orderBy(SearchhistoryColumns.SOURCE_COORDINATES, false);
        return this;
    }

    public SearchhistorySelection destinationCoordinates(String... value) {
        addEquals(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection destinationCoordinatesNot(String... value) {
        addNotEquals(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection destinationCoordinatesLike(String... value) {
        addLike(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection destinationCoordinatesContains(String... value) {
        addContains(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection destinationCoordinatesStartsWith(String... value) {
        addStartsWith(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection destinationCoordinatesEndsWith(String... value) {
        addEndsWith(SearchhistoryColumns.DESTINATION_COORDINATES, value);
        return this;
    }

    public SearchhistorySelection orderByDestinationCoordinates(boolean desc) {
        orderBy(SearchhistoryColumns.DESTINATION_COORDINATES, desc);
        return this;
    }

    public SearchhistorySelection orderByDestinationCoordinates() {
        orderBy(SearchhistoryColumns.DESTINATION_COORDINATES, false);
        return this;
    }
}
