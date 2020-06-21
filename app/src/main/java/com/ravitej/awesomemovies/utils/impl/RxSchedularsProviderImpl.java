package com.ravitej.awesomemovies.utils.impl;

import com.ravitej.awesomemovies.utils.RxSchedularProvider;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executors;

public class RxSchedularsProviderImpl implements RxSchedularProvider {

    private static final int RX_CONCURRENT_THREAD_COUNT = 5;

    private static RxSchedularProvider instance;
    private Scheduler uiSchedular;
    private Scheduler networkSchedular;
    private Scheduler diskSchedular;

    private RxSchedularsProviderImpl() {
        this.uiSchedular = AndroidSchedulers.mainThread();
        this.networkSchedular = Schedulers
            .from(Executors.newFixedThreadPool(RX_CONCURRENT_THREAD_COUNT));
        this.diskSchedular = Schedulers.io();
    }

    public static RxSchedularProvider getInstance() {
        if (instance == null) {
            instance = new RxSchedularsProviderImpl();
        }

        return instance;
    }

    @Override
    public Scheduler uiSchedular() {
        return uiSchedular;
    }

    @Override
    public Scheduler networkSchedular() {
        return networkSchedular;
    }

    @Override
    public Scheduler diskSchedular() {
        return diskSchedular;
    }
}
