package com.basnal.tagit;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.basnal.tagit.tagitapi.TagItData;
import com.basnal.tagit.tagitapi.TagItMap;
import com.basnal.tagit.tagitapi.TagItSearchListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private SearchView search;
    private ImageView btnTagIt, btnNotifications, btnRequests;

    private static final String TAG = "TagItMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        initializeActivity();

        TagItData.getInstance().configure(this.getApplicationContext(), getLayoutInflater());

        if(!TagItData.getInstance().checkPermission(getApplicationContext()))
        {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

    }

    private void initializeActivity()
    {
        search = (SearchView) this.findViewById(R.id.search);

        search.setOnQueryTextListener(new TagItSearchListener(getBaseContext()));

        btnTagIt = (ImageView) this.findViewById(R.id.btntagit);
        btnNotifications = (ImageView) this.findViewById(R.id.btnnotifications);
        btnRequests = (ImageView) this.findViewById(R.id.btnrequests);

        btnTagIt.setOnClickListener(tagItBtnListener);
        btnRequests.setOnClickListener(requestsBtnListener);
        btnNotifications.setOnClickListener(notificationBtnListener);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        //TagItLocationManager.getInstance().initializeTagItLocationManager());
        TagItMap.getInstance().initializeTagItMap(getApplicationContext(), googleMap);
    }


    private View.OnClickListener tagItBtnListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MapsActivity.this, CreateTagActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener requestsBtnListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MapsActivity.this, TagRequestsActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener notificationBtnListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
        }
    };

}
