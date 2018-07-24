package com.sample;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.LiteCycle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImplementer(this);

        LiteCycle.with(0)
                .forLifeCycle(this)
                .onCreateUpdate(i -> 1)
                .onStartUpdate(i -> 2)
                .onResumeUpdate(i -> 3)
                .onPauseUpdate(i -> 4)
                .onStopUpdate(i -> 5)
                .onDestroyUpdate(i -> 6)
                .observe()
                .subscribe(i -> Log.e("MainActivity", "non-null value updated : " + i));

        LiteCycle.with(null)
                .forLifeCycle(this)
                .onCreateUpdate(i -> 1)
                .onStartUpdate(i -> 2)
                .onResumeUpdate(i -> 3)
                .onPauseUpdate(i -> 4)
                .onStopUpdate(i -> 5)
                .onDestroyUpdate(i -> 6)
                .observe()
                .subscribe(i -> Log.e("MainActivity", "null value updated : " + i));


        LiteCycle.defer(this::lazyInitialization)
                .forLifeCycle(this)
                .onResumeUpdate(i -> 3)
                .onPauseUpdate(i -> 4)
                .observe()
                .subscribe(i -> Log.e("MainActivity", "lazy value updated : " + i));

        LiteCycle.defer(null)
                .forLifeCycle(this)
                .onResumeUpdate(i -> 3)
                .onPauseUpdate(i -> 4)
                .observe()
                .subscribe(i -> Log.e("MainActivity", "defer(null) value updated : " + i));


        LiteCycle.with(0)
                .forLifeCycle(this)
                .onResumeUpdate(i -> 30)
                .onPauseUpdate(i -> 40)
                .observe(PublishSubject.create())
                .subscribe(i -> Log.e("MainActivity", "PublishSubject value updated : " + i));


        LiteCycle.with(intervalDisposable())
                .forLifeCycle(this)
                .onDestroyInvoke(Disposable::dispose)
                .build();

    }

    @NonNull
    private Integer lazyInitialization() {
        Log.e("MainActivity", "lazy initialization triggered");
        return 0;
    }

    @Override
    public void updateLocationOnMap(Location location) {
        Log.e("MainActivity","updateLocationOnMap() invoked");
    }

    Disposable intervalDisposable(){
        return Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(this::doSomething);
    }

    void doSomething(Long tickCount){
        Log.e("MainActivity","doSomething("+tickCount+") invoked");
    }
}
