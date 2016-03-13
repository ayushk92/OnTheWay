package com.example.mynanodegreeapps.ontheway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by akhatri on 05/03/16.
 */
public class FilterListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.filter_list)
        ListView filterList;

    CuisineAdapter mAdapter;

    public static final int CUISINE_CHANGED = 0;


    private final int CUISINE_DATA_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterlist);
        ButterKnife.bind(this);
        getSupportLoaderManager().initLoader(CUISINE_DATA_LOADER, null, this);



//        if(savedInstanceState == null){
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.filterlist_container,new FilterListFragment())
//                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_apply:
                RestaurantFilters.getInstance().setCuisineIDs(mAdapter.getCheckedCuisine());
                RestaurantFilters.getInstance().setSelectedCuisines(mAdapter.getSelectedCuisines());
                setResult(CUISINE_CHANGED,null);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        mAdapter = new CuisineAdapter(this,null);
        filterList.setAdapter(mAdapter);

        int currentCityId = RestaurantFilters.getInstance().getCurrentCityId();
        return new CursorLoader(this,
                CitytocuisinemappingColumns.getCuisinesOfCityUri(),
                new String[]{CuisineColumns._ID, CuisineColumns.CUISINE_NAME},
                CitytocuisinemappingColumns.CITY_ID + " = ?",
                new String[]{ String.valueOf(currentCityId)},
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

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
