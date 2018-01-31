package com.android;

import android.arch.lifecycle.LifecycleOwner;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * the abstract life-cycle builder
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */

@SuppressWarnings("unchecked")
public abstract class LiteObserverBuilder<
        T,
        B extends LiteObserverBuilder<T, B, R>,
        R extends LiteObserver<T>> {

    final LifecycleOwner owner;
    final List<Object> onCreate = new LinkedList<>();
    final List<Object> onStart = new LinkedList<>();
    final List<Object> onResume = new LinkedList<>();
    final List<Object> onPause = new LinkedList<>();
    final List<Object> onStop = new LinkedList<>();
    final List<Object> onDestroy = new LinkedList<>();
    final List<Object> onFinishing = new LinkedList<>();


    LiteObserverBuilder(LifecycleOwner owner) {
        this.owner = owner;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onCreate()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onCreateInvoke(Consumer<T> action) {
        this.onCreate.add(action);
        return (B) this;
    }

    public final B onCreateUpdate(Function<T, T> action) {
        this.onCreate.add(action);
        return (B) this;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onStart()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onStartInvoke(Consumer<T> action) {
        this.onStart.add(action);
        return (B) this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onCreate()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final B onStartUpdate(Function<T, T> action) {
        this.onStart.add(action);
        return (B) this;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onResume()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onResumeInvoke(Consumer<T> action) {
        this.onResume.add(action);
        return (B) this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onResume()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final B onResumeUpdate(Function<T, T> action) {
        this.onResume.add(action);
        return (B) this;
    }


    /**
     * add a {@link Consumer} that will be invoked in {@code onPause()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onPauseInvoke(Consumer<T> action) {
        this.onPause.add(action);
        return (B) this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onPause()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final B onPauseUpdate(Function<T, T> action) {
        this.onPause.add(action);
        return (B) this;
    }


    /**
     * add a {@link Consumer} that will be invoked in {@code onStop()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onStopInvoke(Consumer<T> action) {
        this.onStop.add(action);
        return (B) this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onStop()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final B onStopUpdate(Function<T, T> action) {
        this.onStop.add(action);
        return (B) this;
    }


    /**
     * add a {@link Consumer} that will be invoked in {@code onDestroy()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onDestroyInvoke(Consumer<T> action) {
        this.onDestroy.add(action);
        return (B) this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onDestroy()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final B onDestroyUpdate(Function<T, T> action) {
        this.onDestroy.add(action);
        return (B) this;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onDestroy()} in-case that the
     * {@link LifecycleOwner} is finishing (not rotating)
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final B onFinishingInvoke(Consumer<T> action) {
        this.onFinishing.add(action);
        return (B) this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onDestroy()} is invoked, in-case that the
     * {@link LifecycleOwner} is finishing (not rotating)
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final B onFinishingUpdate(Function<T, T> action) {
        this.onFinishing.add(action);
        return (B) this;
    }

    /**
     * build the {@link LifeCycleObservation} which will cause all the passed
     * {@link Consumer Consumers} and {@link Function Functions} to be executed in the
     * life-cycle methods
     *
     * @return a {@link LifeCycleObservation}
     */
    public final LifeCycleObservation observe() {
        return buildObserver().observe();
    }

    abstract R buildObserver();
}
