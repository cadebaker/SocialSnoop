package application;

import com.restfb.exception.FacebookOAuthException;

import apis.FacebookProfile;
import apis.FacebookSnooper;
import apis.TwitterSearch;
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
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import twitter4j.TwitterException;

/***************************************************************
 * Class that handles how the GUI interacts with the APIs.
 *
 * @author Logan Karney, Anthony Sciarini
 ***************************************************************/
public class Controller {

	/** Social Media buttons. **/
	@FXML
	private Button instagramButton, faceBookButton, twitterButton, settingsButton;

	/** Radio Button toggle group. **/
	@FXML
	private ToggleGroup profile;

	/** Radio Buttons that determine which profile is being shown. **/
	@FXML
	private RadioButton profile1Button, profile2Button, profile3Button, profile4Button, profile5Button, profile6Button,
			profile7Button, profile8Button, profile9Button, profile10Button;

	/** Profile Names, updated from the DataBase object. **/
	@FXML
	private Text profile1Name, profile2Name, profile3Name, profile4Name, profile5Name, profile6Name, profile7Name,
			profile8Name, profile9Name, profile10Name;

	/** Profile Twitter URLS, taken from the DataBase object. **/
	@FXML
	private Text profile1T, profile2T, profile3T, profile4T, profile5T, profile6T, profile7T, profile8T, profile9T,
			profile10T;

	/** Profile Instagram URLS, taken from the DataBase object. **/
	@FXML
	private Text profile1I, profile2I, profile3I, profile4I, profile5I, profile6I, profile7I, profile8I, profile9I,
			profile10I;

	/** Profile FaceBook URLS, taken from the DataBase object. **/
	@FXML
	private Text profile1F, profile2F, profile3F, profile4F, profile5F, profile6F, profile7F, profile8F, profile9F,
			profile10F;

	/** Object that holds all profile information. **/
	@FXML
	private TitledPane profile1Data, profile2Data, profile3Data, profile4Data, profile5Data, profile6Data, profile7Data,
			profile8Data, profile9Data, profile10Data;

	/** Object that holds the TitledPanes. **/
	@FXML
	private Accordion accordian;

	/** Center object that displays API results. **/
	@FXML
	private VBox displayBox;

	/** Used to scroll through the API results. **/
	@FXML
	private ScrollPane scroller;

	/** Handles the local saving and loading of user data. **/
	private DataTable table;

	/** Enum used to determine which API is being used. **/
	private SocialFilter filter;

	/** ProfileImage URL for the current facebookUser. **/
	private Image fbProfilePicture;

	/**********************************************************
	 * Constructor that loads any previously saved data.
	 **********************************************************/
	public Controller() {
		table = new DataTable("savedprofiles.txt");
		filter = SocialFilter.FACEBOOK;
	}

	/*********************************************************
	 * Sets preferences.
	 *********************************************************/
	public void initialize() {
		scroller.setFitToWidth(true);

		for (int i = 0; i < getProfileNames().length; i++) {
			getProfileNames()[i].setText(table.getProfileNames().get(i));
		}
	}

	/*********************************************************
	 * Method called when a Social Media button is pressed.
	 *
	 * @param e
	 *            button used
	 ********************************************************/
	@FXML
	private void buttonClicked(final ActionEvent e) {
		if (e.getSource() == faceBookButton) {
			filter = SocialFilter.FACEBOOK;
		} else if (e.getSource() == twitterButton) {
			filter = SocialFilter.TWITTER;
			displayBox.getChildren().clear();
		} else if (e.getSource() == instagramButton) {
			filter = SocialFilter.INSTAGRAM;
		}

		if (profile.getSelectedToggle() != null) {
			radioButtonClicked();
		}

	}

	/********************************************************
	 * Calls the DataTable display method.
	 *
	 * @param e as a button-click event
	 ********************************************************/
	@FXML
	private void settingsButtonClicked(final ActionEvent e) {
		table.display(this);
	}

	/********************************************************
	 * Method that determines which Radio Button was clicked,
	 * and shows the correct data.
	 ********************************************************/
	@FXML
	private void radioButtonClicked() {

		int id = getRadioId();

		// erases any previous results
		displayBox.getChildren().clear();
		if (filter == SocialFilter.FACEBOOK) {
			try {
				FacebookSnooper fb = new FacebookSnooper(table.getFaceBookKeys().get(id), this);

				fbProfilePicture = new Image(fb.getProfilePicture());
				fb.getPosts();

				for (FacebookProfile p : fb.getProfiles()) {

					p.setImg(fbProfilePicture);

					HBox box = p.getHBox();

					if (box == null) {
						System.out.println("HERE");
					}

					if (box != null) {
						HBox.setMargin(box, new Insets(0, 0, 20, 0));

						displayBox.getChildren().addAll(box);
					}
				}
			} catch (FacebookOAuthException e) {
			}

			// loads facebook information
		} else if (filter == SocialFilter.INSTAGRAM) {
			return;
			// loads instagram information
		} else if (filter == SocialFilter.TWITTER) {

			// Instantiates Twitter Api object
			TwitterSnooper snoop = new TwitterSnooper("FhCBvdpzex12AQmkQMKEobkei",
					"8tGcCduevsMdEWSL2K7uZS4gHiGP8v9U5lswJ3SMogXbigu4Wy");
			try {
				if (!table.getTwitterNames().get(getRadioId()).equals("TwitterName")) {
					TwitterSearch search = snoop.searchUser(table.getTwitterNames().get(getRadioId()));
					for (TwitterProfile p : search.getProfiles()) {

						// adds Twitter results to the results area
						displayBox.getChildren().addAll(p.getPane());
					}
				}
			} catch (TwitterException e) {
				// fires when Twitter.com is unavailable
				displayBox.getChildren().addAll(new Text("Error contacting twitter"));
			} catch (NullPointerException e) {
			}
		}
	}

	/***********************************************************
	 * Updates the data within the TitledPanes.
	 ***********************************************************/
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

	/************************************************************
	 * @return Text[] of the profile names.
	 ************************************************************/
	public Text[] getProfileNames() {
		Text[] rtn = {profile1Name, profile2Name, profile3Name, profile4Name, profile5Name, profile6Name, profile7Name,
				profile8Name, profile9Name, profile10Name };

		return rtn;
	}

	/************************************************************
	 * @return Text[] of the profile Twitter URLs.
	 ************************************************************/
	public Text[] getProfileT() {
		Text[] rtn = {profile1T, profile2T, profile3T, profile4T, profile5T, profile6T, profile7T, profile8T,
				profile9T, profile10T };

		return rtn;
	}

	/*************************************************************
	 * @return Text[] of the profile Instagram URLs.
	 *************************************************************/
	public Text[] getProfileI() {
		Text[] rtn = {profile1I, profile2I, profile3I, profile4I, profile5I, profile6I, profile7I, profile8I,
				profile9I, profile10I };

		return rtn;
	}

	/*************************************************************
	 * @return Text[] of the profile FaceBook URLs.
	 *************************************************************/
	public Text[] getProfileF() {
		Text[] rtn = {profile1F, profile2F, profile3F, profile4F, profile5F, profile6F, profile7F, profile8F,
				profile9F, profile10F };

		return rtn;
	}

	/********************************************************************
	 * Method that finds the numerical value of the
	 * Radio Button that is active.
	 *
	 * @return RadioButtonIdNum
	 ******************************************************************/
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

	/*******************************************************************
	 * @return getFbProfilePicture.
	 ******************************************************************/
	public Image getFbProfilePicture() {
		return fbProfilePicture;
	}
	
}
