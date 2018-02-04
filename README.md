# LiteCycle
A library that helps implementing Android's LifeCycleObserver interface for variables instead of Classes

# Sample Code :

    LiteCycle.with(10)
            .forLifeCycle(this)
            .onCreateInvoke(i -> Log.e("LiteCycle", "initial value " + i))
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
            .forLifeCycle(this) // pass Activity or Fragment
            .onCreateInvoke(extra -> Log.e("LiteCycle", "extra boolean : " + extra))
            .observe();
            
# How things work

You can initialize a value that changes based on the Life cycle events by one of two ways :
    
    - Instant initialization :
        LiteCycle.with(10)
    - Lazy initialization :
        LiteCycle.defer(() -> getIntent().getBooleanExtra("EXTRA", false))

for Lazy initialization, you pass a Callable that will be invoked the first time any operation tries to access / update the value

After initializing a LiteCycle with a value stored in it, you can use or update the value based on the life-cycle events through the available methods, for example
    
    LiteCycle.with(10)
            .forLifeCycle(this) // pass Activity or Fragment
            .onCreateUpdate(i -> i + 1)
            
this will update the stored value by adding one to it, in the onCreate() Event ... you can invoke the life-cycle event related methods multiple times, and the passed functions will be executed in the same order, like for example :

    .onCreateInvoke(i -> Log.e("LiteCycle", "initial value " + i))
    .onCreateUpdate(i -> i + 1)
    .onCreateInvoke(i -> Log.e("LiteCycle", "onCreate() invoked " + i))

The above 3 lines will be executed in the same order when onCreate() event happens

Also there are 2 types of actions, <b>update</b> and <b>invoke</b>, like 

    .onResumeUpdate(i -> i + 1)
    .onResumeInvoke(i -> Log.e("LiteCycle", "onResume() invoked " + i))

The <b>update</b> operation takes the result of the passed function and updates the stored value in the <b>LiteCycle</b>, while the <b>invoke</b> operation just uses the value stored, but it does not change it

at the end you need to invoke 
    
    .observe();

for all things to take effect
 
# Handling Rotation
 
There are functions like 
 
     .onFinishingUpdate(i -> null)
     .onFinishingInvoke(i -> Log.e("LiteCycle", "isFinishing() invoked " + i))
     
Those functions will not invoke while rotation, they will be invoked after onDestroy() in case of non-rotation events, where the Life-Cycle-Owner is about to be totally destroyed

# Listen on value updates

Since version <b>1.0.0</b>, the <i>observe()</i> method returns an <b> Rx Java 2 Observable </b> so you can subscribe to it and get notified when ever the value is changed, so in our sample example, we can write it this way

    Observable<Integer> integer = LiteCycle.with(10)
            .forLifeCycle(this)
            .onCreateUpdate(i -> i + 1)
            .onStartUpdate(i -> i + 1)
            .onResumeUpdate(i -> i + 1)
            .onPauseUpdate(i -> i + 1)
            .onStopUpdate(i -> i + 1)
            .onDestroyUpdate(i -> 10)
            .observe();
            
    Disposable disposable = integer.subscribe(i -> Log.e("LiteCycle", "integer value " + i));
    
you do not need to care about <b>Observable</b> created from the <i>observe()</i> call, since it completes itself when the Life-Cycle Owner (Activity or Fragment) is totally finishing, how ever, you can dispose your <b>Disposable</b> in <i>onDestroy()</i>

# Gradle dependency 

    Step 1. Add the JitPack repository to your build file
    Add it in your root build.gradle at the end of repositories:

        allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }

    Step 2. Add the dependency

        dependencies {
                compile 'com.github.Ahmed-Adel-Ismail:LiteCycle:1.0.0'
        }
