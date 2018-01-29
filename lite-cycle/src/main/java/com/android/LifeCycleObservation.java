package com.android;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

/**
 * an Observation state to the life cycle
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */
public class LifeCycleObservation {

    LifeCycleObservation(LifecycleOwner owner, LifecycleObserver observer) {
        owner.getLifecycle().addObserver(observer);
    }
}
