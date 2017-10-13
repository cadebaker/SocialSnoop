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

public class Profile {
	
	private BorderPane pane;
	
	Text name;
	Text description;
	ImageView picture;
	Image image;
	
	public Profile(String pName, String pDescription, String img){
		
		
		this.name = new Text(pName);
		this.description = new Text(pDescription);
		this.image = new Image(img);
		picture = new ImageView(image);
		
		//https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx, by James_D
		picture.setFitHeight(100);
		picture.setFitWidth(100);
		picture.setPreserveRatio(true);
		BorderPane.setMargin(picture, new Insets(12,12,8,12));
		
		name.setId("name-text");
		
		VBox textHolder = new VBox();
		textHolder.getChildren().addAll(name, description);
		VBox.setMargin(name, new Insets(10,10,0,0));
		VBox.setMargin(description, new Insets(0,10,10,10));
		
		pane = new BorderPane();
		pane.setLeft(picture);
		pane.setCenter(textHolder);
		
		pane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	}
	
public Profile(String pName, String pDescription, Image img){
		
		
		this.name = new Text(pName);
		this.description = new Text(pDescription);
		this.image = img;
		picture = new ImageView(image);
		
		//https://stackoverflow.com/questions/27894945/how-do-i-resize-an-imageview-image-in-javafx, by James_D
		picture.setFitHeight(100);
		picture.setFitWidth(100);
		picture.setPreserveRatio(true);
		BorderPane.setMargin(picture, new Insets(12,12,8,12));
		
		name.setId("name-text");
		
		VBox textHolder = new VBox();
		textHolder.getChildren().addAll(name, description);
		VBox.setMargin(name, new Insets(10,10,0,0));
		VBox.setMargin(description, new Insets(0,10,10,10));
		
		pane = new BorderPane();
		pane.setLeft(picture);
		pane.setCenter(textHolder);
		
		pane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
	}
	
	public BorderPane getPane(){
		return pane;
	}

}
