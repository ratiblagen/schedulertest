package com.atlassian.example.scheduling;

import java.util.Date;

public interface TransitionInterface {

    public long getInterval();

    public Date getLastRun();

    public void reschedule();
}
