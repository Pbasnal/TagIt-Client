package com.basnal.tagit.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Basnal on 21-12-2016.
 */

public class Position
{
    private Double lng;

    public Double getLng()
    {
        return lng;
    }

    public void setLng(Double lng)
    {
        lng = lng;
    }

    private Double lat;

    public void setLat(Double lat)
    {
        lat = lat;
    }

    public Double getLat()
    {
        return lat;
    }

    public LatLng getLatLng()
    {
        return new LatLng(lat, lng);
    }

    public void setPosition(LatLng position)
    {
        lng = position.longitude;
        lat = position.latitude;
    }
}
