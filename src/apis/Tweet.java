package apis;

//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import javax.swing.JTextField;

import twitter4j.Status;

/**********************************************************
 * This class will contain a basic information a 
 * for a tweet. This includes the date the tweet was 
 * created, the name and screen name of the user 
 * that tweeted the tweet, and the text of the tweet.
 * @author Anthony Sciarini
 * @version 10/14/2017
 ********************************************************/
public class Tweet {
	
	/**The date the tweet was posted converted to a string for easy use.*/
	private String tweetTime;
	
	/**The name of the twitter user that tweeted the tweet.*/
	private String name;

	/**The screen name of the twitter user that tweeted the tweet.*/
	private String screenName;
	
	/**The test of the tweet.*/
	private String tweet;
	
	/******************************************************************
	 * Constructor for Tweet object. Given a Status s, it will pull
	 * information about the time the tweet was posted. The name and 
	 * screen name of the postee. And the text of the tweet. 
	 * @param s A Status form a user
	 * @param name A name
	 * @param screenName The Twitter screen name
	 ******************************************************************/
	public Tweet(final Status s, final String name, final String screenName) {
		tweetTime = dateToString(s.getCreatedAt()); //Get time tweeted.
		//get the name of the user that posted the tweet
		this.name = name; 
		//get the screen name of the user that posted the tweet.
		this.screenName = screenName; 
		tweet = s.getText();
	}
	/*******************************************
	 * Gets the time the tweet was tweeted.
	 * @return String
	 *******************************************/
	public String getTweetTime() {
		return tweetTime;
	}
	
	/**************************************************
	 * Gets the name of the user who tweeted the post.
	 * @return String
	 **************************************************/
	public String getName() {
		return name;
	}

	/***********************************************************
	 * Gets the screen name of the user that tweeted the post.
	 * @return String
	 ***********************************************************/
	public String getScreenName() {
		return screenName;
	}

	/***********************************
	 * Gets the text of the tweet.
	 * @return String
	 **********************************/
	public String getTweet() {
		return tweet;
	}
	/********************************************************** 
	 * A to string method that will print out information.
	 * @return String 
	 **********************************************************/
	public String toString() {
		return name + " * " + screenName + " * " 
				+ tweetTime + "\n\t" + tweet + "\n";
	}
	
	/****************************************************
	 * Converts a object of Date to a formatted string.
	 * @param date Date that is to be converted.
	 * @return A string representation of the Date.
	 ****************************************************/
	@SuppressWarnings("deprecation")
	private String dateToString(final Date date) {
		
		//Compare current date to the time the other date was created.
		//SimpleDateFormat df = 
			//new SimpleDateFormat("MM/dd/yyyy"); //Format the date.
		Date current = Calendar.getInstance().
				getTime(); //Get the current date.
	
		int x = date.compareTo(current);
		
		
		if (x == 0) { //If the tweet was posted today. 
			//If it was posted within the last hour use minutes.
			if (date.getHours() == 1) { 
				return date.getMinutes() + "m";
			} else {
				//If posted more than an hour ago 
				//then use hours.
				return date.getHours() + "h"; 
			}
			//If the tweet was posted before 
			//current date, then use the month and the day.
		} else if (x < 0) { 
			return date.toString().substring(4, 11);
		} else {
			//If the post was made in the future
			return "FUUUUTURE!!!"; 
		}
	}
	
	/***************************************************************
	 *  Main method. 
	 *  @param args is standard main parameter
	 ***************************************************************/
	public static void main(final String[] args) {
		Date date = Calendar.getInstance().getTime();
		System.out.println(date.toString().substring(4, 11));
	}
}
