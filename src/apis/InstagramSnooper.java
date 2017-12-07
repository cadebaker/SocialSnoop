package apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstagramSnooper {
	
public static void main(final String[] args) throws IOException, JSONException {	
		
		/** String value that holds the access token. **/
		String Access ="6188961345.8504729.30f2ce5d68654477a79cf32dbd94525b";
		
		/** Url that is the same anytime (besides the access token) to get user information **/
		URL url = new URL("https://api.instagram.com/v1/users/self/?access_token=" + Access);
		
		/**Connection to get access to the Web**/
		URLConnection con = url.openConnection();
	    InputStream is =con.getInputStream();
	    
	     
	    /**BufferedReader to read the online files and put it into a JSON **/
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    
	    JSONObject json = new JSONObject(jsonText);
	    
	    JSONObject j = json.optJSONObject("data");
	    
	    /**HashMap to store important parts of a Instagram Profile **/
	    HashMap<String,String> info = user(j);
	    
	    is.close();
	    
	    System.out.println(info.get("profile picture"));
	        
	}
	
	/*************************************************************************
	 * Method readAll is a method that runs through reading a website
	 * to help convert it into a JSON in the main method.
	 * 
	 * @param rd is a BufferedReader that reads the JSON web file
	 *************************************************************************/
	private static String readAll(final Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	
	/*************************************************************************
	 * Method readAll is a method that runs through reading a website
	 * to help convert it into a JSON in the main method.
	 * 
	 * @param j is the JSON object that holds the 'data' JSONArray
	 * 				within the original JSON object.
	 **************************************************************************/
	private static HashMap<String,String> user(final JSONObject j) throws JSONException {
		
		/**JSONObject.get() returns an object, so object x stores
		 * that value until we can call toString on it.
		 */
		Object x = null;
		
		/**the info Hashmap stores different parts of the instagram 
		 * information for easier access to place into the GUI
		 */
		HashMap<String,String> info = new HashMap<String, String>();
		
		x = j.get("profile_picture");
		info.put("profile picture", x.toString());
		
		x = j.get("full_name");
		info.put("full name", x.toString());
		
		x = j.get("bio");
		info.put("bio", x.toString());
		
		x = j.get("username");
		info.put("username", x.toString());
		
		return info;
	}
	
public static HashMap<String,String> getPosts(String Access) throws IOException, JSONException{
		
		/** String value that holds the access token. **/
		String Acces ="241274222.8504729.5e561ee33eca44b1a675b41af138c063";
		
		/** Url that is the same (besides the access token) to get user information **/
		URL url = new URL("https://api.instagram.com/v1/users/self/media/recent?access_token=" + Access);
		
		/**Connection to get access to the Web**/
		URLConnection con = url.openConnection();
	    InputStream is =con.getInputStream();
	    
	     
	    /**BufferedReader to read the online files and put it into a JSON **/
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    String jsonText = readAll(rd);
	    JSONObject json = new JSONObject(jsonText);
//	    System.out.println(json);
//	    System.out.println(json.get("data"));
//	    System.out.println(json.optJSONObject("data"));
	    JSONArray j = json.optJSONArray("data");
	    
	    System.out.println(j);
	    JSONObject post0 = j.getJSONObject(0);
	    System.out.println(post0);
	    
	    
		HashMap<String,String> posts = new HashMap<String, String>();//getPostInfo(json);
		System.out.println(post0.get("images"));
		posts.put("picture", post0.optJSONObject("images").optJSONObject("standard_resolution").get("url").toString());
		posts.put("caption", post0.optJSONObject("caption").get("text").toString());
		posts.put("likes", post0.optJSONObject("likes").get("count").toString());
		JSONArray people = post0.optJSONArray("users_in_photo");
		posts.put("tagged person 1", people.getJSONObject(0).optJSONObject("user").get("username").toString());
		posts.put("tagged person 2", people.getJSONObject(1).optJSONObject("user").get("username").toString());
		
		return posts;
	}
}
