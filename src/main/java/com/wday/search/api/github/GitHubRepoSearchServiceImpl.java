package com.wday.search.api.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

import org.apache.log4j.Logger;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;

/**
 * 
 * @author Anvi
 *
 */
public class GitHubRepoSearchServiceImpl implements GitHubRepoSearchService {
	
	final static Logger logger = Logger.getLogger(GitHubRepoSearchServiceImpl.class);
	
	  public List<String> searchGiHubRepoByKeyword (Map<String, String> apiConfigParams) 
	    {
		  List<String> reactiveRepostList = new ArrayList<String>();
	    	final Github github = new RtGithub();
		  try
		  {
			  logger.info("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: Before GitHub API Query");
			  
		    	final JsonResponse resp = github.entry().uri().path(apiConfigParams.get("path"))
	    			.queryParams(apiConfigParams)
		    	    .back()
		    	    .fetch()
		    	    .as(JsonResponse.class);
		    	final List<JsonObject> itemsList = resp.json().readObject() 
		    	    .getJsonArray("items")
		    	    .getValuesAs(JsonObject.class);
		    	 logger.debug(String.format("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: reactiveRepostList.size: %s ", itemsList.size()));
		    	
		    	 // Capturing the first 10 git repo search results given as keyword
		    	for (final JsonObject item : itemsList.subList(0, 9)) {
		    		reactiveRepostList.add(item.get("name").toString());
		    	}
		    	
		  } catch (  IOException ioException )
		  {
			logger.error(String.format("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: IOExcepion.getMessage(): %s", ioException.getMessage())); 
		  }
		  	logger.info("Returning the results from GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword");
	    	return reactiveRepostList;
	    }
}