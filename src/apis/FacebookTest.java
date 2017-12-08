package apis;

import static org.junit.Assert.assertTrue;

//import static org.junit.Assert.*;

import org.junit.Test;

//import com.restfb.*;
//import com.restfb.types.FacebookType;
//import com.restfb.types.Post;
//import com.restfb.types.User;
//import com.restfb.json.JsonArray;
//import com.restfb.json.JsonObject;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;


/*****************************************************************
 * This program will test all the classes used to pull data from 
 * Facebook users with the RestFB Facebook GRAPH API wrapper for
 * Java.
 * @author Cade Baker
 *
 ******************************************************************/
public class FacebookTest {

	/**setup the constructor to complete the tests.*/
	private FacebookSnooper test = 
			new FacebookSnooper("EAAL02oTtWsgBANXt6DOJPxBuvCQZBTFW3y5I4Eny6WNr2"
					+ "gsQHLeFOZBodPHfEa5Gusffv72PRCwSVPeT8LDhsqMzP8qdlGzpvxSe"
					+ "PMVmBrTJHMaupYv4GTJqLZAUIa5jCUxijD1zAuhfqJPmmDZAZBExoIc"
					+ "MZAJmZAK5PZBG1aWbvwZDZD", null);
	

	
	/**test the getName() function.*/
	@Test
	public void testGetName() {
		assertTrue(test.getName().contains("Cade Baker"));	
	}
	
	/**test that the profile picture URL is not null.*/
	@Test
	public void testProfilePicURLNotNull() {
		assertTrue(test.getProfilePicture() != null);	
	}
	
	/**test that the profile picture URL contains 'https'.*/
	@Test
	public void testProfilePicURLContainsHTTPS() {
		assertTrue(test.getProfilePicture().contains("https"));	
	}
	
	/**test that the profile picture URL is not null.*/
	@Test
	public void testProfileURLNotNull() {
		assertTrue(test.getProfileURL() != null);	
	}
	
	/**test that the postData has several entries.*/
	@Test
	public void testPostDataEntries() {
		test.getPosts();
		assertTrue(test.getPostData() != null);	
	}
	
	/**test that the postStory has several entries.*/
	@Test
	public void testPostStoryEntries() {
		
		assertTrue(test.getPostStory() != null);	
	}
	
	/**test that the postTime has several entries.*/
	@Test
	public void testPostTimeEntries() {
		
		assertTrue(test.getPostTime() != null);	
	}
	
	/**test that the postLink has several entries.*/
	@Test
	public void testPostLinkEntries() {
		
		assertTrue(test.getPostLink() != null);	
	}
	
	/**test that the first postLink entry has 'fb.com' in it.*/
	@Test
	public void testPostLinkForContent() {
		
		assertTrue(test.getPostLink().get(0).contains("fb.com"));	
	}
		
	
	
	
	
}
