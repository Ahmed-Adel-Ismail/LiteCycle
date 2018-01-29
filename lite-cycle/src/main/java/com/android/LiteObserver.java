package com.android;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * the {@link LifecycleObserver} that will invoke the required functions in the life-cycle events
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */
abstract class LiteObserver<T> implements LifecycleObserver {

    private final WeakReference<LifecycleOwner> owner;
    private final List<Object> onCreate;
    private final List<Object> onStart;
    private final List<Object> onResume;
    private final List<Object> onPause;
    private final List<Object> onStop;
    private final List<Object> onDestroy;

    LiteObserver(LiteObserverBuilder<T, ?, ?> builder) {
        owner = new WeakReference<>(builder.owner);
        onCreate = builder.onCreate;
        onStart = builder.onStart;
        onResume = builder.onResume;
        onPause = builder.onPause;
        onStop = builder.onStop;
        onDestroy = builder.onDestroy;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() throws Exception {
        for (Object action : onCreate) {
            invoke(action);
        }
    }


    @SuppressWarnings("unchecked")
    private void invoke(Object action) throws Exception {
        if (action instanceof Consumer) {
            ((Consumer<T>) action).accept(getItem());
        } else if (action instanceof Function) {
            setItem(((Function<T, T>) action).apply(getItem()));
        }
    }

    @NonNull
    abstract T getItem();

    abstract void setItem(@NonNull T item);

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() throws Exception {
        for (Object action : onStart) {
            invoke(action);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() throws Exception {
        for (Object action : onResume) {
            invoke(action);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() throws Exception {
        for (Object action : onPause) {
            invoke(action);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() throws Exception {
        for (Object action : onStop) {
            invoke(action);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() throws Exception {
        for (Object action : onDestroy) {
            invoke(action);
        }

    }

    LifeCycleObservation observe() {
        return new LifeCycleObservation(owner.get(), this);
    }


}
