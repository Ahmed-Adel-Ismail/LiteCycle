package com.android;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.chaining.Lazy;

import java.util.concurrent.Callable;


/**
 * an Observer that starts with an item that will be initialized upon it's first call
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */
class LazyLiteObserver<T> extends LiteObserver<T> {

    private Lazy<T> item;

    private LazyLiteObserver(Builder<T> builder) {
        super(builder);
        item = Lazy.defer(builder.itemInitializer);
    }

    @Override
    T getItem() {
        return item.call();
    }

    @Override
    void setItem(final T item) {
        this.item = Lazy.defer(itemCallable(item));
        this.item.call();
    }

    @NonNull
    private Callable<T> itemCallable(final T item) {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                return item;
            }
        };
    }

    static final class Builder<T> extends LiteObserverBuilder<T> {

        private Callable<T> itemInitializer;

        Builder(LifecycleOwner owner, Callable<T> itemInitializer) {
            super(owner);
            this.itemInitializer = itemInitializer;
        }

        LiteObserver<T> buildObserver() {
            return new LazyLiteObserver<>(this);
        }
    }
}
