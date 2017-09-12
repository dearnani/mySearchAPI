package com.wday.search.api.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;

import com.wday.search.api.github.GitHubRepoSearchService;
import com.wday.search.api.github.GitHubRepoSearchServiceImpl;
import com.wday.search.api.twitter.TweetsSearchService;
import com.wday.search.api.twitter.TweetsSearchServiceImpl;
import com.wday.search.api.util.PropertyReader;

import twitter4j.JSONException;
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
		JSONObject jsonObject = tweetsSearchService.searchRecentTweetByRepositoryName(repoNames);
		logger.debug(String.format("JSON:: %s", jsonObject.toString()));
		
		//Get the file reference
		
		Path path = Paths.get( String.format("%s/Result.json", System.getProperty("user.home"))); 
		//Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter jsonFileWriter = Files.newBufferedWriter(path)) {
		  
			jsonObject.write(jsonFileWriter);
			
		} catch (IOException  |  JSONException exception) {
			
			logger.error(String.format("Exception Occured while saving the resulted Json File: %s",exception.getMessage()));

		}
	}
}