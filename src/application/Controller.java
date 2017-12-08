package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.json.JSONException;

import com.restfb.exception.FacebookOAuthException;

import apis.FacebookProfile;
import apis.FacebookSnooper;
import apis.InstagramProfile;
import apis.InstagramSnooper;
import apis.TwitterProfile;
import apis.TwitterSnooper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import twitter4j.TwitterException;

/*******************************************************************************
 * Class that handles how the GUI interacts with the APIs
 *
 * @author Logan Karney
 ******************************************************************************/
public class Controller {

	/** Social Media buttons **/
	@FXML
	private Button instagramButton, faceBookButton, twitterButton, settingsButton, siteButton;

	/** Radio Button toggle group **/
	@FXML
	private ToggleGroup profile;

	/** Radio Buttons that determine which profile is being shown **/
	@FXML
	private RadioButton profile1Button, profile2Button, profile3Button, profile4Button, profile5Button, profile6Button,
			profile7Button, profile8Button, profile9Button, profile10Button;

	/** Profile Names, updated from the DataBase object **/
	@FXML
	private Text profile1Name, profile2Name, profile3Name, profile4Name, profile5Name, profile6Name, profile7Name,
			profile8Name, profile9Name, profile10Name;

	/** Profile Twitter URLS, taken from the DataBase object **/
	@FXML
	private Text profile1T, profile2T, profile3T, profile4T, profile5T, profile6T, profile7T, profile8T, profile9T,
			profile10T;

	/** Profile Instagram URLS, taken from the DataBase object **/
	@FXML
	private Text profile1I, profile2I, profile3I, profile4I, profile5I, profile6I, profile7I, profile8I, profile9I,
			profile10I;

	/** Profile FaceBook URLS, taken from the DataBase object **/
	@FXML
	private Text profile1F, profile2F, profile3F, profile4F, profile5F, profile6F, profile7F, profile8F, profile9F,
			profile10F;

	/** Object that holds all profile information **/
	@FXML
	private TitledPane profile1Data, profile2Data, profile3Data, profile4Data, profile5Data, profile6Data, profile7Data,
			profile8Data, profile9Data, profile10Data;

	/** Object that holds the TitledPanes **/
	@FXML
	private Accordion accordian;

	/** Center object that displays API results **/
	@FXML
	private VBox displayBox;

	/** Used to scroll through the API results **/
	@FXML
	private ScrollPane scroller;

	/** Handles the local saving and loading of user data **/
	private DataTable table;

	/** Enum used to determine which API is being used **/
	private SocialFilter filter;

	/** ProfileImage URL for the current facebookUser **/
	private Image fbProfilePicture;

	/******************************************************************************
	 * Constructor that loads any previously saved data
	 *****************************************************************************/
	public Controller() {
		table = new DataTable("savedprofiles.txt");

		filter = SocialFilter.FACEBOOK;

	}

	/******************************************************************************
	 * Sets preferences
	 *****************************************************************************/
	public void initialize() {
		scroller.setFitToWidth(true);

		for (int i = 0; i < getProfileNames().length; i++) {
			getProfileNames()[i].setText(table.getProfileNames().get(i));
		}

		// loading of custom font used in GUI
		Font.loadFont(Main.class.getResource("Cruiser.TTF").toExternalForm(), 10);

		faceBookButton.setEffect(new DropShadow());
	}

	/******************************************************************************
	 * Method called when a Social Media button is pressed
	 *
	 * @param e
	 *            button used
	 *****************************************************************************/
	@FXML
	private void buttonClicked(ActionEvent e) {

		faceBookButton.setEffect(null);
		twitterButton.setEffect(null);
		instagramButton.setEffect(null);

		if (e.getSource() == faceBookButton) {
			faceBookButton.setEffect(new DropShadow());

			filter = SocialFilter.FACEBOOK;
		} else if (e.getSource() == twitterButton) {
			twitterButton.setEffect(new DropShadow());

			filter = SocialFilter.TWITTER;
		} else if (e.getSource() == instagramButton) {
			instagramButton.setEffect(new DropShadow());
			filter = SocialFilter.INSTAGRAM;
		}

		// setEffect(new DropShadow());

		if (profile.getSelectedToggle() != null)
			radioButtonClicked();

	}

	/******************************************************************************
	 * Calls the DataTable display method
	 *
	 * @param e
	 *****************************************************************************/
	@FXML
	private void settingsButtonClicked(ActionEvent e) {
		table.display(this);
	}

