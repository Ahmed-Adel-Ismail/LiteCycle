package com.android;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import java.util.concurrent.Callable;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LiteCycleTest {

    @Test
    public void onCreateInvokeForCreatedOwnerThenUpdateValue() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onCreateInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = true;
                    }
                }).observe();

        owner.create();

        assertTrue(result[0]);
    }

    @Test
    public void onCreateUpdateForCreatedOwnerThenUpdateValue() {
        final int[] result = new int[]{0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onCreateUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        result[0] = 1;
                        return 1;
                    }
                }).observe();

        owner.create();

        assertEquals(1, result[0]);
    }

    @Test
    public void onStartInvokeForCreatedOwnerThenUpdateValue() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onStartInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = true;
                    }
                }).observe();

        owner.create();
        owner.start();

        assertTrue(result[0]);
    }

    @Test
    public void onStartUpdateForCreatedOwnerThenUpdateValue() {
        final int[] result = new int[]{0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onStartUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        result[0] = 1;
                        return 1;
                    }
                }).observe();

        owner.create();
        owner.start();

        assertEquals(1, result[0]);
    }

    @Test
    public void onResumeInvokeForCreatedOwnerThenUpdateValue() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onResumeInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = true;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();

        assertTrue(result[0]);
    }

    @Test
    public void onResumeUpdateForCreatedOwnerThenUpdateValue() {
        final int[] result = new int[]{0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onResumeUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        result[0] = 1;
                        return 1;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();

        assertEquals(1, result[0]);
    }

    @Test
    public void onPauseInvokeForCreatedOwnerThenUpdateValue() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onPauseInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = true;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();
        owner.pause();

        assertTrue(result[0]);
    }

    @Test
    public void onPauseUpdateForCreatedOwnerThenUpdateValue() {
        final int[] result = new int[]{0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onPauseUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        result[0] = 1;
                        return 1;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();
        owner.pause();

        assertEquals(1, result[0]);
    }

    @Test
    public void onStopInvokeForCreatedOwnerThenUpdateValue() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onStopInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = true;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();
        owner.pause();
        owner.stop();

        assertTrue(result[0]);
    }

    @Test
    public void onStopUpdateForCreatedOwnerThenUpdateValue() {
        final int[] result = new int[]{0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onStopUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        result[0] = 1;
                        return 1;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();
        owner.pause();
        owner.stop();

        assertEquals(1, result[0]);
    }

    @Test
    public void onDestroyInvokeForCreatedOwnerThenUpdateValue() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onDestroyInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = true;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();
        owner.pause();
        owner.stop();
        owner.destroy();

        assertTrue(result[0]);
    }

    @Test
    public void onDestroyUpdateForCreatedOwnerThenUpdateValue() {
        final int[] result = new int[]{0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle.with(0)
                .forLifeCycle(owner)
                .onDestroyUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        result[0] = 1;
                        return 1;
                    }
                }).observe();

        owner.create();
        owner.start();
        owner.resume();
        owner.pause();
        owner.stop();
        owner.destroy();

        assertEquals(1, result[0]);
    }

    @Test
    public void deferWithCallableThenInvokeCallOnFirstInvoke() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle
                .defer(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        result[0] = true;
                        return 0;
                    }
                })
                .forLifeCycle(owner)
                .onResumeInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        // do nothing
                    }
                })
                .observe();

        owner.create();

        if (result[0]) {
            throw new AssertionFailedError("lazy initialization happened before update()");
        }

        owner.resume();

        assertTrue(result[0]);

    }

    @Test
    public void deferWithCallableThenInvokeCallOnFirstUpdate() {
        final boolean[] result = new boolean[]{false};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();
        LiteCycle
                .defer(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        result[0] = true;
                        return 0;
                    }
                })
                .forLifeCycle(owner)
                .onResumeUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return 1;
                    }
                })
                .observe();

        owner.create();

        if (result[0]) {
            throw new AssertionFailedError("lazy initialization happened before update()");
        }

        owner.resume();

        assertTrue(result[0]);

    }

    @Test
    public void observeWithNoParameterThenReturnBehaviorSubject() {
        Object subject = LiteCycle.with(0)
                .forLifeCycle(new MockLifeCycleOwner())
                .observe();
        assertTrue(subject instanceof BehaviorSubject);
    }

    @Test
    public void buildWithUpdatingValueThenUpdateValue() {
        final int[] result = {0};
        MockLifeCycleOwner owner = new MockLifeCycleOwner();

        LiteCycle.with(result[0])
                .forLifeCycle(owner)
                .onCreateUpdate(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return 1;
                    }
                })
                .onResumeInvoke(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result[0] = integer;
                    }
                })
                .build();

        owner.create();
        owner.resume();

        assertEquals(1,result[0]);
    }

    @Test
    public void observeWithPublishSubjectParameterThenReturnPublishSubject() {
        Object subject = LiteCycle.with(0)
                .forLifeCycle(new MockLifeCycleOwner())
                .observe(PublishSubject.<Integer>create());
        assertTrue(subject instanceof PublishSubject);
    }


}