package com.android;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * the {@link LifecycleObserver} that will invoke the required functions in the life-cycle events
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */
public abstract class LiteObserver<T> implements LifecycleObserver {

    private Subject<T> subject;
    private final LifecycleOwner owner;
    private final List<Object> onCreate;
    private final List<Object> onStart;
    private final List<Object> onResume;
    private final List<Object> onPause;
    private final List<Object> onStop;
    private final List<Object> onDestroy;
    private final List<Object> onFinishing;

    LiteObserver(LiteObserverBuilder<T> builder) {
        owner = builder.owner;
        onCreate = builder.onCreate;
        onStart = builder.onStart;
        onResume = builder.onResume;
        onPause = builder.onPause;
        onStop = builder.onStop;
        onDestroy = builder.onDestroy;
        onFinishing = builder.onFinishing;
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
            updateItemAndNotifySubject((Function<T, T>) action);
        }
    }

    @NonNull
    abstract T getItem();

    private void updateItemAndNotifySubject(Function<T, T> action) {
        try {
            setItem(action.apply(getItem()));
            if (getItem() != null) subject.onNext(getItem());
        } catch (Throwable e) {
            subject.onError(e);
        }
    }

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

        if (isActivityFinishing() || isFragmentFinishing()) {
            for (Object action : onFinishing) {
                invoke(action);
            }
        }

        subject.onComplete();
    }

    private boolean isActivityFinishing() {
        return owner instanceof Activity && ((Activity) owner).isFinishing();
    }

    private boolean isFragmentFinishing() {
        return owner instanceof Fragment &&
                (((Fragment) owner).getActivity() == null
                        || ((Fragment) owner).getActivity().isFinishing());
    }

    Observable<T> observe() {
        return observe(BehaviorSubject.<T>create());
    }

    Observable<T> observe(@NonNull Subject<T> observableType) {
        subject = observableType;
        initializeSubject(subject);
        owner.getLifecycle().addObserver(this);
        return subject;
    }

    /**
     * template method for initializing the {@link Subject} by the subclasses
     *
     * @param subject the {@link Subject} that monitors the item changes
     */
    void initializeSubject(Subject<T> subject) {
        // do nothing
    }


}
