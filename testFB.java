package faceBookAndTwitter;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

/***************************************************************
 * This class is used to test out some of the methods in the 
 * restFB Face Book API.
 *
 * @author Anthony Sciarini
 * @version every version
 **************************************************************/
public class testFB {
	
	/*****************
	 * Main Method.
	 * @param args
	 ****************/
	
	public static void main(String [] args){
		
		//Access token to facebook user Anthony Sciarini
		String accessToken = "EAACEdEose0cBAMd8qSwG7sZCmgStnAw6Dqy8fchTzThs4ROIAIkNAzIU0BscCDA481dRm1P5iZCOl2h4ZAq6cyZCnxwBbA4nB5h05IlC1jXzRXrxZBoheykWNnh25sEmBKZAPzYvUtUdeyPWFIZCGSbByb0ZAHSKLIeAD3pPwOUSB4OmTZC0Xa9x8UbItFTLBdZAtrlsle7OUpWgZDZD";
		
		//Constructor that takes a access token in the form of a string a grants access to that 
		//users information on facebook.
		FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_3); 
		
		//creates a object of the user class by assigning a user object a fbClient object
		User me = fbClient.fetchObject("me", User.class);
		
		System.out.println(me.getName());
	}
	
	
}
