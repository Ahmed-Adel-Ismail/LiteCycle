package com.android;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

import java.util.concurrent.Callable;

/**
 * a class that holds the Object which will be affected by the {@link LifecycleOwner}
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */
public class LiteCycle implements LifecycleObserver {

    /**
     * initialize the stored item when it is first accessed in the life-cycle
     *
     * @param itemInitializer the {@link Callable} that will lazy-initialize the item
     * @param <T>             the expected item type
     * @return a {@link LiteObserverFactory} to create the related {@link LiteObserver}
     */
    public static <T> LiteObserverFactory<T> byLazy(Callable<T> itemInitializer) {
        return new LiteObserverFactory<>(itemInitializer);
    }

    /**
     * initialize the {@link LiteCycle} with the passed item
     *
     * @param item the initial item
     * @param <T>  the item type
     * @return a {@link LiteObserverFactory} to create the related {@link LiteObserver}
     */
    public static <T> LiteObserverFactory<T> with(T item) {
        return new LiteObserverFactory<>(item);
    }

}
