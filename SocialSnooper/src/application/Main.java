package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import resources.*;


public class Main extends Application {
	@Override
	public void start(final Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/resources/gui.fxml"));
	        primaryStage.setTitle("Hello World");
		
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(final String[] args) {
		launch(args);
	}
}
