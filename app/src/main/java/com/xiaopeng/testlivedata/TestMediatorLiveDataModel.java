package com.xiaopeng.testlivedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

/**
 * Date: 2019/3/31
 * Created by LiuJian
 *
 * @author LiuJian
 */

public class TestMediatorLiveDataModel extends ViewModel {
    private LiveData<String> mNetWorkData  = new MutableLiveData<>();

    private LiveData<String> mDBdata = new MutableLiveData<>();

    private MediatorLiveData<String>  mMediatorLiveData = new MediatorLiveData<>();

    private TestMediatorLiveDataModel(){
        mMediatorLiveData.addSource(mNetWorkData, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String mS) {
                // TODO: 2019/3/31 do something
            }
        });
        mMediatorLiveData.addSource(mDBdata, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String mS) {
                // TODO: 2019/3/31 do something
            }
        });
    }
    static final class Holder{
        static final TestMediatorLiveDataModel INSTANCE = new TestMediatorLiveDataModel();
    }


    public static TestMediatorLiveDataModel getInstance(){
        return Holder.INSTANCE;
    }



}
