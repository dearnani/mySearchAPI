package com.wday.search.api.twitter;

import java.util.List;

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

	public JSONObject searchRecentTweetByRepositoryName(List<String> repositoryNames) throws JSONException {

		logger.info("TweetsSearchServiceImpl->searchRecentTweetByRepositoryName: Before Twitter API Query");

		Twitter twitterAPI = new TwitterFactory().getInstance();
		QueryResult result;
		Query twtApiQuery;

		JSONObject json = new JSONObject();
		try {

			//for (String repoName : repositoryNames) {
				twtApiQuery = new Query("Reactive");
				logger.debug(String.format(
						"TweetsSearchServiceImpl->searchRecentTweetByRepositoryName: repositoryNames:: %s ",
						repositoryNames.toString()));
				do {

					result = twitterAPI.search(twtApiQuery);
					List<Status> tweets = result.getTweets();
					int noOfTweets = tweets.size();
					logger.debug(String.format("@ LIst of Statuses %s -  size: %d",  tweets.toString(), noOfTweets));
					if (noOfTweets >= 10)
						noOfTweets = 9;
					for (Status tweet : tweets.subList(0, noOfTweets)) {
						if (!tweet.isRetweeted()) {

							json.append(tweet.getUser().getScreenName(), tweet.getText());

							logger.debug(String.format("@ %s - %s", tweet.getUser().getScreenName(), tweet.getText()));
						}
					}
					twtApiQuery.getResultType();
					logger.debug(String.format(
							"TweetsSearchServiceImpl->searchRecentTweetByRepositoryName: twtApiQuery:: %s ",
							ResultType.values().toString()));
				} while ((twtApiQuery = result.nextQuery()) != null);

			//}
		} catch (TwitterException te) {

			logger.error(String.format("Failed to search tweets: " + te.getMessage()));
		}
		return json;
	}
}