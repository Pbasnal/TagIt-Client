package com.basnal.tagit.tagitapi;

import com.basnal.tagit.models.HotspotQueryModel;
import com.basnal.tagit.models.MapMarker;
import com.basnal.tagit.models.SearchQueryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Basnal on 21-12-2016.
 */

public interface ApiInterface
{
    // @GET("part of url goes here.. it tells the endpoint which retrofit will hit")
    @POST("tag/search")
    Call<List<MapMarker>> getHotspots(@Header("Authorization") String auth1 , @Body SearchQueryModel query);

    @POST("tag/store")
    Call<List<MapMarker>> storeHotspot(@Header("Authorization") String auth1 , @Body HotspotQueryModel query);
}
