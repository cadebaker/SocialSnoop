package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*******************************************************************************
 * Class that handles the local saving and loading of profile data
 *
 * @author Logan Karney
 ******************************************************************************/
public class DataTable {

	/** name of the text file that is loaded **/
	private String fileName;

	/** Used to hold the data of multiple profiles **/
	private ArrayList<String> profileNames, twitterURLs, twitterNames, instagramURLs, instagramKeys, faceBookURLs,
			faceBookKeys;

	/** primary stage **/
	Stage window;

	/** Display Table **/
	TableView<TableCell> table;

	/** columns displayed in the table object **/
	TableColumn<TableCell, String> nameColumn, twitterNameColumn, instagramKeyColumn, faceBookKeyColumn;

	/** scene **/
	Scene scene;

	/** Buttons used for user interaction within the menu **/
	Button clear, reset, done;

	/** instance of the Controller class that called this class **/
	private Controller c;

	/******************************************************************************
	 * Sets up Data and saves filename variable
	 * 
	 * @param fileName
	 *****************************************************************************/
	public DataTable(String fileName) {
		this.fileName = fileName;

		setUp();

	}

	/******************************************************************************
	 * Displays relevant saved data
	 *
	 * @param c
	 *            Controller class
	 *****************************************************************************/
	public void display(Controller c) {
		this.c = c;
		setUp();

		window = new Stage();
		window.initStyle(StageStyle.UNDECORATED);

		// freezes activity in main window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Profiles");

		// ensures the user does not accidently lose unsaved data
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

	/******************************************************************************
	 * Prepares data to be displayed
	 *****************************************************************************/
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
		// creates the Cells for display
		nameColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		twitterNameColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		instagramKeyColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());
		faceBookKeyColumn.setCellFactory(TextFieldTableCell.<TableCell>forTableColumn());

		// https://stackoverflow.com/questions/41465181/tableview-update-database-on-edit,
		// answer by James_D
		// allows TableCells to be edited within the display table
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
		// sets all data to a default state
		clear.setOnAction(e -> {
			erase(fileName);
			table.setItems(getProfiles());
			table.refresh();
		});

		// clears all unsaved data from the current session
		reset.setOnAction(e -> {
			// reload data
			load(fileName);
			table.setItems(getProfiles());
			table.refresh();
		});

		// saves data and updates the TitledPanes accordingly
		done.setOnAction(e -> {
			// save data
			save(fileName);
			load(fileName);
			Text[] names = c.getProfileNames();

			for (int i = 0; i < names.length; i++) {
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

	/******************************************************************************
	 * @return ArrayList of TableCells
	 *****************************************************************************/
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
	 * 
	 * @param fileName
	 * text file to which the data is saved
	 *****************************************************************************/
	public void save(String fileName) {
		fileName = "src\\resources\\" + fileName;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

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
	 * 
	 * @param fileName
	 * text file that is loaded
	 *****************************************************************************/
	public void load(String fileName) {
		setProfileNames(new ArrayList<String>());
		twitterNames = new ArrayList<String>();
		instagramKeys = new ArrayList<String>();
		faceBookKeys = new ArrayList<String>();

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

				// splits and distributes read data
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

				// sets default values

				getProfileNames().add("Profile");
				twitterNames.add("twitterName");
				instagramKeys.add("instagramKey");
				faceBookKeys.add("facebookKey");
			}
		}
	}

	/******************************************************************************
	 * Erases all unsaved changes from the TableView
	 *
	 * @param fileName
	 *****************************************************************************/
	public void erase(String fileName) {
		fileName = "src\\resources\\" + fileName;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

			// sets default values
			for (@SuppressWarnings("unused") TableCell x : table.getItems()) {
				out.println("Profile" + ":" + "TwitterName" + ":" + "InstagramKey" + ":" + "FacebookKey");

			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/******************************************************************************
	 * @return ArrayList of profileNames
	 *****************************************************************************/
	public ArrayList<String> getProfileNames() {
		return profileNames;
	}

	/******************************************************************************
	 * @param profileNames
	 *            sets profileNames
	 *****************************************************************************/
	public void setProfileNames(ArrayList<String> profileNames) {
		this.profileNames = profileNames;
	}

	/******************************************************************************
	 * @return ArrayList of twitterURLs
	 *****************************************************************************/
	public ArrayList<String> getTwitterURLs() {
		return twitterURLs;
	}

	/******************************************************************************
	 * @param twitterURLs
	 *            sets twitterURls
	 *****************************************************************************/
	public void setTwitterURLs(ArrayList<String> twitterURLs) {
		this.twitterURLs = twitterURLs;
	}

	/******************************************************************************
	 * @return ArrayList of instagramURLS
	 *****************************************************************************/
	public ArrayList<String> getInstagramURLs() {
		return instagramURLs;
	}

	/******************************************************************************
	 * @param instagramURLs
	 *            sets instagramURls
	 *****************************************************************************/
	public void setInstagramURLs(ArrayList<String> instagramURLs) {
		this.instagramURLs = instagramURLs;
	}

	/******************************************************************************
	 * @return ArrayList of faceBookURLs
	 *****************************************************************************/
	public ArrayList<String> getFaceBookURLs() {
		return faceBookURLs;
	}

	/******************************************************************************
	 * @param faceBookURLs
	 *            sets faceBookURLs
	 *****************************************************************************/
	public void setFaceBookURLs(ArrayList<String> faceBookURLs) {
		this.faceBookURLs = faceBookURLs;
	}

	/******************************************************************************
	 * @return ArrayList of faceBookKeys
	 *****************************************************************************/
	public ArrayList<String> getFaceBookKeys() {
		return faceBookKeys;
	}

	/******************************************************************************
	 * @param faceBookKeys
	 *            sets faceBookKeys
	 *****************************************************************************/
	public void setFaceBookKeys(ArrayList<String> faceBookKeys) {
		this.faceBookKeys = faceBookKeys;
	}

	/******************************************************************************
	 * @return ArrayList of twitterNames
	 *****************************************************************************/
	public ArrayList<String> getTwitterNames() {
		return twitterNames;
	}

	/******************************************************************************
	 * @param twitterKeys
	 *            sets twitterNames
	 *****************************************************************************/
	public void setTwitterNames(ArrayList<String> twitterKeys) {
		this.twitterNames = twitterKeys;
	}

	/******************************************************************************
	 * @return ArrayList of instagramKeys
	 *****************************************************************************/
	public ArrayList<String> getInstagramKeys() {
		return instagramKeys;
	}

	/******************************************************************************
	 * @param instagramKeys
	 *            sets instagramKeys
	 *****************************************************************************/
	public void setInstagramKeys(ArrayList<String> instagramKeys) {
		this.instagramKeys = instagramKeys;
	}
}
