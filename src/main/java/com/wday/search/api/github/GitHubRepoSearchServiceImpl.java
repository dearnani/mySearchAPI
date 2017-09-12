package com.wday.search.api.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;

/**
 * 
 * @author Narasimha
 * 
 *   @NotNull(message = "user name can't be NULL") final String user,
        @NotNull(message = "password can't be NULL") final String pwd)
 *
 */
public class GitHubRepoSearchServiceImpl implements GitHubRepoSearchService {
	
	final static Logger logger = Logger.getLogger(GitHubRepoSearchServiceImpl.class);
	
	  public List<String> searchGiHubRepoByKeyword (@NotNull(message = "API configuration Parameters should not be NULL") final Map<String, String> apiConfigParams) 
	    {
		  List<String> reactiveRepostList = new ArrayList<String>();
	    	final Github github = new RtGithub();
		  try
		  {
			  logger.info("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: Before GitHub API Query");
			  
		    	final JsonResponse jsonResponse = github.entry().uri().path(apiConfigParams.get("path"))
	    			.queryParams(apiConfigParams)
		    	    .back()
		    	    .fetch()
		    	    .as(JsonResponse.class);
		    	final List<JsonObject> itemsList = jsonResponse.json().readObject() 
		    	    .getJsonArray("items")
		    	    .getValuesAs(JsonObject.class);
		    	 logger.debug(String.format("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: reactiveRepostList.size: %s ", itemsList.size()));
		    	
		    	 // Capturing the first 10 GitHub repos search results given as keyword
		    	for (final JsonObject item : itemsList.subList(0, 9)) {
		    		reactiveRepostList.add(item.get("full_name").toString());
		    	}
		    	
		  } catch (  IOException ioException )
		  {
			logger.error(String.format("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: IOExcepion.getMessage(): %s", ioException.getMessage())); 
		  }
		  	logger.info("GitHubRepoSearchServiceImpl->searchGiHubRepoByKeyword: Returned the results");
	    	return reactiveRepostList;
	    }
	
}