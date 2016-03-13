package com.example.mynanodegreeapps.ontheway.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mynanodegreeapps.ontheway.BuildConfig;
import com.example.mynanodegreeapps.ontheway.provider.base.BaseContentProvider;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.category.CategoryColumns;
import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.example.mynanodegreeapps.ontheway.provider.restaurant.RestaurantColumns;

public class OnTheWayProvider extends BaseContentProvider {
    private static final String TAG = OnTheWayProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.example.mynanodegreeapps.ontheway.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_CITYTOCUISINEMAPPING = 0;
    private static final int URI_TYPE_CITYTOCUISINEMAPPING_ID = 1;

    private static final int URI_TYPE_CATEGORY = 2;
    private static final int URI_TYPE_CATEGORY_ID = 3;

    private static final int URI_TYPE_CITY = 4;
    private static final int URI_TYPE_CITY_ID = 5;

    private static final int URI_TYPE_CUISINE = 6;
    private static final int URI_TYPE_CUISINE_ID = 7;

    private static final int URI_TYPE_RESTAURANT = 8;
    private static final int URI_TYPE_RESTAURANT_ID = 9;

    private static final int URI_TYPE_CUISINE_CITY = 10;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, CitytocuisinemappingColumns.TABLE_NAME, URI_TYPE_CITYTOCUISINEMAPPING);
        URI_MATCHER.addURI(AUTHORITY, CitytocuisinemappingColumns.TABLE_NAME + "/#", URI_TYPE_CITYTOCUISINEMAPPING_ID);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME, URI_TYPE_CATEGORY);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME + "/#", URI_TYPE_CATEGORY_ID);
        URI_MATCHER.addURI(AUTHORITY, CityColumns.TABLE_NAME, URI_TYPE_CITY);
        URI_MATCHER.addURI(AUTHORITY, CityColumns.TABLE_NAME + "/#", URI_TYPE_CITY_ID);
        URI_MATCHER.addURI(AUTHORITY, CuisineColumns.TABLE_NAME, URI_TYPE_CUISINE);
        URI_MATCHER.addURI(AUTHORITY, CuisineColumns.TABLE_NAME + "/#", URI_TYPE_CUISINE_ID);
        URI_MATCHER.addURI(AUTHORITY, RestaurantColumns.TABLE_NAME, URI_TYPE_RESTAURANT);
        URI_MATCHER.addURI(AUTHORITY, RestaurantColumns.TABLE_NAME + "/#", URI_TYPE_RESTAURANT_ID);
        URI_MATCHER.addURI(AUTHORITY, CitytocuisinemappingColumns.TABLE_NAME + "/city", URI_TYPE_CUISINE_CITY);

    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return OnTheWaySQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_CITYTOCUISINEMAPPING:
                return TYPE_CURSOR_DIR + CitytocuisinemappingColumns.TABLE_NAME;
            case URI_TYPE_CITYTOCUISINEMAPPING_ID:
                return TYPE_CURSOR_ITEM + CitytocuisinemappingColumns.TABLE_NAME;

            case URI_TYPE_CATEGORY:
                return TYPE_CURSOR_DIR + CategoryColumns.TABLE_NAME;
            case URI_TYPE_CATEGORY_ID:
                return TYPE_CURSOR_ITEM + CategoryColumns.TABLE_NAME;

            case URI_TYPE_CITY:
                return TYPE_CURSOR_DIR + CityColumns.TABLE_NAME;
            case URI_TYPE_CITY_ID:
                return TYPE_CURSOR_ITEM + CityColumns.TABLE_NAME;

            case URI_TYPE_CUISINE:
                return TYPE_CURSOR_DIR + CuisineColumns.TABLE_NAME;
            case URI_TYPE_CUISINE_ID:
                return TYPE_CURSOR_ITEM + CuisineColumns.TABLE_NAME;

            case URI_TYPE_RESTAURANT:
                return TYPE_CURSOR_DIR + RestaurantColumns.TABLE_NAME;
            case URI_TYPE_RESTAURANT_ID:
                return TYPE_CURSOR_ITEM + RestaurantColumns.TABLE_NAME;
            case URI_TYPE_CUISINE_CITY:
                return TYPE_CURSOR_DIR + CitytocuisinemappingColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_CITYTOCUISINEMAPPING:
            case URI_TYPE_CITYTOCUISINEMAPPING_ID:
                res.table = CitytocuisinemappingColumns.TABLE_NAME;
                res.idColumn = CitytocuisinemappingColumns._ID;
                res.tablesWithJoins = CitytocuisinemappingColumns.TABLE_NAME;
                res.orderBy = CitytocuisinemappingColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CATEGORY:
            case URI_TYPE_CATEGORY_ID:
                res.table = CategoryColumns.TABLE_NAME;
                res.idColumn = CategoryColumns._ID;
                res.tablesWithJoins = CategoryColumns.TABLE_NAME;
                res.orderBy = CategoryColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CITY:
            case URI_TYPE_CITY_ID:
                res.table = CityColumns.TABLE_NAME;
                res.idColumn = CityColumns._ID;
                res.tablesWithJoins = CityColumns.TABLE_NAME;
                res.orderBy = CityColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CUISINE:
            case URI_TYPE_CUISINE_ID:
                res.table = CuisineColumns.TABLE_NAME;
                res.idColumn = CuisineColumns._ID;
                res.tablesWithJoins = CuisineColumns.TABLE_NAME;
                res.orderBy = CuisineColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_RESTAURANT:
            case URI_TYPE_RESTAURANT_ID:
                res.table = RestaurantColumns.TABLE_NAME;
                res.idColumn = RestaurantColumns._ID;
                res.tablesWithJoins = RestaurantColumns.TABLE_NAME;
                res.orderBy = RestaurantColumns.DEFAULT_ORDER;
                break;
            case URI_TYPE_CUISINE_CITY:
                res.tablesWithJoins =
                        CuisineColumns.TABLE_NAME + " INNER JOIN " + CitytocuisinemappingColumns.TABLE_NAME + " ON "
                                + CuisineColumns.TABLE_NAME + "." + CuisineColumns._ID + " = "
                                + CitytocuisinemappingColumns.TABLE_NAME + "." + CitytocuisinemappingColumns.CUISINE_ID;
                res.idColumn = CitytocuisinemappingColumns._ID;
                res.orderBy = CuisineColumns.DEFAULT_ORDER;
                res.table = CitytocuisinemappingColumns.TABLE_NAME;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_CITYTOCUISINEMAPPING_ID:
            case URI_TYPE_CATEGORY_ID:
            case URI_TYPE_CITY_ID:
            case URI_TYPE_CUISINE_ID:
            case URI_TYPE_RESTAURANT_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
