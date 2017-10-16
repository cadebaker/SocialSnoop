package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import twitter4j.User;

public class TwitterProfile {

	private BorderPane pane;
	/** The first and last name of the twitter user. */
	Text name;
	//private String name, screenName, bio, tweetDate;

	/** The screen name of the twitter profile. */
	Text screenName;

	/** The users bio. */
	Text bio;

	/** The users profile picture. */
	ImageView profilePic;
	Image image;

	/** The URL of the users home twitter page. */
	private String url;

	// private ImageIcon profilePic;

	/**********************************************
	 * Takes a object of User, u, and pulls profile information from the user
	 * object.
	 * 
	 * @param u
	 *            A user.
	 *********************************************/
	public TwitterProfile(User u) {
		//name = new Text(u.getName()); // Gets the name of the user.
		name = new Text(u.getName());
		//screenName = new Text(u.getScreenName()); // Gets the screen name of the
													// user.
		screenName = new Text(u.getScreenName());
		//bio = new Text(u.getDescription()); // Gets the user description.
		bio = new Text(u.getDescription());
		url = u.getURL(); // Gets the URL of the user's twitter page.
		image = new Image(u.getProfileImageURL());

		profilePic = new ImageView(image);
		// https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx,
		// by James_D
		profilePic.setFitHeight(100);
		profilePic.setFitWidth(100);
		
		profilePic.setPreserveRatio(true);
		BorderPane.setMargin(profilePic, new Insets(12, 12, 8, 12));
		
		name.setId("name-text");

		VBox textHolder = new VBox();
		textHolder.getChildren().addAll(name,bio);
		VBox.setMargin(name, new Insets(10, 10, 0, 0));
		VBox.setMargin(bio, new Insets(0, 10, 10, 10));

		pane = new BorderPane();
		pane.setLeft(profilePic);
		pane.setCenter(textHolder);

		pane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
	}

	public BorderPane getPane() {
		return pane;
	}

	public void setPane(BorderPane pane) {
		this.pane = pane;
	}

	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}

	public Text getScreenName() {
		return screenName;
	}

	public void setScreenName(Text screenName) {
		this.screenName = screenName;
	}

	public Text getBio() {
		return bio;
	}

	public void setBio(Text bio) {
		this.bio = bio;
	}

	public ImageView getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(ImageView profilePic) {
		this.profilePic = profilePic;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
