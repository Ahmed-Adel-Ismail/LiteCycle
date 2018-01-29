package com.android;

import android.arch.lifecycle.LifecycleOwner;

/**
 * an Observer that starts with an already initialized item
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */
class InstantLiteObserver<T> extends LiteObserver<T> {

    private T item;

    InstantLiteObserver(Builder<T> builder) {
        super(builder);
        this.item = builder.item;
    }

    @Override
    T getItem() {
        return item;
    }

    @Override
    void setItem(T item) {
        this.item = item;
    }

    static final class Builder<T> extends LiteObserverBuilder<T, Builder<T>, InstantLiteObserver<T>> {

        private T item;

        Builder(LifecycleOwner owner, T item) {
            super(owner);
            this.item = item;
        }

        InstantLiteObserver<T> buildObserver() {
            return new InstantLiteObserver<>(this);
        }
    }
}
