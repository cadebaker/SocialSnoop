package apis;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TwitterProfile {

	/** Primary Container */
	private BorderPane pane;

	/** contains Text data */
	private VBox column;

	public TwitterProfile(String name, String screenName, String data, String time, String profilePic,
			String tweetPic) {
		pane = new BorderPane();

		column = new VBox();
		column.setMaxWidth(580);
		column.setMinWidth(580);

		Image img = new Image(profilePic);
		// Image img = new
		// Image("http://pbs.twimg.com/profile_images/553634315532513280/r38xQFqc.jpeg");
		Circle circ = new Circle(50, 50, 35);
		ImageView pPic = new ImageView(img);
		pPic.setClip(circ);
		pPic.setFitHeight(100);
		pPic.setFitWidth(100);

		HBox names = new HBox();

		Label nameLb = new Label(name);
		nameLb.setFont(Font.font("Calibri", FontWeight.BOLD, 70));
		//nameLb.setFont(Font.font("Calibri", 18));
		//nameLb.setStyle("-fx-font-weight: bold");

		Label screenNameLb = new Label("@" + screenName + " · " + time);
		screenNameLb.setFont(Font.font("Calibri", 16));
		screenNameLb.setTextFill(Color.GRAY);

		names.getChildren().addAll(nameLb, screenNameLb);
		HBox.setMargin(screenNameLb, new Insets(4, 0, 0, 5));

		Label tweet = new Label(data);
		tweet.setFont(Font.font("Calibri", 17));
		tweet.setWrapText(true);
		tweet.setMaxWidth(600);

		column.getChildren().addAll(names, tweet);
		VBox.setMargin(names, new Insets(12, 0, 0, -35));
		VBox.setMargin(tweet, new Insets(0, 0, 0, -33));

		try {
			Image postImg = new Image(tweetPic);
			// Image postImg = new
			// Image("http://pbs.twimg.com/profile_images/553634315532513280/r38xQFqc.jpeg");
			ImageView pPic2 = new ImageView(postImg);
			pPic2.setFitHeight(postImg.getHeight() / 1.5);
			pPic2.setFitWidth(postImg.getWidth() / 1.5);
			VBox.setMargin(pPic2, new Insets(5, 0, 0, -50));
		} catch (Exception e) {

			// if (!tweetPic.contains("NOPE")) {
			// System.out.println(name + " @" + screenName);
			// System.out.println(data);

			// System.out.println(tweetPic);

			// System.out.println(e);
			// }
		}

		pane.setLeft(pPic);
		pane.setRight(column);
	}

	public BorderPane getPane() {
		return pane;
	}
}
