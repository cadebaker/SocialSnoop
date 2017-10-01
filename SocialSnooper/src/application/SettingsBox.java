package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsBox {

	public SettingsBox() {

	}

	public SettingsBox(ActionEvent e) {

		Stage window = new Stage();

		// blocks user input with other events until this one is taken care of
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Profile Settings");
		window.setMinWidth(250);

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("SettingsBox.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public SettingsBox(String profileName) {
		// search file for matching name
	}

	public static void display(String title, String message) {
		Stage window = new Stage();

		// blocks user input with other events until this one is taken care of
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);

		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("close the window");
		closeButton.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);

		// shows the stage and waits for it to be closed before returning to the
		// caller
		window.showAndWait();
	}
}