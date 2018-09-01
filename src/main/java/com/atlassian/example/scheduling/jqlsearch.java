package com.atlassian.example.scheduling;

import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.web.bean.PagerFilter;

import java.util.List;

public class jqlsearch {

    String jqlQuery = "project = JRA and reporter = currentUser() and cf[10490] = xss";

    String result = getGeneratedJqlString(Query jqlQuery);



}
////    final SearchService.ParseResult parseResult =
////            parseResult.parseQuery( jqlQuery);
////        if(parseResult.isValid())
//
////    {
////        try {
////            final SearchResults results = searchService.search(authenticationContext.getUser(),
////                    parseResult.getQuery(), PagerFilter.getUnlimitedFilter());
////            final List<Issue> issues = results.getIssues();
////
////        } catch (SearchException e) {
////            log.error("Error running search", e);
////        }
////    }
////        else
//
//    {
//        log.warn("Error parsing jqlQuery: " + parseResult.getErrors());
//    }
//}