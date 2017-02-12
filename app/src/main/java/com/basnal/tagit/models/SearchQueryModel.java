package com.basnal.tagit.models;


/**
 * Created by Basnal on 05-01-2017.
 */

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class SearchQueryModel
{
    String query;
    Position center = new Position();
    Bounds bounds = new Bounds();

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public Bounds getBounds()
    {
        return bounds;
    }

    public void setBounds(LatLngBounds bounds)
    {
        this.bounds.NEPoint.setPosition(bounds.northeast);
        this.bounds.SWPoint.setPosition(bounds.southwest);
    }

    public Position getCenter()
    {
        return center;
    }

    public void setCenter(LatLng center)
    {
        this.center.setLat(center.latitude);
        this.center.setLng(center.longitude);
    }
}