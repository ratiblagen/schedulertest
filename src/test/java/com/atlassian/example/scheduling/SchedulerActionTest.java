package com.atlassian.example.scheduling;

import com.atlassian.jira.junit.rules.AvailableInContainer;
import com.atlassian.jira.junit.rules.MockitoMocksInContainer;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.util.I18nHelper;
import com.atlassian.jira.web.action.RedirectSanitiser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author  Erik van Zijst
 */
public class SchedulerActionTest {
    @Rule
    public RuleChain mockitoMocksInContainer = MockitoMocksInContainer.forTest(this);

    @AvailableInContainer
    @Mock
    private JiraAuthenticationContext jiraAuthenticationContext;

    @AvailableInContainer
    @Mock
    private RedirectSanitiser redirectSanitiser;

    @Mock
    private I18nHelper i18nHelper;
    
    @Before
    public void setUp() throws Exception {
        Mockito.when(jiraAuthenticationContext.getI18nHelper()).thenReturn(i18nHelper);
    }

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
