package com.basnal.tagit.tagitapi;

import android.util.Log;

import com.basnal.tagit.models.HotspotQueryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Basnal on 11-02-2017.
 */

public class TagItRequests
{
    protected final HotspotQueryModel hotspotQueryModel;
    protected RequestState requestState;

    public HotspotQueryModel getHotspotQueryModel()
    {
        return hotspotQueryModel;
    }

    public RequestState getRequestState()
    {
        return requestState;
    }

    public void setRequestState(RequestState requestState)
    {
        this.requestState = requestState;
    }

    public TagItRequests(HotspotQueryModel hotspotQueryModel)
    {
        this.hotspotQueryModel = hotspotQueryModel;
        requestState = RequestState.Initiated;
    }

    public void makeRequest()
    {
        TagItData.getInstance().addRequest(this);
        TagItClient.getInstance().storeHotpspot(hotspotQueryModel, callback);
    }


    private Callback callback = new Callback()
    {
        @Override
        public void onResponse(Call call, Response response)
        {
            Log.e("Create hotspot", "created");
            requestState = RequestState.Success;
        }

        @Override
        public void onFailure(Call call, Throwable t)
        {
            Log.e("Create hotspot", "failed");
            requestState = RequestState.Failed;
        }
    };

    public enum RequestState
    {
        Initiated,
        Success,
        Failed
    }
}
