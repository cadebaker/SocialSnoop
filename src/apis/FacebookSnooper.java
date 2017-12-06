package apis;

//import com.restfb.*;
//import com.restfb.types.FacebookType;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.restfb.Version;

import application.Controller;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;

//import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
//import javax.json.Json;
//import java.io.IOException;
//import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.json.*;

/*******************************************************************************
 * The facebookProfile class is designed to pull data from a user's Facebook
 * profile, including the user's name, profile picture, profile URL, and the
 * posts from that user.
 * 
 * @author Cade Baker
 * @version October 17, 2017.
 ******************************************************************************/
public class FacebookSnooper {

	/** private string to hold the user's Facebook access token. */
	private static String myAccessToken = "";

	/** private string to hold the application secret (never changes so its
	 * static).*/
	private static final String MY_APP_SECRET = 
			"9e1c68e2f607f6b1be1bbef52913421c";

	/** private FacebookClient object to set up access to the Facebook API. */
	private FacebookClient facebookClient;

	/** private User object to set up access to the user's Facebook data. */
	private User activeUser;

	/** private arraylist of strings to hold the messages of posts. */
	private ArrayList<String> postData = new ArrayList<String>();

	/** private arraylist of strings to hold the storys from the user. */
	private ArrayList<String> postStory = new ArrayList<String>();

	/** private arraylist of strings to hold the time of creation of posts. */
	private ArrayList<Date> postTime = new ArrayList<Date>();

	/** private arraylist of strings to hold the links to the posts. */
	private ArrayList<String> postLink = new ArrayList<String>();

	/** private arraylist of profiles created by the api. */
	private ArrayList<FacebookProfile> profiles = 
			new ArrayList<FacebookProfile>();
	/** private arraylist of strings to hold the full pictures to the posts. */
	private ArrayList<String> fullPic = new ArrayList<String>();

	/** private integer that holds how many post the user has made in the provided time-frame */
	private int numPost = 0;


	/**********************************************************************
	 * facebookProfile is a constructor to setup the fetching of data from
	 * Facebook.
	 * 
	 * @param aToken
	 *            the access token for the targeted Facebook user
	 * @param c 
	 *            A call to the conroller class
	 **********************************************************************/
	public FacebookSnooper(final String aToken) {


		// Set the user's access token to the 
		// value inputed into the constructor
		myAccessToken = aToken;

		// Initialize a Facebook Client using RestFB, the user's
		// access token, and the application secret
		facebookClient = new DefaultFacebookClient(myAccessToken, MY_APP_SECRET, Version.VERSION_2_8);

		// Initialize a user object from RestFB
		activeUser = facebookClient.fetchObject("me", User.class);

		getPosts();
	}

	/***********************************************************************
	 * getProfileURL() gets the user's profile URL from Facebook.
	 * 
	 * @return returns the user's Facebook URL
	 **********************************************************************/
	public String getProfileURL() {
		// return the string 'fb.com/' followed by the user's ID
		return "fb.com/" + activeUser.getId();
	}

	/***********************************************************************
	 * getProfilePicture() gets the user's
	 * profile picture URL from Facebook.
	 * 
	 * @return returns the user's Facebook profile picture URL
	 **********************************************************************/
	public String getProfilePicture() {
		// create a JSONObject to pull the user's high quality profile
		// picture URL and filter out the fields that are not the URL
		JsonObject js = facebookClient.fetchObject("/me/picture", 
				JsonObject.class, Parameter.with("type", "large"),
				Parameter.with("redirect", "false"), // don't redirect
				Parameter.with("fields", "url")); // only show URL

		// get the tostring of the JSON Object from above
		String picURL = js.toString();

		// get the index of the 'h' in the URL
		int picURLStart = picURL.lastIndexOf("https");

		// extract the URL from 'http' to the last value of the URL
		// (the 'picURL.length() - 3' filters out quotes and parenthesis
		String picURLFinal = picURL.substring(picURLStart, picURL.length() - 3);

		// return the selected portion of the string
		return picURLFinal;
	}

