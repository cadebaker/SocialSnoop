package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller {

	@FXML
	private TextField searchField;

	@FXML
	private Button instagramButton, faceBookButton, twitterButton, settingsButton;

	// Profile Radio Buttons
	
	@FXML
	private ToggleGroup profile;
	
	@FXML
	private RadioButton profile1Button, profile2Button, profile3Button, profile4Button, profile5Button, profile6Button,
			profile7Button, profile8Button, profile9Button, profile10Button;

	// Profile Button name
	@FXML
	private Text profile1Name, profile2Name, profile3Name, profile4Name, profile5Name, profile6Name, profile7Name,
			profile8Name, profile9Name, profile10Name;

	@FXML
	private Text profile1T, profile2T, profile3T, profile4T, profile5T, profile6T, profile7T, profile8T, profile9T,
			profile10T;

	@FXML
	private Text profile1I, profile2I, profile3I, profile4I, profile5I, profile6I, profile7I, profile8I, profile9I,
			profile10I;

	@FXML
	private Text profile1F, profile2F, profile3F, profile4F, profile5F, profile6F, profile7F, profile8F,
			profile9F, profile10F;

	// contains profile urls
	@FXML
	private TitledPane profile1Data, profile2Data, profile3Data, profile4Data, profile5Data, profile6Data, profile7Data,
			profile8Data, profile9Data, profile10Data;

	// TODO: MAKE VARIABLES FOR EACH TEXT INSIDE THE TITLED PANES

	@FXML
	private Accordion accordian;

	@FXML
	private VBox displayBox;

	@FXML
	private ScrollPane scroller;

	private DataTable table;

	public Controller() {
		table = new DataTable("savedprofiles.txt");

	}

	public void initialize() {
		scroller.setFitToWidth(true);

		for (int i = 0; i < getProfileNames().length; i++) {
			getProfileNames()[i].setText(table.getProfileNames().get(i));
		}
		
		//System.out.println(profile1Button.getToggleGroup());
		//System.out.println(profile.getToggles());
	}

	@FXML
	private void showField() {
		//TODO unselect radiobutton
		//profile.selectToggle(null);
		
		setResultsArea();

	}

	@FXML
	private void buttonClicked(ActionEvent e) {
		if (e.getSource() == faceBookButton) {
			System.out.println("Switching to facebook searching");
		} else if (e.getSource() == twitterButton) {
			displayBox.getChildren().clear();
		} else if (e.getSource() == instagramButton) {
			System.out.println("Switching to instagram searching");
		}
	}

	@FXML
	private void settingsButtonClicked(ActionEvent e) {
		// System.out.println(profile1Name.getText());
		// DataTable table = new DataTable("savedprofiles.txt");
		table.display();
		// load data

		// save new data
		// update Profile changes

	}
	
	@FXML
	private void radioButtonClicked(){
		System.out.println(profile.getSelectedToggle());
	}

	@FXML
	private void updateAccordians() {
		try {
			String id = accordian.getExpandedPane().getId();
			int i = Integer.parseInt(id.substring(7, id.indexOf("Data"))) - 1;
			table.load("savedprofiles.txt");
			getProfileNames()[i].setText(table.getProfileNames().get(i));
			getProfileT()[i].setText(table.getTwitterURLs().get(i));
			getProfileI()[i].setText(table.getInstagramURLs().get(i));
			getProfileF()[i].setText(table.getFaceBookURLs().get(i));
			
			//System.out.println(table.getProfileNames().get(i) + " " + table.getTwitterURLs().get(i) + " "
			//	+ table.getInstagramURLs().get(i) + " " + table.getFaceBookURLs().get(i));
			
			
			
			
			
			
		} catch (NullPointerException e) {
		}

	}

	private void setResultsArea() {
		//Profile ay = new Profile(searchField.getText(), "description of this person", "/resources/fb-art.png");

		//displayBox.getChildren().addAll(ay.getPane());
	}

	public Text[] getProfileNames() {
		Text[] rtn = { profile1Name, profile2Name, profile3Name, profile4Name, profile5Name, profile6Name, profile7Name,
				profile8Name, profile9Name, profile10Name };

		return rtn;
	}

	public Text[] getProfileT() {
		Text[] rtn = { profile1T, profile2T, profile3T, profile4T, profile5T, profile6T, profile7T, profile8T,
				profile9T, profile10T };

		return rtn;
	}

	public Text[] getProfileI() {
		Text[] rtn = { profile1I, profile2I, profile3I, profile4I, profile5I, profile6I, profile7I, profile8I,
				profile9I, profile10I };

		return rtn;
	}

	public Text[] getProfileF() {
		Text[] rtn = { profile1F, profile2F, profile3F, profile4F, profile5F, profile6F, profile7F, profile8F,
				profile9F, profile10F };

		return rtn;
	}
}
