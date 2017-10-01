package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller {

	@FXML
	private TextField searchField;

	@FXML
	private Button faceBookButton;

	@FXML
	private Button twitterButton;

	@FXML
	private Button instagramButton;

	// Profile Buttons
	@FXML
	private Button profile1Button;

	@FXML
	private Button profile2Button;

	@FXML
	private Button profile3Button;

	@FXML
	private Button profile4Button;

	@FXML
	private Button profile5Button;

	@FXML
	private Button profile6Button;

	@FXML
	private Button profile7Button;

	@FXML
	private Button profile8Button;

	@FXML
	private Button profile9Button;

	@FXML
	private Button profile10Button;

	// Profile Button name
	@FXML
	private Text profile1Name;

	@FXML
	private Text profile2Name;

	@FXML
	private Text profile3Name;

	@FXML
	private Text profile4Name;

	@FXML
	private Text profile5Name;

	@FXML
	private Text profile6Name;

	@FXML
	private Text profile7Name;

	@FXML
	private Text profile8Name;

	@FXML
	private Text profile9Name;

	@FXML
	private Text profile10Name;

	@FXML
	TextArea resultsArea;

	@FXML
	Button settingsButton;

	@FXML
	ChoiceBox<String> profileChoice;

	private SettingsBox settings;

	public Controller() {
		settings = new SettingsBox();

	}

	@FXML
	private void showField() {
		setResultsArea();
	}

	@FXML
	private void buttonClicked(ActionEvent e) {
		if (e.getSource() == faceBookButton)
			System.out.println("Switching to facebook searching");
		else if (e.getSource() == twitterButton)
			System.out.println("Switching to twitter searching");
		else if (e.getSource() == instagramButton)
			System.out.println("Switching to instagram searching");
	}

	@FXML
	private void settingsButtonClicked(ActionEvent e) {
		// setChoices();
		settings = new SettingsBox(e);

		// SettingsBox.display("Settings", "test");

		/*
		 * FXMLLoader fxmlLoader = new
		 * FXMLLoader(getClass().getResource("settings.fxml")); Parent root =
		 * null; try { root = fxmlLoader.load(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); } Stage stage = new Stage();
		 * stage.initModality(Modality.APPLICATION_MODAL); stage.setOpacity(1);
		 * stage.setTitle("Settings"); stage.setScene(new Scene(root, 450,
		 * 450)); stage.showAndWait();
		 */

		// save("SavedProfiles");

	}

	@FXML
	private void settingsBoxChangeProfiles() {
		// TODO: Preform action when user profiles are switched, may not use
		// setChoices();
		settings = new SettingsBox(profileChoice.getValue());
	}

	@FXML
	public void setChoices() {
		
		System.out.println(profile1Name);
		
		profileChoice.getItems().removeAll(profileChoice.getItems());

		// profileChoice.getItems().addAll("Option A", "Option B", "Option C");
		// profileChoice.getSelectionModel().select("Option B");

		// profileChoice.getItems().addAll(profile1Name.getText(),
		// profile2Name.getText(),
		// profile3Name.getText(),profile4Name.getText(),
		// profile5Name.getText(), profile6Name.getText(),
		// profile7Name.getText(),profile8Name.getText(),
		// profile9Name.getText(), profile10Name.getText());

		 profileChoice.getItems().removeAll(profileChoice.getItems());
		 profileChoice.getItems().addAll("test", "test2");
		// profileChoice.getSelectionModel().select("test");

	}

	private void setResultsArea() {
		resultsArea.setText(searchField.getText());
	}

	/******************************************************************************
	 * Method that prints all "this" instance variables to a text file
	 *****************************************************************************/
	public static void save(String fileName) {
		fileName = "\\resources\\" + fileName;
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TODO: print out all text data in easily readable format

		out.close();

	}

	/******************************************************************************
	 * Method that uses a scanner to read instance variables from a text file
	 *****************************************************************************/
	public void load(String fileName) {
		try {
			// open the data file
			Scanner fileReader = new Scanner(new File("resources/" + fileName));

			// TODO: load data from text document

			/*
			 * // reads data from file, and splits it in half String[] input =
			 * fileReader.next().split(":");
			 * 
			 * // parses an int for the total number of games won int snippit =
			 * Integer.parseInt(input[0].trim());
			 * 
			 * // parses an int for the total amount of games snippit =
			 * Integer.parseInt(input[1].trim()); // gamesTotal++;
			 * 
			 * fileReader.close();
			 */
		}

		// could not find file
		catch (Exception error) {
		}
	}
}
