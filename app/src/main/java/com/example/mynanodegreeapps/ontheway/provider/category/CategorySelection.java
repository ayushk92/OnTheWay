package com.example.mynanodegreeapps.ontheway.provider.category;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.ontheway.provider.base.AbstractSelection;

/**
 * Selection for the {@code category} table.
 */
public class CategorySelection extends AbstractSelection<CategorySelection> {
    @Override
    protected Uri baseUri() {
        return CategoryColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CategoryCursor} object, which is positioned before the first entry, or null.
     */
    public CategoryCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CategoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CategoryCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CategoryCursor} object, which is positioned before the first entry, or null.
     */
    public CategoryCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CategoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CategoryCursor query(Context context) {
        return query(context, null);
    }


    public CategorySelection id(long... value) {
        addEquals("category." + CategoryColumns._ID, toObjectArray(value));
        return this;
    }

    public CategorySelection idNot(long... value) {
        addNotEquals("category." + CategoryColumns._ID, toObjectArray(value));
        return this;
    }

    public CategorySelection orderById(boolean desc) {
        orderBy("category." + CategoryColumns._ID, desc);
        return this;
    }

    public CategorySelection orderById() {
        return orderById(false);
    }

    public CategorySelection categoryName(String... value) {
        addEquals(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }

    public CategorySelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }

    public CategorySelection categoryNameLike(String... value) {
        addLike(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }

    public CategorySelection categoryNameContains(String... value) {
        addContains(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }

    public CategorySelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }

    public CategorySelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }

    public CategorySelection orderByCategoryName(boolean desc) {
        orderBy(CategoryColumns.CATEGORY_NAME, desc);
        return this;
    }

    public CategorySelection orderByCategoryName() {
        orderBy(CategoryColumns.CATEGORY_NAME, false);
        return this;
    }
}
