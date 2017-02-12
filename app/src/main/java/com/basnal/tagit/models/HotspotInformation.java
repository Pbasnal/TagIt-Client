package com.basnal.tagit.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basnal on 21-12-2016.
 */

public class HotspotInformation implements Parcelable
{
    private ArrayList<String> tags;
    private ArrayList<String> comments;
    private ArrayList<String> commends;
    private ArrayList<String> reports;

    public HotspotInformation()
    {}



    //public List<Image> Images;    // find out how to do this :P
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private String name;

    public List<String> getComments()
    {
        return comments;
    }

    public void setComments(List<String> comments)
    {
        comments = comments;
    }
    public void setComments(String comments)
    {
        if(this.comments == null)
            this.comments = new ArrayList<String>();
        this.comments.add(comments);
    }

    public List<String> getCommends()
    {
        return commends;
    }

    public void setCommends(List<String> commends)
    {
        commends = commends;
    }

    public List<String> getReports()
    {
        return reports;
    }

    public void setReports(List<String> reports)
    {
        reports = reports;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        tags = tags;
    }

    @Override
    public int describeContents()
    {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeStringList(tags);
        dest.writeStringList(comments);
        dest.writeStringList(commends);
        dest.writeStringList(reports);
        dest.writeString(name);
    }

    protected HotspotInformation(Parcel in)
    {
        tags = in.createStringArrayList();
        comments = in.createStringArrayList();
        commends = in.createStringArrayList();
        reports = in.createStringArrayList();
        name = in.readString();
    }

    public static final Creator<HotspotInformation> CREATOR = new Creator<HotspotInformation>()
    {
        @Override
        public HotspotInformation createFromParcel(Parcel in)
        {
            return new HotspotInformation(in);
        }

        @Override
        public HotspotInformation[] newArray(int size)
        {
            return new HotspotInformation[size];
        }
    };

}
