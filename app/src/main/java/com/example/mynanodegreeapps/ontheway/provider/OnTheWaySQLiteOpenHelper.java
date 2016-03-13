package com.example.mynanodegreeapps.ontheway.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.mynanodegreeapps.ontheway.BuildConfig;
import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.category.CategoryColumns;
import com.example.mynanodegreeapps.ontheway.provider.city.CityColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.example.mynanodegreeapps.ontheway.provider.restaurant.RestaurantColumns;

public class OnTheWaySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = OnTheWaySQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "OnTheWay.db";
    private static final int DATABASE_VERSION = 1;
    private static OnTheWaySQLiteOpenHelper sInstance;
    private final Context mContext;
    private final OnTheWaySQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_CITYTOCUISINEMAPPING = "CREATE TABLE IF NOT EXISTS "
            + CitytocuisinemappingColumns.TABLE_NAME + " ( "
            + CitytocuisinemappingColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CitytocuisinemappingColumns.CUISINE_ID + " INTEGER NOT NULL, "
            + CitytocuisinemappingColumns.CITY_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT unique_mapping UNIQUE (cuisine_id, city_id) "
            + ", CONSTRAINT foreignKey_cuisineId FOREIGN KEY(cuisine_id) REFERENCES cuisine(_ID) "
            + ", CONSTRAINT foreignKey_cityId FOREIGN KEY(city_id) REFERENCES city(_ID) "
            + " );";

    public static final String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS "
            + CategoryColumns.TABLE_NAME + " ( "
            + CategoryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoryColumns.CATEGORY_NAME + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_TABLE_CITY = "CREATE TABLE IF NOT EXISTS "
            + CityColumns.TABLE_NAME + " ( "
            + CityColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CityColumns.NAME + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_TABLE_CUISINE = "CREATE TABLE IF NOT EXISTS "
            + CuisineColumns.TABLE_NAME + " ( "
            + CuisineColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CuisineColumns.CUISINE_NAME + " TEXT NOT NULL "
            + " );";

    public static final String SQL_CREATE_TABLE_RESTAURANT = "CREATE TABLE IF NOT EXISTS "
            + RestaurantColumns.TABLE_NAME + " ( "
            + RestaurantColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RestaurantColumns.RESTAURANT_NAME + " TEXT NOT NULL, "
            + RestaurantColumns.PINCODE + " TEXT NOT NULL, "
            + RestaurantColumns.ADDRESS + " TEXT NOT NULL, "
            + RestaurantColumns.LOCALITY + " TEXT NOT NULL, "
            + RestaurantColumns.CITY + " TEXT NOT NULL, "
            + RestaurantColumns.LATITUDE + " REAL NOT NULL, "
            + RestaurantColumns.LONGITUDE + " REAL NOT NULL, "
            + RestaurantColumns.HASONLINEDELIVERY + " INTEGER NOT NULL "
            + " );";

    // @formatter:on

    public static OnTheWaySQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static OnTheWaySQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static OnTheWaySQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new OnTheWaySQLiteOpenHelper(context);
    }

    private OnTheWaySQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new OnTheWaySQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static OnTheWaySQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new OnTheWaySQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private OnTheWaySQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new OnTheWaySQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CITYTOCUISINEMAPPING);
        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        db.execSQL(SQL_CREATE_TABLE_CITY);
        db.execSQL(SQL_CREATE_TABLE_CUISINE);
        db.execSQL(SQL_CREATE_TABLE_RESTAURANT);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
