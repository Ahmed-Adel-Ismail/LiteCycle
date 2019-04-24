package com.android

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import junit.framework.Assert.assertTrue
import org.junit.Test

class LifecycleOwnerExtensionsKtTest {

    @Test
    fun withOnCreateThenInvokeInLifecycleEvent() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner()

        lifecycleOwner.withOnCreate { invoked = true }
        lifecycleOwner.create()

        assertTrue(invoked)
    }

    @Test
    fun withOnStartThenInvokeInLifecycleEvent() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner()

        lifecycleOwner.withOnStart { invoked = true }
        lifecycleOwner.create()
        lifecycleOwner.start()

        assertTrue(invoked)
    }

    @Test
    fun withOnResumeThenInvokeInLifecycleEvent() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner()

        lifecycleOwner.withOnResume { invoked = true }
        lifecycleOwner.create()
        lifecycleOwner.start()
        lifecycleOwner.resume()

        assertTrue(invoked)
    }

    @Test
    fun withOnPauseThenInvokeInLifecycleEvent() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner()

        lifecycleOwner.withOnPause { invoked = true }
        lifecycleOwner.create()
        lifecycleOwner.start()
        lifecycleOwner.resume()
        lifecycleOwner.pause()

        assertTrue(invoked)
    }

    @Test
    fun withOnStopThenInvokeInLifecycleEvent() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner()

        lifecycleOwner.withOnStop { invoked = true }
        lifecycleOwner.create()
        lifecycleOwner.start()
        lifecycleOwner.resume()
        lifecycleOwner.pause()
        lifecycleOwner.stop()

        assertTrue(invoked)
    }

    @Test
    fun withOnDestroyThenInvokeInLifecycleEvent() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner()

        lifecycleOwner.withOnDestroy { invoked = true }
        lifecycleOwner.create()
        lifecycleOwner.start()
        lifecycleOwner.resume()
        lifecycleOwner.pause()
        lifecycleOwner.stop()
        lifecycleOwner.destroy()

        assertTrue(invoked)
    }

    @Test
    fun withOnFinishingThenInvokeInLifecycleEventWhileFinishing() {
        var invoked = false
        val lifecycleOwner = MockLifeCycleOwner(true)

        lifecycleOwner.withOnFinishing { invoked = true }
        lifecycleOwner.create()
        lifecycleOwner.start()
        lifecycleOwner.resume()
        lifecycleOwner.pause()
        lifecycleOwner.stop()
        lifecycleOwner.destroy()

        assertTrue(invoked)
    }
}


class MockLifeCycleOwner(private val finishing: Boolean = false) : LifecycleOwner, FinishingOwner {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    fun create() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun start() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun resume() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    fun pause() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    fun stop() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    fun destroy() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun isFinishing() = finishing


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
