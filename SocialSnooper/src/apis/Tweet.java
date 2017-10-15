package apis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;

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
	 ******************************************************************/
	public Tweet(Status s, String name, String screenName){
		tweetTime = dateToString(s.getCreatedAt()); //Get time tweeted.
		this.name = name; //get the name of the user that posted the tweet
		this.screenName = screenName; //get the screen name of the user that posted the tweet.
		tweet = s.getText();
	}
	/*******************************************
	 * Gets the time the tweet was tweeted.
	 * @return 
	 *******************************************/
	public String getTweetTime() {
		return tweetTime;
	}
	
	/**************************************************
	 * Gets the name of the user who tweeted the post
	 * @return
	 **************************************************/
	public String getName() {
		return name;
	}

	/***********************************************************
	 * Gets the screen name of the user that tweeted the post.
	 * @return
	 ***********************************************************/
	public String getScreenName() {
		return screenName;
	}

	/***********************************
	 * Gets the text of the tweet.
	 * @return
	 **********************************/
	public String getTweet() {
		return tweet;
	}
	
	public String toString(){
		return name + " * " + screenName + " * " + tweetTime + "\n\t" + tweet + "\n";
	}
	
	/****************************************************
	 * Converts a object of Date to a formatted string.
	 * @param Date Date that is to be converted.
	 * @return A string representation of the Date.
	 ****************************************************/
	@SuppressWarnings("deprecation")
	private String dateToString(Date date){
		
		//Compare current date to the time the other date was created.
		//SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //Format the date.
		Date current = Calendar.getInstance().getTime();		  //Get the current date.
	
		int x= date.compareTo(current);
		
		if(x == 0) //If the tweet was posted today.
			if(date.getHours() == 1) //If it was posted within the last hour use minutes.
				return date.getMinutes() + "m";
			else
				return date.getHours() + "h";//If posted more than an hour ago then use hours.
		else if(x < 0)  //If the tweet was posted before current date, then use the month and the day.
			return date.toString().substring(4,11);
		else
			return "FUUUUTURE!!!"; //If the post was made in the future
	}
	
	public static void main(String [] args){
		Date date = Calendar.getInstance().getTime();
		System.out.println(date.toString().substring(4,11));
	}
}