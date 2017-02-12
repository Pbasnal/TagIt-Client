package com.basnal.tagit.models;

import android.location.Location;

/**
 * Created by Basnal on 28-01-2017.
 */

public class HotspotQueryModel
{
    String name;
    Position location;
    String portal;
    String tags;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Position getLocation()
    {
        return location;
    }

    public void setLocation(Position location)
    {
        this.location = location;
    }

    public void setLocation(Location location)
    {
        this.location = new Position();
        this.location.setLat(location.getLatitude());
        this.location.setLng(location.getLongitude());
    }

    public String getPortal()
    {
        return portal;
    }

    public void setPortal(String portal)
    {
        this.portal = portal;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getImages()
    {
        return images;
    }

    public void setImages(String images)
    {
        this.images = images;
    }

    String comments;
    String images;
}
