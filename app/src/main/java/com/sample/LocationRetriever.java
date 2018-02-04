package com.sample;

import android.location.Location;
import android.util.Log;

public abstract class LocationRetriever {


    public void start() {
        // start listening to location changes
        Log.e("LocationRetriever","start() invoked");
    }


    public void stop() {
        // stop listening to location changes
        Log.e("LocationRetriever","stop() invoked");
    }

    public abstract void onLocationChanged(Location location);


}
