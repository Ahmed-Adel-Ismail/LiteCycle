package com.android;

import android.arch.lifecycle.LifecycleOwner;

import java.util.concurrent.Callable;

/**
 * a LiteObserverFactory that handles initializing the item and life-cycle events
 *
 * @param <T> the item type that will be held by the {@link LiteCycle}
 */
public class LiteObserverFactory<T> {

    private final T item;
    private final Callable<T> itemInitializer;

    LiteObserverFactory(T item) {
        this.item = item;
        this.itemInitializer = null;
    }

    LiteObserverFactory(Callable<T> itemInitializer) {
        this.itemInitializer = itemInitializer;
        this.item = null;
    }

    /**
     * attach the {@link LiteCycle} to a {@link LifecycleOwner}
     *
     * @param lifecycleOwner the {@link LifecycleOwner}
     * @return a {@link LiteObserverFactory} to handle completing the operation
     */
    public LiteObserverBuilder<T> forLifeCycle(LifecycleOwner lifecycleOwner) {
        if (itemInitializer != null) {
            return new LazyLiteObserver.Builder<>(lifecycleOwner, itemInitializer);
        } else {
            return new InstantLiteObserver.Builder<>(lifecycleOwner, item);
        }
    }


}
