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

	private ArrayList<String> profileNames, faceBookURLs, twitterURLs, instagramURLs;

	Stage window;

	TableView<TableCell> table;

	TableColumn<TableCell, String> nameColumn, faceBookColumn, twitterColumn, instagramColumn;

	Scene scene;

	Button clear, reset, done;

	public DataTable(String fileName) {
		this.fileName = fileName;
		window = new Stage();
		window.initStyle(StageStyle.UNDECORATED);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Profiles");
		
		window.setOnCloseRequest(e -> save(fileName));

		setUp();

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

		// Facebook column
		faceBookColumn = new TableColumn<>("Facebook URL");
		faceBookColumn.setMinWidth(200);
		faceBookColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("faceBookURL"));

		// Twitter column
		twitterColumn = new TableColumn<>("Twitter URL");
		twitterColumn.setMinWidth(200);
		twitterColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("twitterURL"));

		// Twitter column
		instagramColumn = new TableColumn<>("Instagram URL");
		instagramColumn.setMinWidth(200);
		instagramColumn.setCellValueFactory(new PropertyValueFactory<TableCell, String>("instagramURL"));

		// https://stackoverflow.com/questions/23300774/why-tableview-tablecolumn-is-not-editable
		// by Aerospace
		nameColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		faceBookColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		twitterColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		instagramColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());

		// https://stackoverflow.com/questions/41465181/tableview-update-database-on-edit,
		// answer by James_D
		nameColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setName(event.getNewValue());
		});

		faceBookColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setFaceBookURL(event.getNewValue());
		});

		twitterColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setTwitterURL(event.getNewValue());
		});

		instagramColumn.setOnEditCommit(event -> {
			TableCell cell = event.getRowValue();
			cell.setInstagramURL(event.getNewValue());
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
			window.close();
		});

		table = new TableView<>();
		table.setEditable(true);
		table.getSelectionModel().cellSelectionEnabledProperty().set(true);

		table.setItems(getProfiles());
		table.getColumns().addAll(nameColumn, faceBookColumn, twitterColumn, instagramColumn);

	}

	public ObservableList<TableCell> getProfiles() {

		load(fileName);

		ObservableList<TableCell> profiles = FXCollections.observableArrayList();

		for (int i = 0; i < profileNames.size(); i++) {
			profiles.add(
					new TableCell(profileNames.get(i), faceBookURLs.get(i), twitterURLs.get(i), instagramURLs.get(i)));
		}

		return profiles;
	}

	/******************************************************************************
	 * Method that prints all profile variables to a text file
	 *****************************************************************************/
	public void save(String fileName) {
		fileName = "src\\resources\\" + fileName;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// TODO: print out all text data in easily readable format
			for (TableCell x : table.getItems()) {

				out.println(
						x.getName() + ":" + x.getFaceBookURL() + ":" + x.getTwitterURL() + ":" + x.getInstagramURL());

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
		profileNames = new ArrayList<String>();
		faceBookURLs = new ArrayList<String>();
		twitterURLs = new ArrayList<String>();
		instagramURLs = new ArrayList<String>();

		try {
			// open the data file
			Scanner fileReader = new Scanner(new File("src\\resources\\" + fileName));

			String[] profileArray = new String[10];

			int counter = 0;
			while (fileReader.hasNextLine()) {
				profileArray[counter] = fileReader.nextLine();

				counter++;
			}
			for (String p : profileArray) {
				String[] parts = p.split(":");

				profileNames.add(parts[0]);
				faceBookURLs.add(parts[1]);
				twitterURLs.add(parts[2]);
				instagramURLs.add(parts[3]);
			}

			fileReader.close();
		}

		// could not find file
		catch (Exception error) {
			save(fileName);
			for (int i = 0; i < 10; i++) {
				profileNames.add("Profile");
				faceBookURLs.add("facebookURL");
				twitterURLs.add("twitterURL");
				instagramURLs.add("instagramURL");
			}
		}
	}

	public void erase(String fileName) {
		fileName = "src\\resources\\" + fileName;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			// TODO: print out all text data in easily readable format
			for (TableCell x : table.getItems()) {

				out.println("Profile" + ":" + "facebookURL" + ":" + "twitterURL" + ":" + "instagramURL");

			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}