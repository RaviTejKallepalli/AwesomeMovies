package com.ravitej.awesomemovies.utils;

import io.reactivex.Scheduler;

public interface RxSchedularProvider {

    Scheduler uiSchedular();

    Scheduler networkSchedular();

    Scheduler diskSchedular();
}
