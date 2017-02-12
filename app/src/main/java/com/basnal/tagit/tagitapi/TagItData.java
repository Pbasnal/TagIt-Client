package com.basnal.tagit.tagitapi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by Basnal on 05-01-2017.
 */

public class TagItData
{
    public GoogleMap mMap;
    //public Context appContext;
    public LayoutInflater inflater;

    protected ArrayList<TagItRequests> requests = new ArrayList<TagItRequests>();
    private static TagItData instance;

    private TagItData(){}

    public static TagItData getInstance()
    {
        if (instance == null)
            instance = new TagItData();
        return instance;
    }

    public void configure(Context context, LayoutInflater inflater)
    {
        //appContext = context;
        this.inflater = inflater;
    }

    public boolean checkPermission(Context appContext)
    {
        if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return false;
        }
        return true;
    }

    public void addRequest(TagItRequests tagItRequest)
    {
        requests.add(tagItRequest);
    }

    public ArrayList<TagItRequests> getRequests()
    {
        return requests;
    }
}
