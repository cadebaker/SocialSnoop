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

/**************************************************************************************
 * This class is used to search for twitter user profiles by screen name.
 * It does this by setting up a developer access token and then using various
 * methods from the twitter4j software package to pull search results from twitter. 
 * @author Anthony Sciarini
 * @version 10/9/2017 Release 1.0
 ************************************************************************************/
public class TwitterSnooper {
	
	/**Used to set up access token.*/
	private String consumerKey;
	
	/**Used to set up access token*/
	private String consumerSecret;
	
	/**Used to set up access token.*/
	private final String ACCESS_TOKEN = "916313147946004481-mdvtO2ZWeDIWpKg0twt9DzzIeMEkW45";
	
	/**Used to set up access token.*/
	private final String ACCESS_TOKEN_SECRET = "V0NsucYzB7qQm4I3MYWRxSaqjLXnpqPmXrd3U2zoerNsH";
	
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
	 * @throws throws a twitter exception of twitter is currently down.
	 * @param searchInput screen name that the user is searching for.
	 * @return returns a array list of the first 1000 screen names related to search.
	 **********************************************************************************/
	public ArrayList<String> searchUser(String searchInput)throws TwitterException{

		ArrayList<String> userList = new ArrayList<String>();

		for(int page_index = 1; page_index <=  20; page_index++)	//Loops though first 20 pages.
			for(User u  : twitter.searchUsers(searchInput, page_index)){ //Get users on each page.
				userList.add("Screen Name: " + u.getScreenName() + "		Name: " + u.getName());//Gets the name of the user.
			}
		return userList; 	
	}	
	
	/**************************************************************
	 * This method will return a list of the first 1000 Twitter
	 * user profile pictures for the given search input.
	 * @param searchInput User being searched for.
	 * @return ArrayList of Image Icons.
	 *************************************************************/
	public ArrayList<ImageIcon> getUsersProfilePic(String searchInput){
		ArrayList<ImageIcon> picList = new ArrayList<ImageIcon>();
		
		for(int page_index = 1; page_index <=  20; page_index++)
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
	
	public static void main(String [] args) throws TwitterException{
		
		TwitterSnooper test = new TwitterSnooper("hwVajCUF1Izv1p0n7gt51JWc4","Z900AW4C9j9Ygs6n4fWomLjToZ46fLOz9MZNvXosp63wcV5UyJ");
		
		test.getUsersProfilePic("A");	//Returns an array list of ImageIcons, can be displayed with a JLabel.
		
		System.out.println("Searchs results for A ");
		for(String user :  test.searchUser("A"))
			System.out.println("~~~~~ " + user + " ~~~~~");
		
	}
	
}
