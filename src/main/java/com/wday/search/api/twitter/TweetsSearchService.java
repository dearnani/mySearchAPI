package com.wday.search.api.twitter;

import java.util.List;

import twitter4j.JSONException;
import twitter4j.JSONObject;

//@FunctionalInterface
public interface TweetsSearchService {

	public JSONObject searchRecentTweetByRepositoryName(List<String> repositoryNames) throws JSONException ;
}
