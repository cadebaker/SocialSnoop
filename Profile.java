package faceBookAndTwitter;

import javax.swing.ImageIcon;

import twitter4j.User;

/***************************************************************
 * This class pulls generic information from a twitter users
 * profile for easy access.
 * @author Anthony Sciarini
 * @version 10/13/2017
 **************************************************************/
public class Profile {
	
	/**The first and last name of the twitter user.*/
	private String name;
	
	/**The screen name of the twitter profile.*/
	private String screenName;

	/**The users profile picture.*/
	private ImageIcon profilePic;
	
	/**The users bio.*/
	private String bio;
	
	/**The URL of the users home twitter page.*/
	private String url;
	
	/**********************************************
	 * Takes a object of User, u, and pulls 
	 * profile information from the user object.
	 * @param u A user.
	 *********************************************/
	public Profile(User u){
		name = u.getName();									//Gets the name of the user.
		screenName = u.getScreenName();						//Gets the screen name of the user.
		profilePic = new ImageIcon( u.getProfileImageURL());//Gets the profile image of the user.  
		bio = u.getDescription();							//Gets the user description.
		url = u.getURL();									//Gets the URL of the user's twitter page.
	}

	/******************************
	 * Gets the name of the user.
	 * @return A string of holding the users name.
	 *****************************/
	public String getName() {
		return name;
	}

	/****************************************************
	 * Gets the screen name of the user
	 * @return A string holding the users screen name.
	 ***************************************************/
	public String getScreenName() {
		return screenName;
	}
	
	/***************************************************
	 * Gets a string holding the holding the users bio.
	 * @return A string holding the users screen name.
	 **************************************************/
	public String getBio() {
		return bio;
	}
	
	/*******************************************************
	 * Gets the profile picture of the user.
	 * @return a ImageIcon holding the users profile pic.
	 ******************************************************/
	public ImageIcon getProfilePic(){
		return profilePic;
	}
	
	/*******************************************
	 * Gets the URL to this users home page.
	 * @return the URL converted to a string.
	 *******************************************/
	public String getURL(){
		return url;
	}
	
}
