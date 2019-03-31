package com.xiaopeng.testlivedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Date: 2019/3/31
 * Created by LiuJian
 *
 * @author LiuJian
 */

public class TestViewModel extends ViewModel {
    private MutableLiveData<String> mStatus;

    public MutableLiveData<String> getStatus(){
        if (mStatus == null){
            mStatus = new MutableLiveData<>();
        }
        return mStatus;

    }
}
