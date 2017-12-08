package apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*******************************************************
 * @author Luke Bassett
 *
 * The InstagramSnooper Class is the main class
 * that handles Instagram data from its API.
 * It handles getting user information and post
 * information from specific access tokens.
 *******************************************************/
public class InstagramSnooper2 {
	
	/** ArrayList to store post information. */
	private static ArrayList<String> post;
	
	/** ArrayList to store user information. */
	private static ArrayList<String> info;
	
	
	
	/*************************************************************
	 * Default Constructor for InstagramSnooper.
	 ************************************************************/
	private InstagramSnooper2() {
		post = new ArrayList<String>();
		info = new ArrayList<String>();
	}
	
	
	/*************************************************************
	 * A main method that currently controls all data 
	 * and function.
	 * @param args as standard argument
	 * @throws IOException for reading url and its information
	 * @throws JSONException in case the JSON object has an error
	 *************************************************************/

	public static void main(final String[] args) throws IOException, JSONException {	
		//URL to access token is : 
			//https://www.instagram.com/oauth/authorize/
			//?client_id=8504729cdd204b3bbab5f59c7a995ba9
			//&redirect_uri=https://
			//bakerservers.com/&response_type=token
		
		/** String value that holds the access token. **/
		String access = 
				"6188961345.8504729.30f2ce5d6865"
				+ "4477a79cf32dbd94525b "; 
		
		/** Url to get user information **/
		URL url = new URL(
				"https://api.instagram.com/v1/users"
				+ "/self/?access_token=" + access);
		
		/**Connection to get access to the Web**/
		URLConnection con = url.openConnection();
	    InputStream is = con.getInputStream(); 
	    
	     
	    /**BufferedReader to read the online files and put it into a JSON**/
	    BufferedReader rd = 
	    		new BufferedReader(
	    				new InputStreamReader(is, 
	    						Charset.forName(
	    								"UTF-8")));
	    String jsonText = readAll(rd);
	    
	    JSONObject json = new JSONObject(jsonText);
	    
	    JSONObject j = json.optJSONObject("data");
	    
	    /**HashMap to store important parts of a Instagram Profile **/
	    //ArrayList<String>  info = new ArrayList<String>();
	    info = user(j);
	    
	    System.out.println(info.get(0));
	    is.close();
	    
	    //System.out.println(info.get("profile picture"));
	   // ArrayList<String> post = new ArrayList<String>();
	    post = getPosts(1);
	    System.out.println(post.get(1));
	    
	    //System.out.println(getUserInfo(2));
	}
	
	/***********************************************************************
	 * Method readAll is a method that runs through reading a website
	 * to help convert it into a JSON in the main method.
	 * 
	 * @param rd is a BufferedReader that reads the JSON web file.
	 * @return String for method
	 * @throws IOException for reading file
	 **********************************************************************/
	private static String readAll(final Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	
	/***********************************************************************
	 * Method readAll is a method that runs through reading a website
	 * to help convert it into a JSON in the main method.
	 * 
	 * @param j is the JSON object that holds the 'data' JSONArray
	 * 				within the original JSON object.
	 * @return ArrayList that holds user information
	 * @throws JSONException in case the JSON object breaks
	 **********************************************************************/
	private static ArrayList<String> user(final JSONObject j) throws JSONException {
		
		/**JSONObject.get() returns an object, so object x stores
		 * that value until we can call toString on it.
		 */
		Object x = null;
		
		/**the info Hashmap stores different parts of the instagram 
		 * information for easier access to place into the GUI
		 */
		ArrayList<String> info = new ArrayList<String>();
		
		x = j.get("profile_picture");
		info.add(x.toString());
		
		x = j.get("full_name");
		info.add(x.toString());
		
		
		x = j.get("bio");
		info.add(x.toString());
		
		x = j.get("username");
		info.add(x.toString());
		
		return info;
	}
/********************************************************
 * 	Method getPosts retrieves information from Instagram
 * and stores the data as an ArrayList.
 * 
 * @param postNum is the number of the post from the most 
 * 			recent post by user.
 * @return ArrayList that holds information 
 * @throws IOException for while reading html from url
 * @throws JSONException in case JSON breaks.
 ********************************************************/
public static ArrayList<String> getPosts(final int postNum) throws IOException, JSONException {
		
		/** String value that holds the access token. **/
		String accessCade = 
				"241274222.8504729.5e561ee33eca44b1a6"
				+ "75b41af138c063";
		
		/** Url to get user information **/
		URL url = new URL(
				"https://api.instagram.com/v1/users/self"
				+ "/media/recent?access_token=" + accessCade);
		
		/**Connection to get access to the Web**/
		URLConnection con = url.openConnection();
	    InputStream is = con.getInputStream();
	    
	     
	    /**BufferedReader to read the online files and put it into a JSON**/
	    BufferedReader rd = 
	    		new BufferedReader(new InputStreamReader(
	    				is, Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    JSONObject json = new JSONObject(jsonText);
//	    System.out.println(json);
//	    System.out.println(json.get("data"));
//	    System.out.println(json.optJSONObject("data"));
	    JSONArray j = json.optJSONArray("data");
	   
	    JSONObject post0 = j.getJSONObject(postNum);
	    
	    
	    
		ArrayList<String> posts = 
				new ArrayList<String>(); 
		
		posts.add(
				post0.optJSONObject("images").
				optJSONObject("standard_resolution").
				get("url").toString());
		posts.add(
				post0.optJSONObject("caption").
				get("text").toString());
		posts.add(
				post0.optJSONObject("likes").
				get("count").toString());
		
		
		return posts;
	}

	/***************************************************************
	 * Gets the particular information the program needs
	 * from the user info.
	 * 
	 * @param index is the information it needs.
	 * @return String value which is the information
	 **************************************************************/
	public static String getUserInfo(final int index) {
		return info.get(index);
	}
	
	/***************************************************************
	 * Gets the particular information the program needs
	 * from the post info.
	 * 
	 * @param index is the information it needs.
	 * @return String value which is the information
	 **************************************************************/
	public static String getPostInfo(final int index) {
		return post.get(index);
	}
}
