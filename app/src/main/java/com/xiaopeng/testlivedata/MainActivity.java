package com.xiaopeng.testlivedata;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author XP-PC-XXX
 */

/**
 * 使用LiveData具有以下优点：

 1.确保UI跟数据状态一致
 组件处于活跃状态时，当数据发生变化，LiveData会通知观察者去更新UI，从而使得他们保持一致。

 2.没有内存泄露
 由于观察者绑定到了Lifecycle对象上，因此在Lifecycle被销毁后，观察者会被自行清理掉。

 3.停止Activity不会造成崩溃
 如果Activity处于非活跃的状态，比如Activity在后台时，那么它不会接受到LiveData的数据变更事件。

 4.无需手动处理生命周期
 UI组件仅仅需要观察相应的数据即可，无需手动去停止或恢复观察。因为LiveData会自动管理这所有的，它在观察时能够意识到相关的生命周期状态变化。

 5.始终保持最新数据
 如果生命周期处于不活跃的状态，那么当它变为活跃状态时将会收到最新的数据。比如：后台Activity变为前台时将会收到最新的数据。

 6.适当的配置更改
 如果由于配置更改而重新去创建Activity或Fragment，那么会立即接收最新的可用数据。

 7.资源共享
 你可以继承LiveData并使用单例模式来扩展系统的服务，这样你就可以共享它。这个自定义的LiveData对象只需连接一次系统服务，其他需要这些资源的观察者只需观察这个LiveData即可。

 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private TextView mTextView;
    private TestViewModel mTestViewModel;
    private android.widget.Button mSendMsg;
    private int mTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mSendMsg = (Button) findViewById(R.id.send_msg);

        this.mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestViewModel.getStatus().setValue("this is a msg from button click");
            }
        });
        mTextView = findViewById(R.id.text_view);

        initLiveModelRelation();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                mTestViewModel.getStatus().postValue(""+ mTotal++);
            }
        },500,1000, TimeUnit.MILLISECONDS);

        getLifecycle().addObserver(new LifecycleObserver() {

            private static final String TAG = "LifeCycle";

            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            public void onCreate(){
                Log.d(TAG,"the activity is created");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            public void onStart(){
                Log.d(TAG,"the activity is started");

            }
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            public void onResume(){
                Log.d(TAG,"the activity is resumed");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            public void onPause(){
                Log.d(TAG,"the activity is paused");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            public void onStop(){
                Log.d(TAG, "the activity is stopped");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            public void onDestroy(){
                Log.d(TAG,"the activity is destroyed");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
            public void onAny(){
                Log.d(TAG, "the activity is onAny");
            }


        });

    }

    private void initLiveModelRelation() {


        Observer<String> statusObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String mS) {
                Log.d(TAG,"onchanged: "+mS);
                mTextView.setText(mS);
            }
        };

        mTestViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mTestViewModel.getStatus().observe(this,statusObserver);


    }


    @Override
    protected void onStart() {
        mTestViewModel.getStatus().setValue("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        mTestViewModel.getStatus().setValue("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        mTestViewModel.getStatus().setValue("onPause");
        super.onPause();
    }
}
