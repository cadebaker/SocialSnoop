package apis;

import com.restfb.*;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;

import application.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
//import javax.json.Json;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.json.*;

/*******************************************************************************
 * The facebookProfile class is designed to pull data from a user's Facebook
 * profile, including the user's name, profile picture, profile URL, and the
 * posts from that user
 * 
 * @author Cade Baker
 * @version October 17, 2017.
 ******************************************************************************/
public class FacebookSnooper {

	/* private string to hold the user's Facebook access token */
	private static String MY_ACCESS_TOKEN = "";

	/*
	 * private string to hold the application secret (never changes so its
	 * static)
	 */
	private static final String MY_APP_SECRET = "9e1c68e2f607f6b1be1bbef52913421c";

	/* private FacebookClient object to set up access to the Facebook API */
	private FacebookClient facebookClient;

	/* private User object to set up access to the user's Facebook data */
	private User activeUser;

	/* private arraylist of strings to hold the messages of posts */
	private ArrayList<String> postData = new ArrayList<String>();

	/* private arraylist of strings to hold the storys from the user */
	private ArrayList<String> postStory = new ArrayList<String>();

	/* private arraylist of strings to hold the time of creation of posts */
	private ArrayList<Date> postTime = new ArrayList<Date>();

	/* private arraylist of strings to hold the links to the posts */
	private ArrayList<String> postLink = new ArrayList<String>();

	private User activeUserImages;

	/** private arraylist of profiles created by the api */
	private ArrayList<FacebookProfile> profiles = new ArrayList<FacebookProfile>();

	/**
	 * private instance of the controller class, used to manipulate GUI data
	 **/
	private Controller c;

	/**********************************************************************
	 * facebookProfile is a constructor to setup the fetching of data from
	 * Facebook
	 * 
	 * @param aToken
	 *            the access token for the targeted Facebook user
	 **********************************************************************/
	public FacebookSnooper(String aToken, Controller c) {

		this.c = c;

		// Set the user's access token to the value inputed into the constructor
		MY_ACCESS_TOKEN = aToken;

		// Initialize a Facebook Client using RestFB, the user's access token,
		// and the application secret
		facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, MY_APP_SECRET, Version.VERSION_2_8);

		// Initialize a user object from RestFB
		activeUser = facebookClient.fetchObject("me", User.class);

		getPosts();
	}

	/***********************************************************************
	 * getProfileURL() gets the user's profile URL from Facebook
	 * 
	 * @return returns the user's Facebook URL
	 **********************************************************************/
	public String getProfileURL() {
		// return the string 'fb.com/' followed by the user's ID
		return "fb.com/" + activeUser.getId();
	}

	/***********************************************************************
	 * getProfilePicture() gets the user's profile picture URL from Facebook
	 * 
	 * @return returns the user's Facebook profile picture URL
	 **********************************************************************/
	public String getProfilePicture() {
		// create a JSONObject to pull the user's high quality profile
		// picture URL and filter out the fields that are not the URL
		JsonObject js = facebookClient.fetchObject("/me/picture", JsonObject.class, Parameter.with("type", "large"), // the
																														// image
																														// size
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

	// string to return the name of the user
	public String getName() {
		return activeUser.getName();
	}

	/***********************************************************************
	 * getPosts() gets the user's posts from Facebook and stores them as
	 * FacebookProfile object
	 **********************************************************************/
	public void getPosts() {

		// Create a connection to the Facebook posts for the active user
		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

		// Store the posts from myFeed in the myFeedPage array
		for (List<Post> myFeedPage : myFeed) {
			// Iterate over the list of data from myFeedPage to get single
			// objects
			for (Post post : myFeedPage) {
				try {
					if (c != null) {
						// creates FacebookProfile object
						FacebookProfile p = new FacebookProfile(post, c);

						// adds it to the array
						profiles.add(p);
					} else {
						// store post message in the postData array
						postData.add(post.getMessage());
						// store the story in the postStory array
						postStory.add(post.getStory());
						// store the time the post was created in the postTIme
						// array
						postTime.add(post.getCreatedTime());
						// store the direct link to the post in the postLink
						// array
						postLink.add("fb.com/" + post.getId());
					}

				} catch (Exception e) {
				}

			}

		}
	}

	/******************************************************************************
	 * @return profiles
	 *****************************************************************************/
	public ArrayList<FacebookProfile> getProfiles() {
		return profiles;
	}

	/******************************************************************************
	 * @param profiles
	 *            sets profiles
	 *****************************************************************************/
	public void setProfiles(ArrayList<FacebookProfile> profiles) {
		this.profiles = profiles;
	}

	/***********************************************************************
	 * main(String[] args) tests different aspects of the code above, such as
	 * the ability to use the constructor and the getters
	 * 
	 * @param args
	 *            Default requirement for the running of the program
	 **********************************************************************/
	public static void main(String[] args) {
		// utilize the constructor with a long term access token (Cade's)
		FacebookSnooper fb = new FacebookSnooper(
				"EAAL02oTtWsgBANXt6DOJPxBuvCQZBTFW3y5I4Eny6WNr2gsQHLeFOZBodPHfEa5Gusffv72PRCwSVPeT8LDhsqMzP8qdlGzpvxSePMVmBrTJHMaupYv4GTJqLZAUIa5jCUxijD1zAuhfqJPmmDZAZBExoIcMZAJmZAK5PZBG1aWbvwZDZD", null);

		// get the user's name
		System.out.println(fb.getName());

		// get the profile picture
		System.out.println(fb.getProfilePicture());

	}
