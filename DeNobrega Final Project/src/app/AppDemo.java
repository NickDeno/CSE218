package app;

import java.io.IOException;

import util.GUIUtilities;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppDemo extends Application {
	//Descriptions of all data structures used in this application and their functionality are located in README.txt File
	//FlowChart visual of how this application runs is located in FlowChart.png File
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Chadder!");
		primaryStage.getIcons().add(new Image("/assets/ChadderIcon.png"));
		primaryStage.setResizable(false);
		GUIUtilities.loadNewScene(primaryStage, GUIUtilities.SignInScene);
	}	
}