	/********************************************************
	 * string to return the name of the user.
	 * @return String 
	 *******************************************************/
	public String getName() {
		return activeUser.getName();
	}

	/*********************************************************************
	 * getPosts() gets the user's posts from Facebook and stores them as a
	 * FacebookProfile object
	 ********************************************************************/
	public void getPosts() {

		Date sixMonthsAgo = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L * 7L * 4L * 6L);
		
		// Create a connection to the Facebook posts for the active user
		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class, 
				Parameter.with("fields", "attachments, picture, full_picture, caption, story, description"),
				Parameter.with("limit", 3), Parameter.with("since", sixMonthsAgo));
	
		
		// Store the posts from myFeed in the myFeedPage array
		for (List<Post> myFeedPage : myFeed) {
			// Iterate over the list of data from myFeedPage
			// to get single objects
			for (Post post : myFeedPage) {
					
						// store post message in the 
						// postData array
						postData.add(post.getMessage());
						// store the story in the 
						// postStory array
						postStory.add(post.getStory());
						// store the time the post was created
						// in the Posttime array
						postTime.add(post.getCreatedTime());
						// store the direct link to the post
						// in the postlink array
						postLink.add("fb.com/" + post.getId());
						//store the full puture in the fullPic
						//array
						fullPic.add(post.getFullPicture());
						//increment numPost
						numPost++;
			}
			
		}
		
	}

	/************************************************************
	 * @return profiles
	 ************************************************************/
	public ArrayList<FacebookProfile> getProfiles() {
		return profiles;
	}

	/************************************************************
	 * @param profiles sets profiles
	 ***********************************************************/
	public void setProfiles(final ArrayList<FacebookProfile> profiles) {
		this.profiles = profiles;
	}
	
	/***********************************************************
	 * getPostData() returns the arraylist of postData.
	 * @return returns the postData array
	 ***********************************************************/
	public ArrayList<String> getPostData() {
		return postData;
	}
	
	/*********************************************************
	 * getPostStory() returns the arraylist of postStory.
	 * @return returns the postStory array
	 *********************************************************/
	public ArrayList<String> getPostStory() {
		return postStory;
	}
	
	/********************************************************
	 * getPostTime() returns the arraylist of postTime.
	 * @return returns the postTime array
	 *******************************************************/
	public ArrayList<Date> getPostTime() {
		return postTime;
	}
	
	/*******************************************************
	 * getPostLink() returns the arraylist of postLink.
	 * @return returns the postLink array
	 ******************************************************/
	public ArrayList<String> getPostLink() {
		return postLink;
	}
	
	/******************************************************************************
	 * getFullPic() returns the arraylist of fullPic
	 * @return returns the fullPic array
	 *****************************************************************************/
	public ArrayList<String> getFullPic() {
		return fullPic;
	}

	/******************************************************************************
	 * getNumPost() returns the int numPost
	 * @return the number of posts the user has made
	 *****************************************************************************/
	public int getNumPost() {
		return numPost;
	}

	/*******************************************************
	 * main(String[] args) tests different aspects of the code above,
	 * such as the ability to use the constructor and the getters.
	 * 
	 * @param args
	 *            Default requirement for the running of the program
	 ******************************************************/
	public static void main(final String[] args) {
		//utilize the constructor with a long term access token (Cade's)
		FacebookSnooper fb = new FacebookSnooper(
				"EAAL02oTtWsgBANXt6DOJPxBuvCQZBTFW3y5I4Eny6WNr2gsQHLeFOZBodPHf"
				+ "Ea5Gusffv72PRCwSVPeT8LDhsqMzP8qdlGzpvxSePMVmBrTJHMaupYv4GTJq"
				+ "LZAUIa5jCUxijD1zAuhfqJPmmDZAZBExoIcMZAJmZAK5PZBG1aWbvwZDZD");

		// get the user's name
		System.out.println(fb.getName());

		// get the profile picture
		System.out.println(fb.getProfilePicture());
		


	}
}
