package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import resources.*;

public class Main extends Application {

	private static boolean close = false;

	@Override
	public void start(final Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
			primaryStage.setTitle("Social Snooper");

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setOnCloseRequest(e -> {

				// CloseRequest action from firing after this block of code
				e.consume();

				closeProgram();

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void closeProgram() {

		close = CloseBox.display("Social Snooper", "Are you sure you want to exit?");

		if (close)
			Platform.exit();

	}

	public static void main(final String[] args) {
		launch(args);
	}
}
