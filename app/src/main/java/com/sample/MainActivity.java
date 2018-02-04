package com.sample;

import android.arch.lifecycle.ViewModelProviders;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.LiteCycle;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImplementer(this);

        MVVMViewModel viewModel = ViewModelProviders.of(this).get(MVVMViewModel.class);
        viewModel.initialize(this);

        LiteCycle.with(logMethodIndex(viewModel))
                .forLifeCycle(this)
                .onDestroyInvoke(this::dispose)
                .observe();
    }

    @NonNull
    private Disposable logMethodIndex(MVVMViewModel viewModel) {
        return viewModel.getMethodIndexer()
                .subscribe(i -> Log.e("MainActivity", "method index :" + i));
    }

    private void dispose(Disposable disposable) {
        if (!disposable.isDisposed()) disposable.dispose();
    }

    @Override
    public void updateLocationOnMap(Location location) {
        // update location on map
    }
}
