[![](https://jitpack.io/v/Ahmed-Adel-Ismail/LiteCycle.svg)](https://jitpack.io/#Ahmed-Adel-Ismail/LiteCycle)

# LiteCycle
A library that helps implementing Android's LifeCycleObserver interface for variables instead of Classes

# Sample Kotlin Code :
For Kotlin, this library adds extension functions on LifecycleOwner to make it's life-cycle events accessible from any point, the extension functions are all available at this <a href="https://github.com/Ahmed-Adel-Ismail/LiteCycle/blob/feature/kotlin-support/kotlin/src/main/java/com/android/LifecycleOwnerExtensions.kt">file</a> if you do not want to add a dependency to your project, but do not forget to add <b>DefaultLifecycleObserver</b> dependency in your gradle, which is the Java 8 version of architecture components life-cycle observer :   

```kotlin
// in your activity or fragment, or any class that implements LifecycleOwner interface :
withOnCreate{ /* execute this code in onCreate() */ }
withOnStart{ /* execute this code in onStart() */ }
withOnResume{ /* execute this code in onResume() */ }
withOnPause{ /* execute this code in onPause() */ }
withOnStop{ /* execute this code in onStop() */ }
withOnDestroy{ /* execute this code in onDestroy() */ }
withOnFinishing{ /* execute this code in onDestroy() when Activity.isFinishing() returns true */ }
```

# Sample Java Code :
```java
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
```            
# How things work

You can initialize a value that changes based on the Life cycle events by one of two ways :
    
    - Instant initialization :
        LiteCycle.with(10)
    - Lazy initialization :
        LiteCycle.defer(() -> getIntent().getBooleanExtra("EXTRA", false))

for Lazy initialization, you pass a Callable that will be invoked the first time any operation tries to access / update the value

After initializing a LiteCycle with a value stored in it, you can use or update the value based on the life-cycle events through the available methods, for example
```Java    
LiteCycle.with(10)
        .forLifeCycle(this) // pass Activity or Fragment
        .onCreateUpdate(i -> i + 1)
```            
this will update the stored value by adding one to it, in the onCreate() Event ... you can invoke the life-cycle event related methods multiple times, and the passed functions will be executed in the same order, like for example :
```Java
.onCreateInvoke(i -> Log.e("LiteCycle", "initial value " + i))
.onCreateUpdate(i -> i + 1)
.onCreateInvoke(i -> Log.e("LiteCycle", "onCreate() invoked " + i))
```
The above 3 lines will be executed in the same order when onCreate() event happens

Also there are 2 types of actions, <b>update</b> and <b>invoke</b>, like 
```java
.onResumeUpdate(i -> i + 1)
.onResumeInvoke(i -> Log.e("LiteCycle", "onResume() invoked " + i))
```
The <b>update</b> operation takes the result of the passed function and updates the stored value in the <b>LiteCycle</b>, while the <b>invoke</b> operation just uses the value stored, but it does not change it

at the end you need to invoke 
```java    
.observe();
```
for all things to take effect
 
# Handling Rotation
 
There are functions like 
 ```java
 .onFinishingUpdate(i -> null)
 .onFinishingInvoke(i -> Log.e("LiteCycle", "isFinishing() invoked " + i))
```     
Those functions will not invoke while rotation, they will be invoked after onDestroy() in case of non-rotation events, where the Life-Cycle-Owner is about to be totally destroyed

# Listen on value updates

Since version <b>1.2.1</b>, the <i>observe()</i> method returns an <b> Rx Java 2 Observable </b> so you can subscribe to it and get notified when ever the value is changed, so in our sample example, we can write it this way
```java
Observable<Integer> integer = LiteCycle.with(10)
        .forLifeCycle(this)
        .onCreateUpdate(i -> i + 1)
        .onStartUpdate(i -> i + 1)
        .onResumeUpdate(i -> i + 1)
        .onPauseUpdate(i -> i + 1)
        .onStopUpdate(i -> i + 1)
        .onDestroyUpdate(i -> 10)
        .observe(BehaviorSubject.create());

Disposable disposable = integer.subscribe(i -> Log.e("LiteCycle", "integer value " + i));
```    
The Observable created by <i>observe(Subject)</i> method is a <b>BehaviorSubject</b>, if you want a different type, you can pass any RxJava Subject sub-class, as follows :
```java
Observable<Integer> integer = LiteCycle.with(10)
            .forLifeCycle(this)
            .onResumeUpdate(i -> i + 1)
            .onPauseUpdate(i -> i + 1)
            .observe(PublishSubject.create());
```    
you do not need to care about <b>Observable</b> created from the <i>observe(Subject)</i> call, since it completes itself when the Life-Cycle Owner (Activity or Fragment) calls it's <i>onDestroy()</i>, so you can safely write the previous code as follows :
```java
LiteCycle.with(10)
        .forLifeCycle(this)
        .onResumeUpdate(i -> i + 1)
        .onPauseUpdate(i -> i + 1)
        .observe(PublishSubject.create())
        .map(i -> "integer value " + i)
        .subscribe(text -> Log.e("LiteCycle", text))
```
This practice is perfect when declaring RxJava2 Disposables, since you can guarantee that they are disposed when <i>onDestroy()</i> is called
```Java
LiteCycle.with(interval())
        .forLifeCycle(this)
        .onDestroyInvoke(Disposable::dispose)
        .observe();

Disposable interval(){
    return Observable.interval(2, TimeUnit.SECONDS)
            .subscribe(this::doSomething);
}
```
Notice that if the value is set to <b>null</b>, the Observable wont be notified since <b>RxJava 2</b> does not support <b>null</b> values

# Gradle dependency 

    Step 1. Add the JitPack repository to your build file
    Add it in your root build.gradle at the end of repositories:
```Gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
    Step 2. Add the dependency 
```Gradle
dependencies {
    
    // for Java
    implementation 'com.github.Ahmed-Adel-Ismail:LiteCycle:1.2.1'
    
    // for kotlin
    implementation 'com.github.Ahmed-Adel-Ismail.LiteCycle:kotlin:1.3.0' // notice version difference
}
```
