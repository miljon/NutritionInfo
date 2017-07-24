package com.mwmurawski.nutritioninfo.utils.rx.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

public class TestSchedulerProvider implements SchedulerProvider {

    private final TestScheduler testScheduler;

    public TestSchedulerProvider(final TestScheduler testScheduler) {
        this.testScheduler = testScheduler;
    }

    @Override
    public Scheduler ui() {
        return testScheduler;
    }

    @Override
    public Scheduler computation() {
        return testScheduler;
    }

    @Override
    public Scheduler io() {
        return testScheduler;
    }
}
