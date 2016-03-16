package com.example.mynanodegreeapps.ontheway;


//import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.Fragment;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynanodegreeapps.ontheway.provider.searchhistory.SearchhistoryColumns;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

/**
 * Created by akhatri on 03/01/16.
 */
public class SearchFragment extends android.app.Fragment implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks {

    public SearchFragment(){

    }

    private final String LOG_TAG = SearchFragment.class.getSimpleName();

    private boolean mAddressRequested = false;

    private String mfromLocation;
    private String mtoLocation;
    private String mAddressOutput;

    private GoogleApiClient mGoogleApiClient;

    private Location mLastLocation;
    private AddressResultReceiver mResultReceiver;

    int PLACE_AUTOCOMPLETE_DESTINATION_REQUEST_CODE = 1;
    int PLACE_AUTOCOMPLETE_SOURCE_REQUEST_CODE = 2;

    protected void startIntentService() {
        Intent intent = new Intent(getActivity(), FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
        getActivity().startService(intent);
    }

    private void displayAddressOutput(){
        if(mLastLocation != null)
            mfromLocation = String.valueOf(mLastLocation.getLatitude()) + "," + String.valueOf(mLastLocation.getLongitude());
        TextView tv = (TextView) SearchFragment.this.getActivity().findViewById(R.id.fromLocation);
        tv.setText(mAddressOutput);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            //Load city reference data
            Intent intent = new Intent(getActivity(), FetchReferenceDataIntentService.class);
            intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
            getActivity().startService(intent);


            // Determine whether a Geocoder is available.
            if (!Geocoder.isPresent()) {
                return;
            }

            if (mAddressRequested) {
                startIntentService();
                mAddressRequested = false;
            }
        }

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

//        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                mtoLocation = String.valueOf(place.getLatLng().latitude) + "," + String.valueOf(place.getLatLng().longitude);
//                Log.i(LOG_TAG, "Place: " + place.getName());
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i(LOG_TAG, "An error occurred: " + status);
//            }
//        });

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).
                addApi(LocationServices.API).
                build();
        super.onActivityCreated(savedInstanceState);
    }

    private void addSearchHistory(String source_location,String source_coordinates,
                                  String destination_location, String destination_coordinates){
        ContentValues searchHistory = new ContentValues();
        searchHistory.put(SearchhistoryColumns.SOURCE_LOCATION,source_location);
        searchHistory.put(SearchhistoryColumns.SOURCE_COORDINATES,source_coordinates);
        searchHistory.put(SearchhistoryColumns.DESTINATION_LOCATION,destination_location);
        searchHistory.put(SearchhistoryColumns.DESTINATION_COORDINATES, destination_coordinates);

        Uri inserted_uri = getActivity().getContentResolver().insert(SearchhistoryColumns.CONTENT_URI,
                                                  searchHistory);

        //Update widget after getting data
        Intent intent = new Intent(Constants.SEARCH_HISTORY_DATA_UPDATED);
        getActivity().sendBroadcast(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_search,container,false);

        Button searchBtn = (Button)rootView.findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             addSearchHistory(((TextView)rootView.findViewById(R.id.fromLocation)).getText().toString(),
                                                               mfromLocation,
                                                               ((TextView)rootView.findViewById(R.id.toLocation)).getText().toString(),
                                                              mtoLocation);
                                             Intent mapIntent = new Intent(getActivity(), MapsActivity.class);
                                             mapIntent.putExtra("fromLocation", (mfromLocation));
                                             mapIntent.putExtra("toLocation", mtoLocation);
                                             startActivity(mapIntent);
                                         }
                                     }
        );

        TextView toLocation_tv = (TextView) rootView.findViewById(R.id.toLocation);

        toLocation_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_DESTINATION_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                return false;
            }
        });

        TextView fromLocation_tv = (TextView) rootView.findViewById(R.id.fromLocation);

        fromLocation_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_SOURCE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
                return false;
            }
        });

        ImageView currentLocationButton = (ImageView) rootView.findViewById(R.id.currentLocation);

        currentLocationButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Only start the service to fetch the address if GoogleApiClient is
                // connected.
                if (mGoogleApiClient.isConnected() && mLastLocation != null) {
                    if (mResultReceiver == null)
                        mResultReceiver = new AddressResultReceiver(new Handler());
                    startIntentService();
                    return false;
                }
                // If GoogleApiClient isn't connected, process the user's request by
                // setting mAddressRequested to true. Later, when GoogleApiClient connects,
                // launch the service to fetch the address. As far as the user is
                // concerned, pressing the Fetch Address button
                // immediately kicks off the process of getting the address.
                mAddressRequested = true;
                return false;
            }
        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_DESTINATION_REQUEST_CODE) {

            Status status = PlaceAutocomplete.getStatus(getActivity(), data);
            if (resultCode == PlaceAutocomplete.RESULT_ERROR) {

                // TODO: Handle the error.
                Log.i(LOG_TAG, status.getStatusMessage());

            }
            else
            {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                if(place != null) {

                    mtoLocation = String.valueOf(place.getLatLng().latitude) + "," + String.valueOf(place.getLatLng().longitude);
                    TextView tv = (TextView) getActivity().findViewById(R.id.toLocation);
                    tv.setText(place.getAddress().toString());
                    Log.i(LOG_TAG, "Place: " + place.getName());
                }
            }
        }
        else if (requestCode == PLACE_AUTOCOMPLETE_SOURCE_REQUEST_CODE) {

            Status status = PlaceAutocomplete.getStatus(getActivity(), data);
            if (resultCode == PlaceAutocomplete.RESULT_ERROR) {

                // TODO: Handle the error.
                Log.i(LOG_TAG, status.getStatusMessage());

            }
            else
            {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                if(place != null) {

                    mfromLocation = String.valueOf(place.getLatLng().latitude) + "," + String.valueOf(place.getLatLng().longitude);
                    TextView tv = (TextView) getActivity().findViewById(R.id.fromLocation);
                    tv.setText(place.getAddress().toString());
                    Log.i(LOG_TAG, "Place: " + place.getName());
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string
            // or an error message sent from the intent service.
            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            displayAddressOutput();

        }

    }
}
