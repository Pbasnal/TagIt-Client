package com.basnal.tagit.models;

/**
 * Created by Basnal on 21-12-2016.
 */

public class MapMarker
{
    private String name;
    private HotspotInformation info;
    private Position location;
    private String portal;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        name = name;
    }

    public HotspotInformation getInfo()
    {
        return info;
    }

    public void setInfo(HotspotInformation info)
    {
        info = info;
    }

    public String getPortal()
    {
        return portal;
    }

    public void setPortal(String portal)
    {
        portal = portal;
    }

    public Position getMarkerPosition()
    {
        return location;
    }

    public void setMarkerPosition(Position markerPosition)
    {
        this.location = markerPosition;
    }
}
