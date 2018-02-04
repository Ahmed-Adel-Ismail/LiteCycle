package com.sample;

import android.location.LocationListener;
import android.os.Bundle;

public abstract class LocationRetriever implements LocationListener{




    public void start(){
        // start listening to location changes
    }


    public void stop(){
        // stop listening to location changes
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
