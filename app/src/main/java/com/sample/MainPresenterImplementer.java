package com.sample;

import android.location.Location;

import com.android.LiteCycle;

/**
 * Created by Ahmed Adel Ismail on 2/4/2018.
 */

public class MainPresenterImplementer implements MainPresenter {

    MainPresenterImplementer(MainView view) {
        LiteCycle.with(locationRetriever(view))
                .forLifeCycle(view)
                .onStartInvoke(locationRetriever -> locationRetriever.start())
                .onStopInvoke(locationRetriever -> locationRetriever.stop())
                .observe();
    }

    private LocationRetriever locationRetriever(MainView view) {
        return new LocationRetriever() {
            @Override
            public void onLocationChanged(Location location) {
                view.updateLocationOnMap(location);
            }
        };
    }
}
