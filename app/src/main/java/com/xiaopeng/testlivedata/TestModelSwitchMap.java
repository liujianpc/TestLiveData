package com.xiaopeng.testlivedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

/**
 * Date: 2019/3/31
 * Created by LiuJian
 *
 * @author LiuJian
 */

/**
 * SwitchMap转换LiveData
 * 与Map的区别是，SwitchMap返回的是一个LiveData
 */
public class TestModelSwitchMap extends ViewModel {
    private MutableLiveData<Integer> mIntegerMutableLiveData = new MutableLiveData<>();

    private LiveData<String> mStringLiveData = Transformations.switchMap(mIntegerMutableLiveData, new Function<Integer, LiveData<String>>() {
        @Override
        public LiveData<String> apply(Integer input) {
            return getLiveData(input);
        }
    });

    private LiveData<String> getLiveData(Integer mInput) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(mInput+"_Str");
        return mutableLiveData;
    }

    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return mIntegerMutableLiveData;
    }

    public LiveData<String> getStringLiveData() {
        return mStringLiveData;
    }
}
