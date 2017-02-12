package com.basnal.tagit.tagitapi;

import com.basnal.tagit.Configuration;
import com.basnal.tagit.models.HotspotQueryModel;
import com.basnal.tagit.models.MapMarker;
import com.basnal.tagit.models.SearchQueryModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Basnal on 21-12-2016.
 */

public class TagItClient
{
    private static Retrofit retrofit = null;
    private static final String TAG = "TagItClient";

    // making it singleton
    private static TagItClient client;
    private TagItClient(){}

    public static TagItClient getInstance()
    {
        if(client == null)
        {
            client = new TagItClient();
            if (retrofit==null)
            {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Configuration.API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return client;
    }

    private static OkHttpClient get_HTTPClient(final Map<String, String> headers)
    {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(
            new Interceptor()
            {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException
                {
                    Request original = chain.request();

                    // Request customization: add request headers

                    Request.Builder requestBuilder = original.newBuilder(); // <-- this is the important line

                    for (Map.Entry<String, String> pairs : headers.entrySet())
                    {
                        if (pairs.getValue() != null)
                        {
                            requestBuilder.header(pairs.getKey(), pairs.getValue());
                        }
                    }

                    requestBuilder.method(original.method(), original.body());
                    Request request = requestBuilder.build();

                    return chain.proceed(request);

                }
            });

        return httpClient.build();
    }

    public void getTags(SearchQueryModel query, Callback callback)
    {
        if(retrofit == null)
            return;

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<List<MapMarker>> call = service.getHotspots(Configuration.AUTH_TOKEN , query);

        call.enqueue(callback);

    }

    public void storeHotpspot(HotspotQueryModel query, Callback callback)
    {
        if(retrofit == null)
            return;

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<List<MapMarker>> call = service.storeHotspot(Configuration.AUTH_TOKEN , query);

        call.enqueue(callback);

    }
}





















