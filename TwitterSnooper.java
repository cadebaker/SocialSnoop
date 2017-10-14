package faceBookAndTwitter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/*************************************************************************************
 * This class is used to search for twitter user profiles by screen name.
 * It does this by setting up a developer access token and then using various
 * methods from the twitter4j software package to pull search results from twitter. 
 * @author Anthony Sciarini
 * @version 10/9/2017 Release 1.0
 ************************************************************************************/
public class TwitterSnooper {
	
	/**
	 * What do we want from a user.
	 * 		-Profile pic.
	 * 			-u.getProfileImage
	 * 		-Name.
	 * 			-u.geName
	 * 		-Screen name.
	 * 			-u.getScreenName
	 * 		-List of status's on timeline.
	 * 				-get each status with created at. s.created_at
	 * 				-get who posted the status. s.
	 * 				-post the status
	 */

	
	/**Used to set up access token.*/
	private String consumerKey;
	
	/**Used to set up access token*/
	private String consumerSecret;
	
	/**Used to set up access token.*/
	private final String ACCESS_TOKEN = "";
	
	/**Used to set up access token.*/
	private final String ACCESS_TOKEN_SECRET = "";
	
	/**Takes access token strings to gain access to twitter4j's capabilities.*/
	private ConfigurationBuilder cb;
	
	/**An instance of the twitter4j API content.*/
	private TwitterFactory tf;
	
	/**Gets instance of twitter4j API content for Twitter Factory.*/
	private twitter4j.Twitter twitter;
	
	/*******************************************************************************************
	 * Constructor for Twitter Snooper class. Takes in parameters for a developer access token
	 * and sets up twitter4j package for use. 
	 * @param consumerKey
	 * @param consumerSecret
	 * @param accessToken
	 * @param accessTokenSecret
	 ********************************************************************************************/
	public TwitterSnooper(String consumerKey, String consumerSecret){
		//Set up configuration builder.
		cb = new ConfigurationBuilder();
		
		//Input access tokens
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(consumerKey)
		.setOAuthConsumerSecret(consumerSecret)
		.setOAuthAccessToken(ACCESS_TOKEN)
		.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		//Set up twitter factory
		tf = new TwitterFactory(cb.build());
		
		//Set up twitter4j object
		twitter = tf.getInstance();	
	}
	
	/**********************************************************************************
	 * Search for users with screenName specified by the input parameter.
	 * @throws throws a twitter exception if twitter is currently down.
	 * @param searchInput screen name that the user is searching for.
	 * @return returns a array list of the first 1000 screen names related to search.
	 **********************************************************************************/
	public Search searchUser(String searchInput)throws TwitterException{

		ArrayList<Profile> users = new ArrayList<Profile>();
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		
		for(int page_index = 1; page_index <=  5; page_index++)	//Loops though first 20 pages.
			for(User u  : twitter.searchUsers(searchInput, page_index)){ //Get users on each page.
				users.add(new Profile(u));//Gets the name of the user.
				tweets.add(new Tweet(u.getStatus(),u.getName(), u.getScreenName()));//Gets the last post from the user.
			}
		return new Search(users,tweets); 	
	}
	
	/********************************************************************************
	 * Searches for one user. Used mostly for testing.
	 * @param searchInput	The screen name or name of the user being searched for.
	 * @return				The user 
	 * @throws TwitterException If twitter is down.
	 ********************************************************************************/
	public User searchAUser(String searchInput)throws TwitterException{
		return twitter.lookupUsers(searchInput).get(0);
	}
	
	/**********************************************************************
	 * Given a user, u, this method will return the last tweet made by that
	 * user.
	 * @param u a User.
	 * @return ArrayList of the users tweets.
	 ***********************************************************************/
	/*public Tweet getTweet(User u){
		return new Tweet(u.getStatus());
	}*/
	
	/**************************************************************
	 * This method will return a list of the first 1000 Twitter
	 * user profile pictures for the given search input.
	 * @param searchInput User being searched for.
	 * @return ArrayList of Image Icons.
	 *************************************************************/
	/*
	public ArrayList<ImageIcon> getUsersProfilePic(String searchInput){
		ArrayList<ImageIcon> picList = new ArrayList<ImageIcon>();
		
		for(int page_index = 1; page_index <=  5; page_index++)
			try {
				for(User u  : twitter.searchUsers(searchInput, page_index)){
					picList.add(new ImageIcon(u.getProfileImageURL()));
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return picList; 	
	}
	*/
	public static void main(String [] args) throws TwitterException{
		
		TwitterSnooper test = new TwitterSnooper("Qaw0NK0hKJtVBGddXwMTtmTdT","YZ6nRlV9rhqAr5kK9pa1n67NJgS9SlYREVIlri8qOyueymhwgi");
		
		//test.getUsersProfilePic("A");	//Returns an array list of ImageIcons, can be displayed with a JLabel.
		
		
		System.out.println("Searchs results for A ");
		Search search = test.searchUser("A");
		for(Profile p:  search.profiles)
			System.out.println("~~~~~ " +"\nName: " +p.getName() + "\nScreen name: " + p.getScreenName()  
			+ "\nBio: " + p.getBio() + "\nLast Status: " + search.tweets.get(search.profiles.indexOf(p)).toString() + "\n~~~~~");
			
	}
	
}
