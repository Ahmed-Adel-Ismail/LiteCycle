package com.android;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * the abstract life-cycle builder
 * <p>
 * Created by Ahmed Adel Ismail on 1/29/2018.
 */

@SuppressLint("CheckResult")
@SuppressWarnings("unchecked")
public abstract class LiteObserverBuilder<T> {

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
    public final LiteObserverBuilder<T> onCreateInvoke(Consumer<T> action) {
        this.onCreate.add(action);
        return this;
    }

    public final LiteObserverBuilder<T> onCreateUpdate(Function<T, T> action) {
        this.onCreate.add(action);
        return this;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onStart()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onStartInvoke(Consumer<T> action) {
        this.onStart.add(action);
        return this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onCreate()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onStartUpdate(Function<T, T> action) {
        this.onStart.add(action);
        return this;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onResume()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onResumeInvoke(Consumer<T> action) {
        this.onResume.add(action);
        return this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onResume()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onResumeUpdate(Function<T, T> action) {
        this.onResume.add(action);
        return this;
    }


    /**
     * add a {@link Consumer} that will be invoked in {@code onPause()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onPauseInvoke(Consumer<T> action) {
        this.onPause.add(action);
        return this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onPause()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onPauseUpdate(Function<T, T> action) {
        this.onPause.add(action);
        return this;
    }


    /**
     * add a {@link Consumer} that will be invoked in {@code onStop()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onStopInvoke(Consumer<T> action) {
        this.onStop.add(action);
        return this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onStop()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onStopUpdate(Function<T, T> action) {
        this.onStop.add(action);
        return this;
    }


    /**
     * add a {@link Consumer} that will be invoked in {@code onDestroy()}
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onDestroyInvoke(Consumer<T> action) {
        this.onDestroy.add(action);
        return this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onDestroy()} is invoked
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onDestroyUpdate(Function<T, T> action) {
        this.onDestroy.add(action);
        return this;
    }

    /**
     * add a {@link Consumer} that will be invoked in {@code onDestroy()} in-case that the
     * {@link LifecycleOwner} is finishing (not rotating)
     *
     * @param action a {@link Consumer} to be invoked
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onFinishingInvoke(Consumer<T> action) {
        this.onFinishing.add(action);
        return this;
    }

    /**
     * add a {@link Function} that will update the stored item, it will be invoked when
     * {@code onDestroy()} is invoked, in-case that the
     * {@link LifecycleOwner} is finishing (not rotating)
     *
     * @param action a {@link Function} to be invoked and it's result will update the stored item
     * @return {@code this} instance for chaining
     */
    public final LiteObserverBuilder<T> onFinishingUpdate(Function<T, T> action) {
        this.onFinishing.add(action);
        return this;
    }

    /**
     * start observation on life-Cycle events
     */
    public final void observe() {
        buildObserver().observe();
    }

    abstract LiteObserver<T> buildObserver();

    /**
     * observe on the life-Cycle events
     *
     * @param observableType the type of the {@link Subject} that listens to the updates of
     *                       the value from the life-cycle events, the default one used by
     *                       {@link #observe()} is {@link BehaviorSubject}
     * @return the {@link Observable} which will be notified when the value is updated
     */
    public final Observable<T> observe(@NonNull Subject<T> observableType) {
        return buildObserver().observe(observableType);
    }
}
