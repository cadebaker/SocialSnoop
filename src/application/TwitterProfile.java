package application;

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
public class TwitterProfile {

	/** Creates Border. */
	private BorderPane pane;
	/** The first and last name of the twitter user. */
	private Text name;
	// private String name, screenName, bio, tweetDate;

	/** The screen name of the twitter profile. */
	//Text screenName;

	/** The users bio. */
	private Text bio;

	/** The users profile picture. */
	private ImageView profilePic;
	
	/** Create image. */
	private Image image;

	/** The URL of the users home twitter page. */
	//private String url;

	// private ImageIcon profilePic;

	/**********************************************
	 * Takes a object of User, u, and pulls profile information from the user
	 * object.
	 * 
	 * @param u
	 *            A user.
	 *********************************************/
	public TwitterProfile(final User u) {
		// name = new Text(u.getName()); // Gets the name of the user.
		name = new Text(u.getName());
		// screenName = new Text(u.getScreenName()); // Gets the screen name of
		// the
		// user.
		//screenName = new Text(u.getScreenName());
		// bio = new Text(u.getDescription()); // Gets the user description.
		bio = new Text(u.getDescription());
		//url = u.getURL(); // Gets the URL of the user's twitter page.
		image = new Image(u.getBiggerProfileImageURL());

		profilePic = new ImageView(image);
		// https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx,
		// by James_D
		profilePic.setFitHeight(100);
		profilePic.setFitWidth(100);

		profilePic.setPreserveRatio(true);
		BorderPane.setMargin(profilePic, new Insets(12, 12, 8, 12));

		name.setId("name-text");

		VBox textHolder = new VBox();
		textHolder.getChildren().addAll(name, bio);
		VBox.setMargin(name, new Insets(10, 10, 0, 0));
		VBox.setMargin(bio, new Insets(0, 10, 10, 10));

		pane = new BorderPane();
		pane.setLeft(profilePic);
		pane.setCenter(textHolder);

		pane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	}

	/******************************************************************************
	 * @return pane
	 *****************************************************************************/
	public BorderPane getPane() {
		return pane;
	}
}
