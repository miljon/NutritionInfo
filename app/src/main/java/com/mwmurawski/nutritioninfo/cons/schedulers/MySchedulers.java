package com.mwmurawski.nutritioninfo.cons.schedulers;

import io.reactivex.Scheduler;

public interface MySchedulers {

    Scheduler ui();
    Scheduler computation();
    Scheduler io();

    //other schedulers if needed

}
