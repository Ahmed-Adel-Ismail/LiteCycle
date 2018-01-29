package com.sample;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.LiteCycle;

public class MainActivity extends AppCompatActivity {

    {
        LiteCycle.with(R.layout.activity_main)
                .forLifeCycle(this)
                .onCreateInvoke(this::setContentView)
                .observe();


        LiteCycle.with(10)
                .forLifeCycle(this)
                .onCreateInvoke(i -> Log.e("LiteCycle", "onCreate invoked " + i))
                .onCreateUpdate(i -> i * 10)
                .onResumeInvoke(i -> Log.e("LiteCycle", "onResume invoked " + i))
                .onResumeUpdate(i -> i + 1)
                .onPauseInvoke(i -> Log.e("LiteCycle", "onPause invoked " + i))
                .observe();



        LiteCycle.byLazy(() -> getIntent().getBooleanExtra("EXTRA", false))
                .forLifeCycle(this)
                .onCreateInvoke(extra -> Log.e("LiteCycle", "extra boolean : " + extra))
                .observe();


    }


}
