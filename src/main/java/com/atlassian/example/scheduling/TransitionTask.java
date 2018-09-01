package com.atlassian.example.scheduling;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.sal.api.scheduling.PluginJob;
import org.ofbiz.core.entity.GenericEntityException;

import java.util.*;

public class TransitionTask implements PluginJob {
    public void execute(Map<String, Object> jobDataMap) {

        final TransitionImpl monitor = (TransitionImpl) jobDataMap.get(TransitionImpl.KEY);
        assert monitor != null;
        try {
            List<Issue> issues = getIssues();
            monitor.setIssues(issues);
        } catch (GenericEntityException e) {
            e.printStackTrace();
        }
        monitor.setLastRun(new Date());
    }

    private List<Issue> getIssues() throws GenericEntityException {
        List<Issue> issues = new ArrayList<Issue>();
        ProjectManager projectManager = ComponentAccessor.getProjectManager();
        List<Project> projects = projectManager.getProjects();
        IssueManager issueManager = ComponentAccessor.getIssueManager();
        for (Project project : projects) {
            Collection<Long> issuesids = issueManager.getIssueIdsForProject(project.getId());
            for (Long id : issuesids) {
                Issue issue = issueManager.getIssueObject(id);
                issues.add(issueManager.getIssueObject(id));
            }
        }
        return issues;
    }
}
