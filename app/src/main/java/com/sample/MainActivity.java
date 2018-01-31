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
                .onCreateUpdate(i -> i + 1)
                .onCreateInvoke(i -> Log.e("LiteCycle", "onCreate() invoked " + i))
                .onStartUpdate(i -> i + 1)
                .onStartInvoke(i -> Log.e("LiteCycle", "onStart() invoked " + i))
                .onResumeUpdate(i -> i + 1)
                .onResumeInvoke(i -> Log.e("LiteCycle", "onResume() invoked " + i))
                .onPauseUpdate(i -> i + 1)
                .onPauseInvoke(i -> Log.e("LiteCycle", "onPause() invoked " + i))
                .onStopUpdate(i -> i + 1)
                .onStartInvoke(i -> Log.e("LiteCycle", "onStop() invoked " + i))
                .onDestroyUpdate(i -> 10)
                .onDestroyInvoke(i -> Log.e("LiteCycle", "onDestroy() invoked " + i))
                .onFinishingUpdate(i -> null)
                .onFinishingInvoke(i -> Log.e("LiteCycle", "isFinishing() invoked " + i))
                .observe();


        LiteCycle.defer(() -> getIntent().getBooleanExtra("EXTRA", false))
                .forLifeCycle(this)
                .onCreateInvoke(extra -> Log.e("LiteCycle", "extra boolean : " + extra))
                .observe();


    }


}
