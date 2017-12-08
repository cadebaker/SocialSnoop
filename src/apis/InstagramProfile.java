package apis;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*******************************************************************************
 * Class that takes pulled data from the Instagram API and creates a visual
 * representation
 *
 * @author Logan Karney
 ******************************************************************************/
public class InstagramProfile {

	/** Primary Container */
	private VBox column;

	/******************************************************************************
	 * @param profileName
	 * @param profilePic
	 * @param imagePic
	 * @param caption
	 * @param likes
	 *****************************************************************************/
	public InstagramProfile(String profileName, String profilePic, String imagePic, String caption, String likes) {
		column = new VBox();
		column.setMaxWidth(580);
		column.setMinWidth(580);

		
		//creation of GUI objects
		Font calibr = Font.font("Calibri", 15);

		HBox row1 = new HBox();

		Image img = new Image(profilePic);
		ImageView pPic = new ImageView(img);
		Circle circ = new Circle(30, 30, 17);
		pPic.setClip(circ);

		Label profileNLb = new Label(profileName);
		profileNLb.setFont(calibr);

		Image img2 = new Image(imagePic);
		ImageView pPic2 = new ImageView(img2);
		pPic2.setFitWidth(600);
		pPic2.setPreserveRatio(true);

		row1.getChildren().addAll(pPic, profileNLb);
		HBox.setMargin(profileNLb, new Insets(18, 0, 0, -90));

		Label likesLb = new Label(likes + " likes");
		likesLb.setFont(calibr);

		Label captionLb = new Label(caption);
		captionLb.setFont(calibr);
		captionLb.setWrapText(true);

		column.getChildren().addAll(row1, pPic2, likesLb, captionLb);
		
		//formating
		VBox.setMargin(pPic2, new Insets(-95, 0, 0, 55));
		VBox.setMargin(likesLb, new Insets(10, 0, 0, 60));
		VBox.setMargin(captionLb, new Insets(5, 0, 10, 60));
	}

	/******************************************************************************
	 * Returns completed GUI component
	 *
	 * @return column
	 *****************************************************************************/
	public VBox getColumn() {
		return column;
	}

}
