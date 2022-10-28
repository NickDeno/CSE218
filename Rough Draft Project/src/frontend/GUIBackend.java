package frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUIBackend {
	//Loads new window with specified fxml file and sets title of window with specified name
	public static void loadNewWindow(String fxmlFileName){
		try{
			Parent root = FXMLLoader.load(GUIBackend.class.getResource("/frontend/fxmls/" + fxmlFileName));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}catch (IOException e) {
			System.out.println("Unable to load new window with " + fxmlFileName + ".fxml");
			e.printStackTrace();
		}
	}
	
	//Loads a specified AnchorPane with a specified fxml file
	public static void loadContentPane(AnchorPane ap, String fxmlFileName) {
		try {
			AnchorPane pane = FXMLLoader.load(GUIBackend.class.getResource("/frontend/fxmls/" + fxmlFileName));
			ap.getChildren().clear();
			ap.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println("Unable to load content pane with " + fxmlFileName);
			e.printStackTrace();
		}
	}
	
	//Loads new scene into specified stage with specified fxml file
	public static void loadNewScene(Stage stage, String fxmlFileName) {
		try {
			Parent root = FXMLLoader.load(GUIBackend.class.getResource("/frontend/fxmls/" + fxmlFileName));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			System.out.println("Unable to load scene with " + fxmlFileName);
			e.printStackTrace();
		}
	}
}
