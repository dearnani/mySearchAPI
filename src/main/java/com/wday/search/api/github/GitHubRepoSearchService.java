package com.wday.search.api.github;

import java.util.List;
import java.util.Map;

//@FunctionalInterface
public interface GitHubRepoSearchService {
	 	public  List<String> searchGiHubRepoByKeyword (Map<String,String> keyword) throws Exception;
}
