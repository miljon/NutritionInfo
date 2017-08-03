package com.mwmurawski.nutritioninfo.utils.rx.schedulers;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();
    Scheduler computation();
    Scheduler io();

    //other schedulers if needed

}
