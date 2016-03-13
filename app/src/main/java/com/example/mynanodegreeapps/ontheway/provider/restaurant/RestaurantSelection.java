package com.example.mynanodegreeapps.ontheway.provider.restaurant;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractSelection;

/**
 * Selection for the {@code restaurant} table.
 */
public class RestaurantSelection extends AbstractSelection<RestaurantSelection> {
    @Override
    protected Uri baseUri() {
        return RestaurantColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code RestaurantCursor} object, which is positioned before the first entry, or null.
     */
    public RestaurantCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new RestaurantCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public RestaurantCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code RestaurantCursor} object, which is positioned before the first entry, or null.
     */
    public RestaurantCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new RestaurantCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public RestaurantCursor query(Context context) {
        return query(context, null);
    }


    public RestaurantSelection id(long... value) {
        addEquals("restaurant." + RestaurantColumns._ID, toObjectArray(value));
        return this;
    }

    public RestaurantSelection idNot(long... value) {
        addNotEquals("restaurant." + RestaurantColumns._ID, toObjectArray(value));
        return this;
    }

    public RestaurantSelection orderById(boolean desc) {
        orderBy("restaurant." + RestaurantColumns._ID, desc);
        return this;
    }

    public RestaurantSelection orderById() {
        return orderById(false);
    }

    public RestaurantSelection restaurantName(String... value) {
        addEquals(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }

    public RestaurantSelection restaurantNameNot(String... value) {
        addNotEquals(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }

    public RestaurantSelection restaurantNameLike(String... value) {
        addLike(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }

    public RestaurantSelection restaurantNameContains(String... value) {
        addContains(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }

    public RestaurantSelection restaurantNameStartsWith(String... value) {
        addStartsWith(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }

    public RestaurantSelection restaurantNameEndsWith(String... value) {
        addEndsWith(RestaurantColumns.RESTAURANT_NAME, value);
        return this;
    }

    public RestaurantSelection orderByRestaurantName(boolean desc) {
        orderBy(RestaurantColumns.RESTAURANT_NAME, desc);
        return this;
    }

    public RestaurantSelection orderByRestaurantName() {
        orderBy(RestaurantColumns.RESTAURANT_NAME, false);
        return this;
    }

    public RestaurantSelection pincode(String... value) {
        addEquals(RestaurantColumns.PINCODE, value);
        return this;
    }

    public RestaurantSelection pincodeNot(String... value) {
        addNotEquals(RestaurantColumns.PINCODE, value);
        return this;
    }

    public RestaurantSelection pincodeLike(String... value) {
        addLike(RestaurantColumns.PINCODE, value);
        return this;
    }

    public RestaurantSelection pincodeContains(String... value) {
        addContains(RestaurantColumns.PINCODE, value);
        return this;
    }

    public RestaurantSelection pincodeStartsWith(String... value) {
        addStartsWith(RestaurantColumns.PINCODE, value);
        return this;
    }

    public RestaurantSelection pincodeEndsWith(String... value) {
        addEndsWith(RestaurantColumns.PINCODE, value);
        return this;
    }

    public RestaurantSelection orderByPincode(boolean desc) {
        orderBy(RestaurantColumns.PINCODE, desc);
        return this;
    }

    public RestaurantSelection orderByPincode() {
        orderBy(RestaurantColumns.PINCODE, false);
        return this;
    }

    public RestaurantSelection address(String... value) {
        addEquals(RestaurantColumns.ADDRESS, value);
        return this;
    }

    public RestaurantSelection addressNot(String... value) {
        addNotEquals(RestaurantColumns.ADDRESS, value);
        return this;
    }

    public RestaurantSelection addressLike(String... value) {
        addLike(RestaurantColumns.ADDRESS, value);
        return this;
    }

    public RestaurantSelection addressContains(String... value) {
        addContains(RestaurantColumns.ADDRESS, value);
        return this;
    }

    public RestaurantSelection addressStartsWith(String... value) {
        addStartsWith(RestaurantColumns.ADDRESS, value);
        return this;
    }

    public RestaurantSelection addressEndsWith(String... value) {
        addEndsWith(RestaurantColumns.ADDRESS, value);
        return this;
    }

    public RestaurantSelection orderByAddress(boolean desc) {
        orderBy(RestaurantColumns.ADDRESS, desc);
        return this;
    }

    public RestaurantSelection orderByAddress() {
        orderBy(RestaurantColumns.ADDRESS, false);
        return this;
    }

    public RestaurantSelection locality(String... value) {
        addEquals(RestaurantColumns.LOCALITY, value);
        return this;
    }

    public RestaurantSelection localityNot(String... value) {
        addNotEquals(RestaurantColumns.LOCALITY, value);
        return this;
    }

    public RestaurantSelection localityLike(String... value) {
        addLike(RestaurantColumns.LOCALITY, value);
        return this;
    }

    public RestaurantSelection localityContains(String... value) {
        addContains(RestaurantColumns.LOCALITY, value);
        return this;
    }

    public RestaurantSelection localityStartsWith(String... value) {
        addStartsWith(RestaurantColumns.LOCALITY, value);
        return this;
    }

    public RestaurantSelection localityEndsWith(String... value) {
        addEndsWith(RestaurantColumns.LOCALITY, value);
        return this;
    }

    public RestaurantSelection orderByLocality(boolean desc) {
        orderBy(RestaurantColumns.LOCALITY, desc);
        return this;
    }

    public RestaurantSelection orderByLocality() {
        orderBy(RestaurantColumns.LOCALITY, false);
        return this;
    }

    public RestaurantSelection city(String... value) {
        addEquals(RestaurantColumns.CITY, value);
        return this;
    }

    public RestaurantSelection cityNot(String... value) {
        addNotEquals(RestaurantColumns.CITY, value);
        return this;
    }

    public RestaurantSelection cityLike(String... value) {
        addLike(RestaurantColumns.CITY, value);
        return this;
    }

    public RestaurantSelection cityContains(String... value) {
        addContains(RestaurantColumns.CITY, value);
        return this;
    }

    public RestaurantSelection cityStartsWith(String... value) {
        addStartsWith(RestaurantColumns.CITY, value);
        return this;
    }

    public RestaurantSelection cityEndsWith(String... value) {
        addEndsWith(RestaurantColumns.CITY, value);
        return this;
    }

    public RestaurantSelection orderByCity(boolean desc) {
        orderBy(RestaurantColumns.CITY, desc);
        return this;
    }

    public RestaurantSelection orderByCity() {
        orderBy(RestaurantColumns.CITY, false);
        return this;
    }

    public RestaurantSelection latitude(double... value) {
        addEquals(RestaurantColumns.LATITUDE, toObjectArray(value));
        return this;
    }

    public RestaurantSelection latitudeNot(double... value) {
        addNotEquals(RestaurantColumns.LATITUDE, toObjectArray(value));
        return this;
    }

    public RestaurantSelection latitudeGt(double value) {
        addGreaterThan(RestaurantColumns.LATITUDE, value);
        return this;
    }

    public RestaurantSelection latitudeGtEq(double value) {
        addGreaterThanOrEquals(RestaurantColumns.LATITUDE, value);
        return this;
    }

    public RestaurantSelection latitudeLt(double value) {
        addLessThan(RestaurantColumns.LATITUDE, value);
        return this;
    }

    public RestaurantSelection latitudeLtEq(double value) {
        addLessThanOrEquals(RestaurantColumns.LATITUDE, value);
        return this;
    }

    public RestaurantSelection orderByLatitude(boolean desc) {
        orderBy(RestaurantColumns.LATITUDE, desc);
        return this;
    }

    public RestaurantSelection orderByLatitude() {
        orderBy(RestaurantColumns.LATITUDE, false);
        return this;
    }

    public RestaurantSelection longitude(double... value) {
        addEquals(RestaurantColumns.LONGITUDE, toObjectArray(value));
        return this;
    }

    public RestaurantSelection longitudeNot(double... value) {
        addNotEquals(RestaurantColumns.LONGITUDE, toObjectArray(value));
        return this;
    }

    public RestaurantSelection longitudeGt(double value) {
        addGreaterThan(RestaurantColumns.LONGITUDE, value);
        return this;
    }

    public RestaurantSelection longitudeGtEq(double value) {
        addGreaterThanOrEquals(RestaurantColumns.LONGITUDE, value);
        return this;
    }

    public RestaurantSelection longitudeLt(double value) {
        addLessThan(RestaurantColumns.LONGITUDE, value);
        return this;
    }

    public RestaurantSelection longitudeLtEq(double value) {
        addLessThanOrEquals(RestaurantColumns.LONGITUDE, value);
        return this;
    }

    public RestaurantSelection orderByLongitude(boolean desc) {
        orderBy(RestaurantColumns.LONGITUDE, desc);
        return this;
    }

    public RestaurantSelection orderByLongitude() {
        orderBy(RestaurantColumns.LONGITUDE, false);
        return this;
    }

    public RestaurantSelection hasonlinedelivery(boolean value) {
        addEquals(RestaurantColumns.HASONLINEDELIVERY, toObjectArray(value));
        return this;
    }

    public RestaurantSelection orderByHasonlinedelivery(boolean desc) {
        orderBy(RestaurantColumns.HASONLINEDELIVERY, desc);
        return this;
    }

    public RestaurantSelection orderByHasonlinedelivery() {
        orderBy(RestaurantColumns.HASONLINEDELIVERY, false);
        return this;
    }
}
