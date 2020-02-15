package com.example.myapplication;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class DatabaseHelper {

    private PublishSubject<String> mOnNewData = PublishSubject.create();
    private PublishSubject<Integer> mOnNewIntData = PublishSubject.create();
    private PublishSubject<CacheHelper> mOnNewCache = PublishSubject.create();

    public Observable<String> onNewData() {
        return mOnNewData;
    }

    public Observable<Integer> onNewIntData() {
        return mOnNewIntData;
    }

    public Observable<CacheHelper> onNewCache() {
        return mOnNewCache;
    }

    public void retrieveData() {
        mOnNewData.onNext("Name");
        mOnNewIntData.onNext(30);
        mOnNewCache.onNext(CacheHelper.getInstance());
    }


}
