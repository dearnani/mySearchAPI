package com.wday.search.api.core;

import java.util.List;
import org.apache.log4j.Logger;
import com.wday.search.api.github.GitHubRepoSearchService;
import com.wday.search.api.github.GitHubRepoSearchServiceImpl;
import com.wday.search.api.twitter.TweetsSearchService;
import com.wday.search.api.twitter.TweetsSearchServiceImpl;
import com.wday.search.api.util.PropertyReader;

import twitter4j.JSONObject;
/**
 * 
 * @author Narasimha
 *
 */
public class APIManager {
	
	final static Logger logger = Logger.getLogger(APIManager.class);

	public static void managesearchAPI()  {
		
		GitHubRepoSearchService gitHubRepoSearchService = new GitHubRepoSearchServiceImpl();
		List<String> repoNames = gitHubRepoSearchService.searchGiHubRepoByKeyword(PropertyReader.populateSearchRepoProperties());
		logger.debug(String.format("repoNames::", repoNames.toString()));
		
		TweetsSearchService tweetsSearchService = new TweetsSearchServiceImpl();
		JSONObject JSONObject = tweetsSearchService.searchRecentTweetByRepositoryName(repoNames);
		logger.debug(String.format("JSON:: %s", JSONObject.toString()));
	}
	
}
