package apis;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import twitter4j.User;

/*******************************************************************************
 * Creates a user profile generated from data taken from the Twitter api.
 *
 * @author Logan Karney
 ******************************************************************************/
public class TwitterTest {

	/**BorderPane variable for visual.*/
	private BorderPane pane;
	/** The first and last name of the twitter user. */
	private Text name;
	// private String name, screenName, bio, tweetDate;

	/** The screen name of the twitter profile. */
	private Text screenName;

	/** The users bio. */
	private Text bio;

	/** The users profile picture. */
	private ImageView profilePic;
	/** The users post picture. */
	private Image image;

	/** The URL of the users home twitter page. */
	private String url;

	// private ImageIcon profilePic;

	/**********************************************
	 * Takes a object of User, u, and pulls profile
	 *  information from the user object.
	 * 
	 * @param u
	 *            A user.
	 *********************************************/
	public TwitterTest(final User u) {
		// name = new Text(u.getName()); // Gets the name of the user.
		name = new Text(u.getName());
		// screenName = new Text(u.getScreenName()); 
		// Gets the screen name of
		// the
		// user.
		screenName = new Text(u.getScreenName());
		// bio = new Text(u.getDescription()); 
		// Gets the user description.
		setBio(new Text(u.getDescription()));
		//url = u.getURL(); // Gets the URL of the user's twitter page.
		image = new Image(u.getBiggerProfileImageURL());

		profilePic = new ImageView(image);
		// https://stackoverflow.com/questions/
		//27894945/how-do-i-resize-an-imageview-image-in-javafx,
		// by James_D
		profilePic.setFitHeight(100);
		profilePic.setFitWidth(100);

		profilePic.setPreserveRatio(true);
		BorderPane.setMargin(profilePic, new Insets(12, 12, 8, 12));

		name.setId("name-text");

		VBox textHolder = new VBox();
		textHolder.getChildren().addAll(name, getBio());
		VBox.setMargin(name, new Insets(10, 10, 0, 0));
		VBox.setMargin(getBio(), new Insets(0, 10, 10, 10));

		pane = new BorderPane();
		pane.setLeft(profilePic);
		pane.setCenter(textHolder);

		pane.getStylesheets().add(getClass().
				getResource("application.css").
				toExternalForm());

	}

	/***********************************************************************
	 * @return pane
	 **********************************************************************/
	public BorderPane getPane() {
		return pane;
	}

	/********************************************************
	 * Gets the twitter user bio.
	 * @return	returns the a text object of the user bio.
	 ********************************************************/
	public Text getBio() {
		return bio;
	}
	
	/***************************************
	 * Sets the bio of the user profile.
	 * @param bio a object of text
	 **************************************/
	public void setBio(final Text bio) {
		this.bio = bio;
	}

	/*********************************************
	 * Gets the twitter user name.
	 * @return returns a Text of the user name.
	 *********************************************/
	public Text getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	/*******************************************************************
	 * Gets the screen name of the twitter user.
	 * @return returns a Text object of the twitter users screen name.
	 ******************************************************************/
	public Text getScreenName() {
		return screenName;
	}
}
