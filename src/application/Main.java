package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/*******************************************************************************
 * Main JavaFX class that handles the GUI loading of 
 * the FXML document and CSS class.
 *
 * @author Logan Karney
 ******************************************************************************/
public class Main extends Application {

	@Override
	public void start(final Stage primaryStage) {
		try {
			// loading of Gui.FXML
			Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
			primaryStage.setTitle("Social Snooper");

			Scene scene = new Scene(root);
			
			// loading of application.css
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// handles user requests to close the window
			primaryStage.setOnCloseRequest(e -> {

				// stops CloseRequest action from firing after this block of code
				e.consume();
				
				// call to closeProgram method
				closeProgram();

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**********************************************************************
	 * Method that ensures the user actually wants to close the program.
	 *********************************************************************/
	private static void closeProgram() {
		
		boolean close = CloseBox.display("Social Snooper", "Are you sure you want to exit?");

		if (close) {
			Platform.exit();
		}

	}

	/**********************************************************************
	 * JavaFX method that launches the program.
	 *
	 * @param args Traditional parameter
	 *********************************************************************/
	public static void main(final String[] args) {
		launch(args);
	}
}
