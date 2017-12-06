package apis;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class FacebookProfile{
	
	/** Primary Container */
	private BorderPane pane;
	
	/** contains Text data*/
	private VBox column;
	

	public FacebookProfile(String profilePicURL, String name, String postStory, String postPictureURL, String PostLink){
		pane = new BorderPane();
		
		column = new VBox();
		column.setMaxWidth(580);
		column.setMinWidth(580);
		
		//row 1
		Image img = new Image(profilePicURL);
		Circle circ = new Circle(50,50,35);
		ImageView pPic = new ImageView(img);
		pPic.setClip(circ);
		pPic.setFitHeight(100);
		pPic.setFitWidth(100);
		
		Label profileNameStory = new Label(name + " - " + postStory);
		profileNameStory.setFont(Font.font("Calibri", 16));
		//profileNameStory.setWrapText(true);
		
		column.getChildren().add(profileNameStory);
		VBox.setMargin(profileNameStory, new Insets(15,0,4,-30));
		
		//row 2
		Label tempTimeDate = new Label("Temp Time - Temp Date");
		tempTimeDate.setTextFill(Color.GRAY);
		tempTimeDate.setTextAlignment(TextAlignment.JUSTIFY);
		
		
		
		column.getChildren().add(tempTimeDate);
		VBox.setMargin(tempTimeDate, new Insets(0,0,0,-22));
		
	
		//row 3
		Image postImage = new Image(postPictureURL);
		ImageView pPic2 = new ImageView(postImage);
		pPic2.setFitHeight(postImage.getHeight()/1.5);
		pPic2.setFitWidth(postImage.getWidth()/1.5);
		
		column.getChildren().add(pPic2);
		VBox.setMargin(pPic2, new Insets(12,0,0,-22));
		
		//row 4
		TextFlow flow = new TextFlow();
		
		pane.setLeft(pPic);
		pane.setRight(column);	
	}
	
	public BorderPane getPane(){
		return pane;
	}
	
}
