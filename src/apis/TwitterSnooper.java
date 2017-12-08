package apis;

import java.util.ArrayList;
//import java.util.List;
import java.util.Calendar;
import java.util.Date;

//import javax.swing.ImageIcon;

//import javax.swing.ImageIcon;
//import javax.swing.JFrame;

//import application.TwitterProfile;
//import twitter4j.MediaEntity;
import twitter4j.Paging;
//import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/*******************************************************************************
 * This class is used to search for twitter user profiles by screen name. It
 * does this by setting up a developer access token and then using various
 * methods from the twitter4j software package to pull search results from
 * twitter.
 * 
 * @author Anthony Sciarini
 * @version 10/9/2017 Release 2.0
 ******************************************************************************/
public class TwitterSnooper {

	/** Used to set up access token. */
	private String consumerKey;

	/** Used to set up access token. */
	private String consumerSecret;

	/** Used to set up access token. */
	private static final String ACCESS_TOKEN = 
			"916313147946004481-mdvtO2ZWeDIWpKg0twt9DzzIeMEkW45";

	/** Used to set up access token. */
	private static final String ACCESS_TOKEN_SECRET = 
			"V0NsucYzB7qQm4I3MYWRxSaqjLXnpqPmXrd3U2zoerNsH";

	/** Takes access token strings to gain 
	 * access to twitter4j's capabilities.*/
	private ConfigurationBuilder cb;

	/** An instance of the twitter4j API content. */
	private TwitterFactory tf;

	/** Gets instance of twitter4j API content for Twitter Factory. */
	private twitter4j.Twitter twitter;

	// THE DATA OF THE TIMELINE.

	/** List of the names of the twitter users 
	 * that posted on the timeline. */
	private ArrayList<String> tweetName;

	/** List of the screen names of the twitter 
	 * users that posted on the timeline.*/
	private ArrayList<String> tweetScreenName;

	/** List of the content of each tweet. */
	private ArrayList<String> tweetData;

	/** List of the bios of each twitter user profile. */
	private ArrayList<String> tweetBio;

	/** List of the dates times that the tweets were posted. */
	private ArrayList<String> tweetTime;

	/** List of the profile pictures of each twitter user. */
	private ArrayList<String> tweetProfileImageURL;
	
	/** List of the image URLS of images in tweets.*/
	private ArrayList<String> tweetURL;
	
	/** Integer which describes the size of the list. */
	private int listSize = 0;

	/**********************************************************************
	 * Constructor for Twitter Snooper class. Takes in parameters for a
	 * developer access token and sets up twitter4j package for use.
	 * 
	 * @param consumerKey
	 *            The Key needed to access servers
	 * @param consumerSecret
	 *            A different type of token for Access
	 * @param accessToken
	 * @param accessTokenSecret
	 *********************************************************************/
	public TwitterSnooper(final String consumerKey,
			final String consumerSecret) {

		// Set up configuration builder.
		cb = new ConfigurationBuilder();

		// Input access tokens
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(ACCESS_TOKEN)
				.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

		// Set up twitter factory
		tf = new TwitterFactory(cb.build());

		// Get the current instance of the twitter users information.
		twitter = tf.getInstance();
		
		//Set up array lists
		tweetName = new ArrayList<String>();
		tweetScreenName = new ArrayList<String>();
		tweetData = new ArrayList<String>();
		tweetBio = new ArrayList<String>();
		tweetTime = new ArrayList<String>();
		tweetProfileImageURL = new ArrayList<String>();
		tweetURL = new ArrayList<String>();
	}

