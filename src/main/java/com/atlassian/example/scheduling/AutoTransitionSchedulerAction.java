package com.atlassian.example.scheduling;

import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.jql.parser.JqlParseException;
import com.atlassian.jira.web.action.JiraWebActionSupport;

public class AutoTransitionSchedulerAction extends JiraWebActionSupport {
    private final TransitionInterface transitionInterface;

    public AutoTransitionSchedulerAction(TransitionInterface transitionInterface) {
        this.transitionInterface = transitionInterface;
    }

    public String doReschedule() throws JqlParseException, SearchException {
        transitionInterface.reschedule();
        return getRedirect("AutoTransition!default.jspa", false);
    }

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }
}
