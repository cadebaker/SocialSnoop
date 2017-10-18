package apis;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import apis.Tweet;
import apis.TwitterSearch;
import apis.TwitterSnooper;
import application.TwitterProfile;
import twitter4j.TwitterException;

/*****************************************************************
 * This program will test all the classes used to pull data from 
 * twitter users with the twitter4j API.
 * @author Anthony Sciarini
 *
 ******************************************************************/
public class TwitterTest {

	//Set up testing
	private TwitterSnooper test = new TwitterSnooper("jYIuPAqqJtnE8GoIA1NmBXjs3","E3Jym5p4p6rbZhFxNc7FWbJut06eASAF2h5aY2sVoea1Zitear");
	private TwitterSearch search;
	private TwitterProfile profile;
	private Tweet tweet;
	
	public TwitterTest(){
		try {
			 search = test.searchUser("sciarian");
			 profile = search.getProfiles().get(0);
			 tweet = search.tweets.get(0);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Test TwitterSearch.
	
	@Test
	public void testSearchPopulatesProfileList() {
		assertTrue(search.getProfiles().size() > 0);	
	}
	
	@Test
	public void testSearchPopulatesTweetList() {
		assertTrue(search.tweets.size() > 0);	
	}
	
	//Test TwitterProfile.
	
	@Test
	public void testGetUserBio(){
		assertEquals( profile.getBio(), "My Bio");
	}
	
	@Test
	public void testGetUserName(){
		assertEquals(profile.getName(), "Anthony Sciarini");
	}
	
	@Test
	public void testGetUserScreenName(){
		assertEquals(profile.getBio(), "sciarian");
	}
	
	@Test 
	public void getUserTimeLine(){
		ArrayList<Tweet> tweets;
		try {
			tweets = test.getUserTimeLine();
			assertTrue(tweets.size() > 0);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Test tweet
	
	@Test
	public void getTweetTime(){
		assertEquals(tweet.getTweetTime(), "Oct 8");
	}
	
	@Test
	public void getTweetUserName(){
		assertEquals(tweet.getName(), "Anthony Sciarini");
	}
	
	@Test
	public void getTweetUserScreenName(){
		assertEquals(tweet.getScreenName(), "sciarian");
	}
	
	@Test
	public void getUserTweet(){
		assertEquals(tweet.getTweet(), "Hello World");
	}
	
	
	
}
