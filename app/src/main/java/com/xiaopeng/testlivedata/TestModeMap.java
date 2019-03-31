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
 * MAP转换LiveData
 */
public class TestModeMap extends ViewModel{

    private MutableLiveData<Integer> mIntegerMutableLiveData = new MutableLiveData<>();

    private LiveData<String> mStringLiveData = Transformations.map(mIntegerMutableLiveData, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return input + "";
        }
    });


    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return mIntegerMutableLiveData;
    }

    public LiveData<String> getStringLiveData() {
        return mStringLiveData;
    }
}
