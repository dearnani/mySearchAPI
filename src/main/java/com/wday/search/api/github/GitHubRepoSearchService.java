package com.wday.search.api.github;

import java.util.List;
import java.util.Map;

public interface GitHubRepoSearchService {
	 	 List<String> searchGiHubRepoByKeyword (Map<String,String> keyword);
}
