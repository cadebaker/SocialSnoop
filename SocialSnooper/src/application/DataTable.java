package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DataTable {

	private String fileName;

	private ArrayList<String> profileNames, twitterURLs, twitterNames, instagramURLs, instagramKeys, faceBookURLs,
			faceBookKeys;

	Stage window;

	TableView<TableCell> table;

	TableColumn<TableCell, String> nameColumn, twitterNameColumn, instagramKeyColumn, faceBookKeyColumn;

	Scene scene;

	Button clear, reset, done;
	
	private Controller c;

	public DataTable(String fileName) {
		this.fileName = fileName;

		setUp();

	}

	public void display(Controller c) {
		this.c = c;
		setUp();

		window = new Stage();
		window.initStyle(StageStyle.UNDECORATED);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Profiles");

		window.setOnCloseRequest(e -> save(fileName));

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10, 10, 10, 10));
		// spacing between individual components
		hBox.setSpacing(10);
		hBox.getChildren().addAll(clear, reset, done);

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(table, hBox);

		scene = new Scene(layout);
		window.setScene(scene);
		window.show();

	}

	@SuppressWarnings("unchecked")
	public void setUp() {
		// Profile name column
		nameColumn = new TableColumn<>("Profile");
		nameColumn.setMinWidth(200);
		// lower case string has to match the variable name in the sub class
		nameColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("name"));

		// TwitterKey column
		twitterNameColumn = new TableColumn<>("Twitter Name");
		twitterNameColumn.setMinWidth(200);
		twitterNameColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("twitterName"));

		// InstagramKey column
		instagramKeyColumn = new TableColumn<>("Instagram Key");
		instagramKeyColumn.setMinWidth(200);
		instagramKeyColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("instagramKey"));

		// FacebookKey column
		faceBookKeyColumn = new TableColumn<>("Facebook Key");
		faceBookKeyColumn.setMinWidth(200);
		faceBookKeyColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("faceBookKey"));

		// https://stackoverflow.com/questions/23300774/why-tableview-tablecolumn-is-not-editable
		// by Aerospace
		nameColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		twitterNameColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		instagramKeyColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		faceBookKeyColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());

		// https://stackoverflow.com/questions/41465181/tableview-update-database-on-edit,
		// answer by James_D
		nameColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setName(event.getNewValue());
		});

		twitterNameColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setTwitterName(event.getNewValue());
		});

		instagramKeyColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setInstagramKey(event.getNewValue());
		});

		faceBookKeyColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setFaceBookKey(event.getNewValue());
		});

		// Clear button
		clear = new Button();
		clear.setText("Clear");

		// Reset button
		reset = new Button();
		reset.setText("Reset");

		// Done button
		done = new Button();
		done.setText("Done");

		// Button actions
		clear.setOnAction(e -> {
			erase(fileName);
			table.setItems(getProfiles());
			table.refresh();
		});

		reset.setOnAction(e -> {
			// reload data
			load(fileName);
			table.setItems(getProfiles());
			table.refresh();
		});

		done.setOnAction(e -> {
			// save data
			save(fileName);
			load(fileName);
			Text[] names = c.getProfileNames();
			
			for(int i = 0; i < names.length; i++){
				names[i].setText(profileNames.get(i));
			}
			
			window.close();
		});

		table = new TableView<>();
		table.setEditable(true);
		table.getSelectionModel().cellSelectionEnabledProperty().set(true);

		table.setItems(getProfiles());
		table.getColumns().addAll(nameColumn, twitterNameColumn, instagramKeyColumn, faceBookKeyColumn);

	}

	public ObservableList<TableCell> getProfiles() {

		load(fileName);

		ObservableList<TableCell> profiles = FXCollections.observableArrayList();

		for (int i = 0; i < getProfileNames().size(); i++) {
			profiles.add(new TableCell(getProfileNames().get(i), twitterNames.get(i), instagramKeys.get(i),
					faceBookKeys.get(i)));
		}

		return profiles;
	}

	/******************************************************************************
	 * Method that prints all profile variables to a text file
	 *****************************************************************************/
	public void save(String fileName) {
		fileName = "SocialSnooper\\src\\resources\\" + fileName;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// TODO: print out all text data in easily readable format
			for (TableCell x : table.getItems()) {

				out.println(
						x.getName() + ":" + x.getTwitterName() + ":" + x.getInstagramKey() + ":" + x.getFaceBookKey());

			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/******************************************************************************
	 * Method that uses a scanner to read profile variables from a text file
	 *****************************************************************************/
	public void load(String fileName) {
		setProfileNames(new ArrayList<String>());
		twitterNames = new ArrayList<String>();
		instagramKeys = new ArrayList<String>();
		faceBookKeys = new ArrayList<String>();

		try {
			// open the data file
			Scanner fileReader = new Scanner(new File("SocialSnooper\\src\\resources\\" + fileName));

			String[] profileArray = new String[10];

			int counter = 0;
			while (fileReader.hasNextLine()) {
				profileArray[counter] = fileReader.nextLine();

				counter++;
			}
			for (String p : profileArray) {
				String[] parts = p.split(":");

				getProfileNames().add(parts[0]);
				twitterNames.add(parts[1]);
				instagramKeys.add(parts[2]);
				faceBookKeys.add(parts[3]);
			}

			fileReader.close();
		}

		// could not find file
		catch (Exception error) {
			save(fileName);
			for (int i = 0; i < 10; i++) {
				getProfileNames().add("Profile");
				twitterNames.add("twitterName");
				instagramKeys.add("instagramKey");
				faceBookKeys.add("facebookKey");
			}
		}
	}

	public void erase(String fileName) {
		fileName = "SocialSnooper\\src\\resources\\" + fileName;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// TODO: print out all text data in easily readable format
			for (TableCell x : table.getItems()) {

				out.println("Profile" + ":" + "TwitterName" + ":" + "InstagramKey" + ":" + "FacebookKey");

			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> getProfileNames() {
		return profileNames;
	}

	public void setProfileNames(ArrayList<String> profileNames) {
		this.profileNames = profileNames;
	}

	public ArrayList<String> getTwitterURLs() {
		return twitterURLs;
	}

	public void setTwitterURLs(ArrayList<String> twitterURLs) {
		this.twitterURLs = twitterURLs;
	}

	public ArrayList<String> getInstagramURLs() {
		return instagramURLs;
	}

	public void setInstagramURLs(ArrayList<String> instagramURLs) {
		this.instagramURLs = instagramURLs;
	}

	public ArrayList<String> getFaceBookURLs() {
		return faceBookURLs;
	}

	public void setFaceBookURLs(ArrayList<String> faceBookURLs) {
		this.faceBookURLs = faceBookURLs;
	}

	public ArrayList<String> getFaceBookKeys() {
		return faceBookKeys;
	}

	public void setFaceBookKeys(ArrayList<String> faceBookKeys) {
		this.faceBookKeys = faceBookKeys;
	}

	/******************************************************************************
	 * @return the twitterKeys
	 *****************************************************************************/
	public ArrayList<String> getTwitterKeys() {
		return twitterNames;
	}

	/******************************************************************************
	 * @param twitterKeys
	 *            the twitterKeys to set
	 *****************************************************************************/
	public void setTwitterKeys(ArrayList<String> twitterKeys) {
		this.twitterNames = twitterKeys;
	}

	/******************************************************************************
	 * @return the instagramKeys
	 *****************************************************************************/
	public ArrayList<String> getInstagramKeys() {
		return instagramKeys;
	}

	/******************************************************************************
	 * @param instagramKeys
	 *            the instagramKeys to set
	 *****************************************************************************/
	public void setInstagramKeys(ArrayList<String> instagramKeys) {
		this.instagramKeys = instagramKeys;
	}
}
