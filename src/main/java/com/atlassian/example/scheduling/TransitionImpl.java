package com.atlassian.example.scheduling;

import com.atlassian.jira.issue.Issue;
import com.atlassian.sal.api.lifecycle.LifecycleAware;
import com.atlassian.sal.api.scheduling.PluginScheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TransitionImpl implements TransitionInterface, LifecycleAware {
    private final PluginScheduler pluginScheduler;  // provided by SAL
    static final String KEY = TransitionImpl.class.getName() + ":instance";
    private static final String JOB_NAME = TransitionImpl.class.getName() + ":job";
    private long interval = 100l;      // default job interval (5 sec)
    private List<Issue> issues;
    private Date lastRun = null;        // time when the last search returned

    public TransitionImpl(PluginScheduler pluginScheduler) {
        this.pluginScheduler = pluginScheduler;
    }

    // declared by LifecycleAware
    public void onStart() {
        reschedule();
    }

    public void reschedule() {
        pluginScheduler.scheduleJob(
                JOB_NAME,                   // unique name of the job
                TransitionTask.class,     // class of the job
                new HashMap<String, Object>() {{
                    put(KEY, TransitionImpl.this);
                }},                         // data that needs to be passed to the job
                new Date(),                 // the time the job is to start
                interval);                  // interval between repeats, in milliseconds
    }


    public long getInterval() {
        return interval;
    }

    public Date getLastRun() {
        return lastRun;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    /* package */ void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    /* package */ void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }

}


