package com.wday.search.api.twitter;

import java.util.List;

import twitter4j.JSONObject;

public interface TweetsSearchService {

	JSONObject searchRecentTweetByRepositoryName(List<String> repositoryNames);
}
