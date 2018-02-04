package com.sample;

import android.arch.lifecycle.LifecycleOwner;
import android.location.Location;

/**
 * Created by Ahmed Adel Ismail on 2/4/2018.
 */

public interface MainView extends LifecycleOwner{

    void updateLocationOnMap(Location location);

}
