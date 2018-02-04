package com.sample;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;

import com.android.LiteCycle;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Ahmed Adel Ismail on 2/4/2018.
 */

public class MVVMViewModel extends ViewModel {

    private BehaviorSubject<Integer> methodIndexer;

    public void initialize(LifecycleOwner lifecycleOwner) {
        methodIndexer = createMethodIndexer(lifecycleOwner);
    }

    private BehaviorSubject<Integer> createMethodIndexer(LifecycleOwner lifecycleOwner) {
        return LiteCycle.with(0)
                .forLifeCycle(lifecycleOwner)
                .onCreateUpdate(i -> 1)
                .onStartUpdate(i -> 2)
                .onResumeUpdate(i -> 3)
                .onPauseUpdate(i -> 4)
                .onStopUpdate(i -> 5)
                .onDestroyUpdate(i -> 6)
                .observe();
    }

    public BehaviorSubject<Integer> getMethodIndexer() {
        return methodIndexer;
    }
}
