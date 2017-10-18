package apis;


import java.util.Date;

import com.restfb.types.Post;

import application.Controller;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/*******************************************************************************
 * Class that formats data pulled from the Facebook API.
 *
 * @author Logan
 ******************************************************************************/
public class FacebookProfile {

	/** the primary storage container. **/
	private HBox pane;

	/** Holds the text data from the facebook API. **/
	private VBox textHolder;

	/** The Post's main data. **/
	private Text postDataText;

	/** The Post's secondary heading. **/
	private Text postStoryText;

	/** Time at which the post was made. **/
	private Date postTime;

	/** Raw data from the Facebook API. **/
	@SuppressWarnings("Unused field")
	private Post p;

	/** The object created from the Profile Image url. **/
	private Image img;

	/******************************************************************************
	 * @param p
	 *            Raw data from the facebook API.
	 * @param c
	 *            Instance of the Controller class
	 *****************************************************************************/
	@SuppressWarnings("deprecation")
	public FacebookProfile(final Post p, final Controller c) {

		this.img = c.getFbProfilePicture();

		pane = new HBox();

		//this.p = p;

		textHolder = new VBox();

		this.postTime = p.getCreatedTime();

		String date = postTime.getYear() + "";

		// proper formating of the date
		date = postTime.getMonth() + "/" + postTime.getDate() + "/" + date.substring(1);

		this.postDataText = new Text(p.getMessage());

		this.postStoryText = new Text(date + ":  " + p.getStory());
	}

	/******************************************************************************
	 * @return a VBox that contains relevant Text Post information.
	 *****************************************************************************/
	public VBox getVBox() {
		if (!postDataText.getText().trim().equals("")) {
			textHolder.getChildren().addAll(postDataText);
		VBox.setMargin(postDataText, new Insets(5, 10, 0, 10));
		}
		if (!postStoryText.getText().equals("")) {
			postDataText.setId("name-text");
			if (postDataText.getText().trim().equals("")) {

				textHolder.getChildren().addAll(postStoryText);

			VBox.setMargin(postStoryText, new Insets(0, 0, 0, 10));
			}
		} else {
			return textHolder;
		}

		return textHolder;
	}

	/******************************************************************************
	 * @param img
	 *            sets the Image url.
	 *****************************************************************************/
	public void setImg(final Image img) {
		this.img = img;
	}

	/******************************************************************************
	 * @return a HBox Container that has the user's profile image and text data.
	 *****************************************************************************/
	public HBox getHBox() {

		ImageView pPicture = new ImageView(img);

		pPicture.setFitHeight(100);
		pPicture.setFitWidth(100);

		pane.getChildren().addAll(pPicture);

		HBox.setMargin(pPicture, new Insets(0, 0, 10, 10));

		pane.getChildren().addAll(getVBox());

		return pane;

	}
}
