package com.sample

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.withOnCreate
import com.android.withOnStart

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        withOnCreate { Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show() }
                .observe(this,Observer{ })
        withOnStart { Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show() }

    }
}
