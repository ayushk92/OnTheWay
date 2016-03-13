package com.example.mynanodegreeapps.ontheway;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mynanodegreeapps.ontheway.provider.citytocuisinemapping.CitytocuisinemappingColumns;
import com.example.mynanodegreeapps.ontheway.provider.cuisine.CuisineColumns;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by akhatri on 05/03/16.
 */
public class FilterFragment extends Fragment {
    public FilterFragment() {
        super();
    }

    @OnClick(R.id.cuisineFilter)
            void loadCuisines(){
        Intent intent = new Intent(getActivity(),FilterListActivity.class);
        startActivityForResult(intent, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_filter,container,false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onFilterChanged(int requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((Callback)getActivity()).onFilterChanged(resultCode);


    }
}
