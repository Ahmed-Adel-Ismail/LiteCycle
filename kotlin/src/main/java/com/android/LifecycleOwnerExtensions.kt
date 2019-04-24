package com.android

import android.app.Activity
import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.support.v4.app.Fragment

fun <T : LifecycleOwner> T.withOnCreate(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        block(this@withOnCreate)
    }
})


fun <T : LifecycleOwner> T.withOnStart(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        block(this@withOnStart)
    }
})


fun <T : LifecycleOwner> T.withOnResume(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        block(this@withOnResume)
    }
})


fun <T : LifecycleOwner> T.withOnPause(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        block(this@withOnPause)
    }
})


fun <T : LifecycleOwner> T.withOnStop(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        block(this@withOnStop)
    }
})


fun <T : LifecycleOwner> T.withOnDestroy(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        block(this@withOnDestroy)
    }
})


fun <T : LifecycleOwner> T.withOnFinishing(block: T.() -> Unit) = lifecycle.addObserver(object : DefaultLifecycleObserver {
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)

        fun isFinishingFragment() = this@withOnFinishing is Fragment && this@withOnFinishing.activity?.isFinishing == true

        fun isFinishingActivity() = this@withOnFinishing is Activity && this@withOnFinishing.isFinishing

        fun isFinishingDestroyable() = this@withOnFinishing is FinishingOwner && this@withOnFinishing.isFinishing()

        if (isFinishingActivity() || isFinishingFragment() || isFinishingDestroyable()) {
            block(this@withOnFinishing)
        }
    }
})


interface FinishingOwner : LifecycleOwner {
    fun isFinishing(): Boolean
}