package com.sample;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;

import com.android.LiteCycle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Ahmed Adel Ismail on 2/4/2018.
 */

public class MVVMViewModel extends ViewModel {

    private Observable<Integer> methodIndexer;

    public void initialize(LifecycleOwner lifecycleOwner) {
        methodIndexer = createMethodIndexer(lifecycleOwner);
    }

    private Observable<Integer> createMethodIndexer(LifecycleOwner lifecycleOwner) {
        return LiteCycle.with(0)
                .forLifeCycle(lifecycleOwner)
                .onCreateUpdate(i -> 1)
                .onStartUpdate(i -> 2)
                .onResumeUpdate(i -> 3)
                .onPauseUpdate(i -> 4)
                .onStopUpdate(i -> 5)
                .onDestroyUpdate(i -> 6)
                .observe(BehaviorSubject.create());
    }

    public Observable<Integer> getMethodIndexer() {
        return methodIndexer;
    }
}
