package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;import javafx.scene.control.TextField;

public class Controller {
	
	@FXML
	private TextField searchField;
	
	@FXML
	private Button faceBookButton;
	
	@FXML
	private Button twitterButton;
	
	@FXML
	private Button instagramButton;
	
	@FXML
	private void showField(){
		System.out.println(searchField.getText());
	}
	
	@FXML
	private void buttonClicked(ActionEvent e){
		System.out.println(e.getSource());
	}
}
