package com.basnal.tagit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;

import com.basnal.tagit.tagitapi.TagItData;

public class AppLoaderActivity extends FragmentActivity
{
    private static final int SPLASH_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_loader);


        //TODO: also check connection to server
        if(!TagItData.getInstance().checkPermission(getApplicationContext()))
        {
            ActivityCompat.requestPermissions(AppLoaderActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        else
            startApp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        Boolean allPermissionsGranted = true;

        switch (requestCode)
        {
            case 1:
                if(grantResults.length < 0)
                    finish();
                for(int i = 0; i < grantResults.length; i++)
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED)
                    {
                        allPermissionsGranted = false;
                        break;
                    }
                break;
        }

        if(allPermissionsGranted)
            startApp();
        else
        {
            finish();
            System.exit(1);
        }
    }

    private void startApp()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(AppLoaderActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);
    }
}