	/**********************************************************
	 * Get timeline in an arraylist of tweets.
	 * 
	 * @throws TwitterException
	 *             an exception
	 * 
	 ********************************************************/
	public void getUserTimeLine() throws TwitterException {

		// reset list size
		listSize = 0; 	
		
		// reset list size
		Paging paging = new Paging(1, 50); 
		
		// reset list.
		tweetName.clear(); 
		// reset list.
		tweetScreenName.clear();
		// reset list.
		tweetData.clear(); 	
		// reset list.
		tweetBio.clear(); 	
		// reset list.
		tweetTime.clear(); 	
		// reset list.
		tweetProfileImageURL.clear(); 
		// reset list.
		tweetURL.clear();		
	
		//Get the users screen name.
		String user = twitter.verifyCredentials().getScreenName();
		
		//Get all the the users Tweets.
		for (Status s : twitter.getUserTimeline(user)) {
			
			//Get data.
			User u = s.getUser(); 	
			//Get data. .
			tweetName.add(u.getName()); 	
			//Get data. .
			tweetScreenName.add(u.getScreenName());
			//Get data. .
			tweetData.add(s.getText()); 		
			//Get data. .
			tweetBio.add(u.getDescription()); 
			//Get data. .
			tweetTime.add(dateToString(s.getCreatedAt())); 
			//Get data. .
			tweetProfileImageURL.add(u.getProfileImageURL()); 
			
			
			if (s.getMediaEntities().length == 0) {
				//Get data. .
				tweetURL.add("NOPE");
			} else {						
				String url = 
						//Get data.
						s.getMediaEntities()[0].
						getMediaURL(); 
				//If it is a .jpg add it.
				if (url.endsWith(".jpg") 
						|| url.endsWith(".png") 
						|| url.endsWith(".jpeg")) { 
					//Get data.
					tweetURL.add(url);	
				} else {
					//Get data.
					tweetURL.add("NOPE");	
				}
			}
			listSize++; // The size of the list.
		}
		
		//Get all the tweets in the users TimeLine
		for (Status s : twitter.getHomeTimeline(paging)) {
			//Get data.
			User u = s.getUser(); 			
			//Get data. .
			tweetName.add(u.getName()); 	
			//Get data. .
			tweetScreenName.add(u.getScreenName()); 
			//Get data. .
			tweetData.add(s.getText()); 	
			//Get data. .
			tweetBio.add(u.getDescription()); 
			//Get data. .
			tweetTime.add(dateToString(s.getCreatedAt())); 
			//Get data. .
			tweetProfileImageURL.add(u.getProfileImageURL()); 
			//Get data. .
			
			if (s.getMediaEntities().length == 0) {	
				//Get data. .
				tweetURL.add("NOPE");
			} else {				
				String url = 
						//Get data.
						s.getMediaEntities()[0].
						getMediaURL(); 
				//If it is a .jpg add it.
				if (url.endsWith(".jpg") 
						|| url.endsWith(".png") 
						|| url.endsWith(".jpeg")) {
					tweetURL.add(url);	 
				} else {			 
					tweetURL.add("NOPE");	 
				}
			}
			listSize++;	 // The size of the list.
		}
	}

	/******************************************************
	 * Converts a date to a string.
	 * 
	 * @param date
	 *            The date to be converted to string
	 * @return The string representation of the date
	 *****************************************************/
	private String dateToString(final Date date) {

		// Compare current date to the time the other date was created.
		// SimpleDateFormat df =
		// new SimpleDateFormat("MM/dd/yyyy"); //Format the date.
		Date current = Calendar.getInstance().
				getTime(); // Get the current
								 // date.

		int x = date.compareTo(current);

		if (x == 0) { // If the tweet was posted today.
			if (date.getHours() == 1) { 
			// If it was posted within the last hour use minutes.
				return date.getMinutes() + "m";
			} else {
				return date.getHours() + "h"; 
			//If posted more than an hour ago then use hours.
			}
		} else if (x < 0) { 
		//If the tweet was posted before 
			//current date, then use the month and the day.
			return date.toString().substring(4, 11);
		} else {
			// If the post was made in the future
			return "FUUUUTURE!!!"; 
		}
	}

	/************************************
	 * Gets the size of each array list.
	 * @return integer.
	 ************************************/
	public int getSize() {
		return listSize;
	}

	/*************************************
	 * Sets the size of each array list.
	 * @param listSize is the size of the list
	 ************************************/
	public void setSize(final int listSize) {
		this.listSize = listSize;
	}

	/************************************************************
	 * Main method.
	 * 
	 * @param args
	 *            Standard Main argument
	 * @throws TwitterException for network error
	 ***********************************************************/
	public static void main(final String[] args) throws TwitterException {

		TwitterSnooper test = new TwitterSnooper(
				"sN5hY2x6U4Y5jJEDjMxXyVvb5",
				"hwap0d0rnoup4c0lW6S8ULYMMHX"
				+ "Ku28OseLz9vvhI50t0o9cjJ");

		test.getUserTimeLine();
		
		/*
		 	tweetName.add(u.getName()); 			     
		 			 .
			tweetScreenName.add(u.getScreenName()); 
				      .
			tweetData.add(s.getText()); 	
					  .
			tweetBio.add(u.getDescription()); 	
				      .
			tweetTime.add(dateToString(s.getCreatedAt())); 
				      .
			tweetPic.add(new ImageIcon(u.getProfileImageURL()));
			 		 .
		 */

		System.out.println("HOMETIME LINE: ");
		for (int idx = 0; idx < test.getSize(); idx++) {
			String str = idx + ".)\n";
			str += "Name: " + test.tweetName.get(idx) + "\n";
			str += "ScreenName: " 
			+ test.tweetScreenName.get(idx) + "\n";
			str += "Tweet: " + test.tweetData.get(idx) + "\n";
			str += "Bio: " + test.tweetBio.get(idx) + "\n";
			str += "Time Tweeted: " 
					+ test.tweetTime.get(idx) + "\n";
			str += "Profile pic URL: "  
					+ test.tweetProfileImageURL.
					get(idx) + "\n";
			str += "Media URL: " + test.tweetURL.get(idx) + "\n\n";
			System.out.println(str);
		}

	}

}
