package com.atlassian.example.scheduling;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author  Erik van Zijst
 */
public class SchedulerActionTest {

    @Test
    public void testReadonlyAction() throws Exception {

        final TwitterMonitor twitterMonitor = mock(TwitterMonitor.class);

        SchedulerAction action = new SchedulerAction(twitterMonitor);
        action.setInterval(1000L);
        action.setQuery("query");
        action.doExecute();
        verify(twitterMonitor, never()).reschedule("query", 1000L);
    }

    @Test
    public void testRescheduleAction() {

        final TwitterMonitor twitterMonitor = mock(TwitterMonitor.class);

        SchedulerAction action = new SchedulerAction(twitterMonitor);
        action.setInterval(1000L);
        action.setQuery("query");
        action.doReschedule();
        verify(twitterMonitor, times(1)).reschedule("query", 1000L);
    }
}
