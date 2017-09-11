package com.wday.search.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.wday.search.api.github.GitHubRepoSearchService;
import com.wday.search.api.github.GitHubRepoSearchServiceImpl;
import com.wday.search.api.twitter.TweetsSearchService;
import com.wday.search.api.twitter.TweetsSearchServiceImpl;

import twitter4j.JSONObject;

public class App {
	final static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws Exception {

		GitHubRepoSearchService gitHubRepoSearchService = new GitHubRepoSearchServiceImpl();

		// will be moved to configrable params
		Map<String, String> configParams = new HashMap<String, String>();
		configParams.put("path", "/search/repositories");
		configParams.put("q", "Reactive");
		configParams.put("sort", "stars");
		configParams.put("order", "desc");

		List<String> repoNames = gitHubRepoSearchService.searchGiHubRepoByKeyword(configParams);
		// will be moved to Unit Test
		for (String repoName : repoNames) {
			logger.debug(String.format("repoName:%s", repoName));
		}

		logger.debug(String.format("repoNames::", repoNames.toString()));

		TweetsSearchService tweetsSearchService = new TweetsSearchServiceImpl();

		JSONObject JSONObject = tweetsSearchService.searchRecentTweetByRepositoryName(repoNames);
		JSONObject = tweetsSearchService.searchRecentTweetByRepositoryName(repoNames);

		logger.debug(String.format("JSON:: %s", JSONObject.toString()));
	}

}
