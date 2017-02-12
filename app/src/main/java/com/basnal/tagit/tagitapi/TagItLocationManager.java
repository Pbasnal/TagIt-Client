package com.basnal.tagit.tagitapi;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;


/**
 * Created by Basnal on 21-01-2017.
 */

public class TagItLocationManager
{
    private static TagItLocationManager instance;
    private static final String TAG = "TagItLocationManager";
    private static LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;
    private Timer timer1;
    private TagItLocationResult locationResult;
    private boolean gps_enabled = false;
    private boolean network_enabled = false;
    private static TagItData tagItData = TagItData.getInstance();

    private TagItLocationManager()
    {
    }

    public static TagItLocationManager getInstance(LocationManager manager)
    {
        if(instance == null)
        {
            instance = new TagItLocationManager();
            locationManager = manager;
        }
        return instance;
    }

    public boolean getLocation(TagItLocationResult result)
    {
        //I use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult = result;
        if (locationManager == null)
            return false;
        //exceptions will be thrown if provider is not permitted.
        try
        {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            //don't start listeners if no provider is enabled
            if (!gps_enabled && !network_enabled)
                return false;

            /*if (gps_enabled)
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 0, locationListener);*/
            if (network_enabled)
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        catch (SecurityException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return true;
    }

    LocationListener locationListener = new LocationListener()
    {
        @Override
        public void onLocationChanged(Location location)
        {
            try
            {
                //timer1.cancel();
                locationResult.gotLocation(location);
                locationManager.removeUpdates(this);
            }
            catch (SecurityException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {

        }

        @Override
        public void onProviderEnabled(String provider)
        {

        }

        @Override
        public void onProviderDisabled(String provider)
        {

        }
    };



}