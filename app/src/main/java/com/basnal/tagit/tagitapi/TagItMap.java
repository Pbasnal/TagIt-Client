package com.basnal.tagit.tagitapi;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * Created by Basnal on 17-01-2017.
 */

public class TagItMap implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener
{
    private static TagItMap instance;
    private static final String TAG = "TagItMap";

    private TagItMap()
    {
    }

    public static TagItMap getInstance()
    {
        if(instance == null)
            instance = new TagItMap();
        return instance;
    }

    public void initializeTagItMap(Context appContext, GoogleMap googleMap)
    {
        Log.v(TAG, "onMapReady");

        TagItData tagItData = TagItData.getInstance();
        tagItData.mMap = googleMap;

        tagItData.mMap.setOnMarkerClickListener(new TagItMapMarker());
        tagItData.mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());

        TagItLocationResult locationResult = new TagItLocationResult(){
            @Override
            public void gotLocation(Location location)
            {
                TagItData tagItData = TagItData.getInstance();
                LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());

                MarkerOptions markerOptions = new MarkerOptions().position(loc).title("Marker");

                tagItData.mMap.addMarker(markerOptions);
                tagItData.mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
                tagItData.mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
            }
        };
        TagItLocationManager myLocation = TagItLocationManager.getInstance((LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE));
        myLocation.getLocation(locationResult);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {

    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    @Override
    public void onLocationChanged(Location location)
    {

    }
}
