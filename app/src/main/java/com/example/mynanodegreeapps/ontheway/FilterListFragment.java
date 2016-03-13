package com.example.mynanodegreeapps.ontheway;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by akhatri on 05/03/16.
 */
public class FilterListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public FilterListFragment() {
        super();
    }
    @Bind(R.id.filter_list)
    ListView filterList;

    CuisineAdapter mAdapter;


    private final int CUISINE_DATA_LOADER = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_filterlist,container,false);
        ButterKnife.bind(this,rootView);
        mAdapter = new CuisineAdapter(getActivity(),null);
        filterList.setAdapter(mAdapter);
        getLoaderManager().initLoader(CUISINE_DATA_LOADER, null, this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {

        //getActivity().finish();

        super.onPause();
    }





    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                CitytocuisinemappingColumns.getCuisinesOfCityUri(),
                new String[]{CuisineColumns._ID, CuisineColumns.CUISINE_NAME},
                CitytocuisinemappingColumns.CITY_ID + " = ?",
                new String[]{ "6"},
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        int i = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            i++;
            cursor.moveToNext();
        }
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
