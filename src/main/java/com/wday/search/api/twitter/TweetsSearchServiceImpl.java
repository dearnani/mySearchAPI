package com.wday.search.api.twitter;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.Query.ResultType;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetsSearchServiceImpl implements TweetsSearchService {

	final static Logger logger = Logger.getLogger(TweetsSearchServiceImpl.class);

	public JSONObject searchRecentTweetByRepositoryName(@NotNull(message = "Repository Names should not be NULL") final List<String> repositoryNames)  {

		logger.info("TweetsSearchServiceImpl->searchRecentTweetByRepositoryName: Before Twitter API Query");

		QueryResult queryResult;
		Query twtApiQuery;
		List<Status> tweets;
		int noOfTweets = 0;
		Twitter twitterAPI = new TwitterFactory().getInstance();
		JSONObject json = new JSONObject();

		try {

			for (String repoName : repositoryNames) {
			twtApiQuery = new Query(repoName);
			logger.debug(String.format("TweetsSearchServiceImpl->searchRecentTweetByRepositoryName: repositoryNames:: %s ", repositoryNames.toString()));
			
			do {
				queryResult = twitterAPI.search(twtApiQuery);
				tweets = queryResult.getTweets();
				noOfTweets = tweets.size();
				
				logger.debug(String.format("@ LIst of Statuses %s -  size: %d", tweets.toString(), noOfTweets));
				
				if (noOfTweets >= 10) 
					noOfTweets = 9;
				
				for (Status tweet : tweets.subList(0, noOfTweets)) {
				
					if (!tweet.isRetweeted()) {
						json.append(tweet.getUser().getScreenName(), tweet.getText());
						logger.debug(String.format("@ %s - %s", tweet.getUser().getScreenName(), tweet.getText()));
					}
				}
				
				twtApiQuery.getResultType();
				logger.debug(String.format("TweetsSearchServiceImpl->searchRecentTweetByRepositoryName: twtApiQuery:: %s ",	ResultType.values().toString()));
			
			} while ((twtApiQuery = queryResult.nextQuery()) != null);
			}
		} catch (TwitterException | JSONException  twitterServiceException) {

			logger.error(String.format("Failed to search tweets: " + twitterServiceException.getMessage()));
		}
		return json;
	}
}