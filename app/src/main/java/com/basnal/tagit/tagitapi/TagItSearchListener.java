package com.basnal.tagit.tagitapi;

import android.content.Context;
import android.util.Log;
import android.widget.SearchView;

import com.basnal.tagit.models.Bounds;
import com.basnal.tagit.models.MapMarker;
import com.basnal.tagit.models.SearchQueryModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Basnal on 20-12-2016.
 */

public class TagItSearchListener implements SearchView.OnQueryTextListener
{
    private Context mainContext;
    private static final String TAG = "TagItSearchListener";
    private final TagItData tagItData = TagItData.getInstance();
    public TagItSearchListener(Context mainContext)
    {
        this.mainContext = mainContext;
    }

    List<Polyline> polylines = new ArrayList<Polyline>();
    List<Marker> markers = new ArrayList<Marker>();

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        SearchQueryModel queryModel = new SearchQueryModel();

        queryModel.setQuery(query);
        queryModel.setCenter(tagItData.mMap.getCameraPosition().target);
        queryModel.setBounds(tagItData.mMap.getProjection().getVisibleRegion().latLngBounds);


        drawOnMap(queryModel.getBounds());

        TagItClient client = TagItClient.getInstance();

        Callback<List<MapMarker>> callback = new Callback<List<MapMarker>>(){
            @Override
            public void onResponse(Call<List<MapMarker>> call, retrofit2.Response<List<MapMarker>> response)
            {
                try
                {
                    clearMap();
                    for (MapMarker marker: response.body())
                    {
                        LatLng latLng = marker.getMarkerPosition().getLatLng();
                        MarkerOptions markerOpt = new MarkerOptions();
                        markerOpt.position(latLng);
                        markers.add(tagItData.mMap.addMarker(markerOpt));
                    }

                    Log.v(TAG, "Call Successful");
                }
                catch(Exception ex)
                {
                    throw ex;
                }
            }

            @Override
            public void onFailure(Call<List<MapMarker>> call, Throwable t)
            {
                Log.v(TAG, "Call Failed");
            }
        };

        client.getTags(queryModel, callback);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        return false;
    }

    public void drawOnMap(Bounds bounds)
    {
        LatLng a = new LatLng(bounds.NEPoint.getLat(), bounds.NEPoint.getLng());
        LatLng b = new LatLng(bounds.SWPoint.getLat(), bounds.NEPoint.getLng());
        LatLng c = new LatLng(bounds.SWPoint.getLat(), bounds.SWPoint.getLng());
        LatLng d = new LatLng(bounds.NEPoint.getLat(), bounds.SWPoint.getLng());

        PolylineOptions rectOptions = new PolylineOptions()
                .add(a)
                .add(b)  // North of the previous point, but at the same longitude
                .add(c)  // Same latitude, and 30km to the west
                .add(d)  // Same longitude, and 16km to the south
                .add(a); // Closes the polyline.

        // Get back the mutable Polyline
        Polyline polyline = TagItData.getInstance().mMap.addPolyline(rectOptions);
        polylines.add(polyline);
    }


    private void clearMap()
    {
        for (Marker marker: markers)
        {
            marker.remove();
        }
        for (Polyline polyline: polylines)
        {
            polyline.remove();
        }
    }
}
