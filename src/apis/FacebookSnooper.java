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

	private static String MY_ACCESS_TOKEN = "";
	private static final String MY_APP_SECRET = "9e1c68e2f607f6b1be1bbef52913421c";
	private FacebookClient facebookClient;
	private User activeUser;
	private ArrayList<FacebookProfile> profiles = new ArrayList<FacebookProfile>();
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
					// FacebookProfile p = new
					// FacebookProfile(post.getMessage(), post.getStory(),
					// post.getCreatedTime(),
					// "fb.com/" + post.getId());

					FacebookProfile p = new FacebookProfile(post, c);

					profiles.add(p);

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
}