	/******************************************************************************
	 * Loads bakerservers.com on user's default browser
	 *
	 * @param e
	 * @throws URISyntaxException
	 *****************************************************************************/
	@FXML
	private void siteButtonClicked(ActionEvent e) throws URISyntaxException {

		// answer provided by Kevin Reynolds at
		// https://stackoverflow.com/questions/24202337/javafx-open-url-in-chrome-browser
		try {
			Desktop.getDesktop().browse(new URI("https://bakerservers.com/"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/******************************************************************************
	 * Method that determines which Radio Button was clicked, and shows the
	 * correct data
	 *****************************************************************************/
	@FXML
	private void radioButtonClicked() {

		int id = getRadioId();

		// erases any previous results
		displayBox.getChildren().clear();
		if (filter == SocialFilter.FACEBOOK) {
			try {	
				
				//Searches for facebook data based on the profile
				FacebookSnooper fb = new FacebookSnooper(table.getFaceBookKeys().get(id));

				for (int i = 0; i < fb.getNumPost(); i++) {
					
					//creates a GUI component for each post found
					FacebookProfile fbP = new FacebookProfile(fb.getProfilePicture(), fb.getName(),
							fb.getPostStory().get(i), fb.getFullPic().get(i), fb.getPostTime().get(i).toString());

					displayBox.getChildren().addAll(fbP.getPane());

					VBox.setMargin(fbP.getPane(), new Insets(0, 0, 5, 0));
					
					getProfileF()[id].setText(fb.getProfileURL());
				}

			} catch (FacebookOAuthException e) {
			}

			// loads facebook information
		} else if (filter == SocialFilter.INSTAGRAM) {

			try {
				
				//searches for instagram data based on the profile
				InstagramSnooper iS = new InstagramSnooper(table.getInstagramKeys().get(id));

				HashMap<String, String> info = iS.getInfo();

				String profilePic = info.get("profile picture");

				for (int i = 0; i < 5; i++) {

					
					//creates a GUI component for each post found
					InstagramProfile iP = new InstagramProfile(info.get("username"), profilePic,
							"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/22069552_2021124434784827_6245902769404772352_n.jpg",
							"caption", "500");

					displayBox.getChildren().add(iP.getColumn());
				}

			} catch (Exception e) {
			}

		} else if (filter == SocialFilter.TWITTER) {
			
			//searches for twitter data
			TwitterSnooper test = new TwitterSnooper("sN5hY2x6U4Y5jJEDjMxXyVvb5",
					"hwap0d0rnoup4c0lW6S8ULYMMHXKu28OseLz9vvhI50t0o9cjJ");

			try {
				test.getUserTimeLine();
				for (int i = 0; i < test.getSize(); i++) {
					
					//for each tweet in the feed, creates a GUI component
					TwitterProfile tP = new TwitterProfile(test.getTweetName().get(i), test.getTweetScreenName().get(i),
							test.getTweetData().get(i), test.getTweetTime().get(i),
							test.getTweetProfileImageURL().get(i), test.getTweetURL().get(i));

					displayBox.getChildren().add(tP.getPane());

				}
			} catch (TwitterException e) {
			}
		}
	}

	/******************************************************************************
	 * Updates the data within the TitledPanes
	 *****************************************************************************/
	@FXML
	private void updateAccordians() {
		try {
			// gets the expanded pane, if there is one
			String id = accordian.getExpandedPane().getId();

			// pulls RadioButton number from its id
			int i = Integer.parseInt(id.substring(7, id.indexOf("Data"))) - 1;
			table.load("savedprofiles.txt");

			// pulls relevant data from the DataTable
			getProfileT()[i].setText(table.getTwitterURLs().get(i));
			getProfileI()[i].setText(table.getInstagramURLs().get(i));
			getProfileF()[i].setText(table.getFaceBookURLs().get(i));

		} catch (NullPointerException e) {
			// catches any errors sent when a TitledPane is being closed
		}

	}

	/******************************************************************************
	 * @return Text[] of the profile names
	 *****************************************************************************/
	public Text[] getProfileNames() {
		Text[] rtn = { profile1Name, profile2Name, profile3Name, profile4Name, profile5Name, profile6Name, profile7Name,
				profile8Name, profile9Name, profile10Name };

		return rtn;
	}

	/******************************************************************************
	 * @return Text[] of the profile Twitter URLs
	 *****************************************************************************/
	public Text[] getProfileT() {
		Text[] rtn = { profile1T, profile2T, profile3T, profile4T, profile5T, profile6T, profile7T, profile8T,
				profile9T, profile10T };

		return rtn;
	}

	/******************************************************************************
	 * @return Text[] of the profile Instagram URLs
	 *****************************************************************************/
	public Text[] getProfileI() {
		Text[] rtn = { profile1I, profile2I, profile3I, profile4I, profile5I, profile6I, profile7I, profile8I,
				profile9I, profile10I };

		return rtn;
	}

	/******************************************************************************
	 * @return Text[] of the profile FaceBook URLs
	 *****************************************************************************/
	public Text[] getProfileF() {
		Text[] rtn = { profile1F, profile2F, profile3F, profile4F, profile5F, profile6F, profile7F, profile8F,
				profile9F, profile10F };

		return rtn;
	}

	/******************************************************************************
	 * Method that finds the numerical value of the Radio Button that is active
	 *
	 * @return RadioButtonIdNum
	 *****************************************************************************/
	private int getRadioId() {
		int rtn = 0;
		try {

			// gets selected radio button's number
			String id = profile.getSelectedToggle().toString();

			rtn = Integer.parseInt(id.substring(22, id.indexOf("Button", 22)));

		} catch (NullPointerException e) {
			rtn = -1;
		}

		return rtn - 1;
	}

	/******************************************************************************
	 * @return getFbProfilePicture
	 *****************************************************************************/
	public Image getFbProfilePicture() {
		return fbProfilePicture;
	}
}
