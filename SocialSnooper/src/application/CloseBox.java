package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CloseBox {

	static boolean answer;

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		
		//disables user input on other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(150);

		Label label = new Label();
		label.setText(message);

		Button yesButton = new Button("yes");
		Button noButton = new Button("no");

		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		
		//disables user input on other windows
		window.showAndWait();
		
		return answer;
	}
}
