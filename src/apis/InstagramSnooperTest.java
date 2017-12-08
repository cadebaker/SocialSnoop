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
	 *         The InstagramSnooper Class is the main class that handles Instagram
	 *         data from its API. It handles getting user information and post
	 *         information from specific access tokens.
	 *******************************************************/
	public class InstagramSnooperTest {

		String accessCode;
		
		URL url;
		
		/** ArrayList to store post information. */
		private static ArrayList<String> posts;
		
		private static ArrayList<String> captions;
		
		private static ArrayList<String> likes;
		
		private static ArrayList<String> user;
		
		JSONObject json;
		
		JSONObject jObject;
		
		JSONArray jArray;
	

		/** ArrayList to store user information. */
		private static ArrayList<String> info;

		/*************************************************************
		 * Default Constructor for InstagramSnooper.
		 ************************************************************/
		private InstagramSnooperTest() throws IOException, JSONException {
			
			/** String value that holds the access token. **/
			accessCode = "241274222.8504729.5e561ee33eca44b1a6" + "75b41af138c063";

			/** Url to get user information **/
			url = new URL("https://api.instagram.com/v1/users/self" + "/media/recent?access_token=" + accessCode);

			/** Connection to get access to the Web **/
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();

			/** BufferedReader to read the online files and put it into a JSON **/
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			
			json = new JSONObject(jsonText);

			jObject = json.optJSONObject("data");
			
			jArray = json.optJSONArray("data");
			
		
			posts = new ArrayList<String>();
			captions = new ArrayList<String>();
			likes = new ArrayList<String>();
			
			//info = user(jObject);
			
			//System.out.println(info.get(0));
//			getProfilePicture();
//			getProfileName();
//			getProfileBio();
			getUserName();
			
			getPosts();
			System.out.println(posts.get(0));
			System.out.println(captions.get(0));
			System.out.println(likes.get(0));
			
			System.out.println();
			
			System.out.println(posts.get(1));
			System.out.println(captions.get(1));
			System.out.println(likes.get(1));

		}

		/*************************************************************
		 * A main method that currently controls all data and function.
		 * 
		 * @param args
		 *            as standard argument
		 * @throws IOException
		 *             for reading url and its information
		 * @throws JSONException
		 *             in case the JSON object has an error
		 *************************************************************/

		public static void main(final String[] args) {
			try {
				InstagramSnooperTest iS = new InstagramSnooperTest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/***********************************************************************
		 * Method readAll is a method that runs through reading a website to help
		 * convert it into a JSON in the main method.
		 * 
		 * @param rd
		 *            is a BufferedReader that reads the JSON web file.
		 * @return String for method
		 * @throws IOException
		 *             for reading file
		 **********************************************************************/
		private static String readAll(final Reader rd) throws IOException {
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			return sb.toString();
		}


		public String getProfilePicture() throws JSONException{
			/**
			 * JSONObject.get() returns an object, so object x stores that value
			 * until we can call toString on it.
			 */
			Object x = null;

			/**
			 * the info Hashmap stores different parts of the instagram information
			 * for easier access to place into the GUI
			 */
			ArrayList<String> info = new ArrayList<String>();

			x = jObject.get("profile_picture");
			info.add(x.toString());

			return info.get(0);
		}
			
			
		
		
		public String getProfileName() throws JSONException{
			Object x = null;
			x = jObject.get("full_name");
			return x.toString();
		}
		
		public String getProfileBio() throws JSONException{
			Object x = null;
			x = jObject.get("bio");
			return x.toString();
		}
		
		public String getUserName() throws JSONException{
			Object x = null;
			x = jObject.get("username");
			return x.toString();
		}
		
		
		
//		private static ArrayList<String> user(final JSONObject j) throws JSONException {
//
//			/**
//			 * JSONObject.get() returns an object, so object x stores that value
//			 * until we can call toString on it.
//			 */
//			Object x = null;
//
//			/**
//			 * the info Hashmap stores different parts of the instagram information
//			 * for easier access to place into the GUI
//			 */
//			ArrayList<String> info = new ArrayList<String>();
//
//			x = j.get("profile_picture");
//		    info.add(x.toString());
//
//			x = j.get("full_name");
//			info.add(x.toString());
//			System.out.println(x.toString());
//
//			x = j.get("bio");
//			info.add(x.toString());
//
//			x = j.get("username");
//			info.add(x.toString());
//
//			return info;
//		}
		
		/********************************************************
		 * Method getPosts retrieves information from Instagram and stores the data
		 * as an ArrayList.
		 * 
		 * @param postNum
		 *            is the number of the post from the most recent post by user.
		 * @return ArrayList that holds information
		 * @throws IOException
		 *             for while reading html from url
		 * @throws JSONException
		 *             in case JSON breaks.
		 ********************************************************/
		public void getPosts() throws IOException, JSONException {

			for(int i = 0; i < 20; i++){
				JSONObject post0 = jArray.getJSONObject(i);

				posts.add(post0.optJSONObject("images").optJSONObject("standard_resolution").get("url").toString());
				captions.add(post0.optJSONObject("caption").get("text").toString());
				likes.add(post0.optJSONObject("likes").get("count").toString());
			}

			
		}

	
}
