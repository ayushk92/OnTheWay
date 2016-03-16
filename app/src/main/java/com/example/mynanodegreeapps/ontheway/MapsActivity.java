package com.example.mynanodegreeapps.ontheway;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,LoaderManager.LoaderCallbacks<ArrayList<RouteToRestaurant>>,
                                                                FilterFragment.Callback{

    private final String LOG_TAG = MapsActivity.class.getSimpleName();

    private GoogleMap mMap;

    private Road mRoad;

    private static final int MAPS_DATA_LOADER = 0;


    private String mfromLocation;
    private String mtoLocation;

    private ArrayList<RouteToRestaurant> routeToRestaurants;

    private final String COST_FOR_TWO_FRAGMENT_TAG = "costfortwo";

    private final String FILTER_FRAGMENT_STATE = "filterFragment";

    private final String RESTAURANTS_KEY = "restaurants";

    @Bind(R.id.costForTwoFilter)
    ImageView costForTwoFilter_imageView;

    @OnClick(R.id.costForTwoFilter)
        void onClickcostForTwoFilter(){
            getFragmentManager().beginTransaction().hide(getFragmentManager().findFragmentById(R.id.filterFragment)).commit();
            costForTwoFilter_imageView.setVisibility(View.GONE);
            hasOnlineDeliveryFilter_imageView.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.filterLayoutContainer, new CostForTwoFragment())
                    .addToBackStack(COST_FOR_TWO_FRAGMENT_TAG)
                    .commit();
    }

    @Bind(R.id.hasOnlineDeliveryFilter)
    ImageView hasOnlineDeliveryFilter_imageView;

    @OnClick(R.id.hasOnlineDeliveryFilter)
    void onClickHasOnlineDeliveryFilter(){
        if(RestaurantFilters.getInstance().getHasOnlineDelivery() == null){
            hasOnlineDeliveryFilter_imageView.setBackground(getDrawable(R.drawable.filteritemselected));
            RestaurantFilters.getInstance().setHasOnlineDelivery(true);
            this.onFilterChanged(2);
        }
        else
        {
            hasOnlineDeliveryFilter_imageView.setBackground(getDrawable(R.drawable.filteritem));
            RestaurantFilters.getInstance().setHasOnlineDelivery(null);
            this.onFilterChanged(3);
        }
    }

    @Override
    public void onFilterChanged(int resultCode) {
        if(routeToRestaurants != null) {
            mMap.clear();
            addRouteToMap();
            getLoaderManager().destroyLoader(MAPS_DATA_LOADER);

            //update UI
            if(resultCode == CostForTwoFragment.COST_FOR_TWO_CHANGED){
                this.onBackPressed();
            }
            ArrayList<Restaurant> restaurants = getRestaurantsAfterFilter();
            addRestaurantsToMap(restaurants);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .show(
                        getFragmentManager().findFragmentById(R.id.filterFragment)
                ).commit();
        costForTwoFilter_imageView.setVisibility(View.VISIBLE);
        hasOnlineDeliveryFilter_imageView.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }

    private void addRouteToMap(){
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;
        for (RouteToRestaurant rtr : routeToRestaurants) {
            lineOptions = new PolylineOptions();
            lineOptions.addAll(rtr.getRoutePoints());
            mMap.addPolyline(lineOptions);
        }
    }

    private void addRestaurantsToMap(ArrayList<Restaurant> restaurants){
        for (Restaurant restaurant : restaurants){
            mMap.addMarker(new MarkerOptions().position(restaurant.getLocation()).title(restaurant.getName()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(routeToRestaurants != null) {
            super.onSaveInstanceState(outState);
            RestaurantFilters.getInstance().setCurrentRouteToRestaurants(routeToRestaurants);
            if(getFragmentManager().findFragmentByTag(COST_FOR_TWO_FRAGMENT_TAG) != null)
                getFragmentManager().putFragment(outState,
                                                 FILTER_FRAGMENT_STATE,
                                                 getFragmentManager().findFragmentByTag(COST_FOR_TWO_FRAGMENT_TAG));
            else
                getFragmentManager().putFragment(outState,
                        FILTER_FRAGMENT_STATE,
                        getFragmentManager().findFragmentById(R.id.filterFragment));

            //outState.putParcelableArrayList(RESTAURANTS_KEY, routeToRestaurants);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        routeToRestaurants = RestaurantFilters.getInstance().getCurrentRouteToRestaurants();
        getFragmentManager().beginTransaction().
                show(getFragmentManager().getFragment(savedInstanceState, FILTER_FRAGMENT_STATE)).
                commit();
        //routeToRestaurants = savedInstanceState.getParcelableArrayList(RESTAURANTS_KEY);
    }

    private ArrayList<Restaurant> getRestaurantsAfterFilter(){
        ArrayList<Restaurant> resultantRestaurants = new ArrayList<Restaurant>();

        //apply cuisine filter
        ArrayList<String> selectedCuisines = RestaurantFilters.getInstance().getSelectedCuisines();
        if(selectedCuisines.size() > 0) {
            for (RouteToRestaurant rtr : routeToRestaurants) {
                for (Restaurant restaurant : rtr.getRestaurants().values()) {
                    for (String cuisine : restaurant.getCuisines()) {
                        if (selectedCuisines.contains(cuisine)) {
                            resultantRestaurants.add(restaurant);
                            break;
                        }
                    }
                }
            }
        }
        else {
            for (RouteToRestaurant rtr : routeToRestaurants) {
                for (Restaurant restaurant : rtr.getRestaurants().values()) {
                            resultantRestaurants.add(restaurant);
                }
            }
        }

        //apply price range filter on results fetched from restaurants filtered by cuisine
        Integer priceRange = RestaurantFilters.getInstance().getSelectedCostForTwo();
        if(priceRange != null) {
            Iterator<Restaurant> iterator = resultantRestaurants.iterator();
            while (iterator.hasNext()) {
                Restaurant restaurant = iterator.next();
                if (restaurant.getCostForTwoCategory() != priceRange)
                    iterator.remove();
            }
        }

        //apply online delivery filter
        Boolean hasOnlineDelivery = RestaurantFilters.getInstance().getHasOnlineDelivery();
        if(hasOnlineDelivery != null){
            Iterator<Restaurant> iterator = resultantRestaurants.iterator();
            while (iterator.hasNext()) {
                Restaurant restaurant = iterator.next();
                if (!restaurant.isHasOnlineDelivery())
                    iterator.remove();
            }
        }
        return resultantRestaurants;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(savedInstanceState == null) {
            mfromLocation = getIntent().getExtras().getString("fromLocation");
            mtoLocation = getIntent().getExtras().getString("toLocation");

            getLoaderManager().initLoader(MAPS_DATA_LOADER, null, this);
        }
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public Loader<ArrayList<RouteToRestaurant>> onCreateLoader(int id, Bundle args) {
        if(mtoLocation != null)
            return new FetchLocationData(getBaseContext(),mfromLocation,mtoLocation);
        else
            return null;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<RouteToRestaurant>> loader, ArrayList<RouteToRestaurant> data) {
        routeToRestaurants = data;
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;
        if(mMap != null)
            mMap.clear();


        for (RouteToRestaurant rtr : routeToRestaurants) {
            lineOptions = new PolylineOptions();
            lineOptions.addAll(rtr.getRoutePoints());
            mMap.addPolyline(lineOptions);

            for (Restaurant resto : rtr.getRestaurants().values())
                mMap.addMarker(new MarkerOptions().position(resto.getLocation()).title(resto.getName()));
        }
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(data[0].getRoutePoints()[0],
//                                                                     data[0].getRoutePoints()[1]),15));

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<RouteToRestaurant>> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.maps_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_apply:
                Intent intent = new Intent(this,FilterFragment.class);
                startActivityForResult(intent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(routeToRestaurants != null) {
            addRouteToMap();
            for(RouteToRestaurant rtr : routeToRestaurants){
                addRestaurantsToMap(new ArrayList<Restaurant>(rtr.getRestaurants().values()));
            }
            onFilterChanged(0);
        }
    }


}
