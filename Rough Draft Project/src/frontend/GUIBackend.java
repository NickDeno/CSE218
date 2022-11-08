package frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUIBackend {
	public static final String SignInScene = "/frontend/fxmls/SignInPage.fxml";
	public static final String SignUpScene = "/frontend/fxmls/SignUpPage.fxml";
	public static final String LandingScene = "/frontend/fxmls/LandingPage.fxml";
	public static final String HomeFeedScene = "/frontend/fxmls/HomeFeedPage.fxml";
	public static final String ProfileScene = "/frontend/fxmls/ProfilePage.fxml";
	public static final String SettingsScene = "/frontend/fxmls/SettingsPage.fxml";
	public static final String CreatePostScene = "/frontend/fxmls/CreatePostPage.fxml";
	
	//Loads new window with specified fxml file and returns "Controller" of specified fxml file
	public static <T> T loadNewWindow(String fxmlFileName) {
		try {
//			Parent root = FXMLLoader.load(GUIBackend.class.getResource(fxmlFileName));
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			return loader.getController();
		} catch (IOException e) {
			System.out.println("Unable to load new window with " + fxmlFileName);
			e.printStackTrace();
			return null;
		}
	}
	
	// Loads new scene into specified stage with specified fxml file
	public static void loadNewScene(Stage stage, String fxmlFileName) {
		try {
			Parent root = FXMLLoader.load(GUIBackend.class.getResource(fxmlFileName));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			System.out.println("Unable to load scene with " + fxmlFileName);
			e.printStackTrace();
		}
	}
	
	//Loads a specified AnchorPane with a specified fxml file and returns "Controller" of specified fmxl file
	public static <T> T loadPane(AnchorPane ap, String fxmlFileName) {
		try {
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			AnchorPane pane = loader.load();
			ap.getChildren().clear();
			ap.getChildren().setAll(pane);
			return loader.getController();	
		} catch (IOException e) {
			System.out.println("Unable to load content pane with " + fxmlFileName);
			e.printStackTrace();
		}
		return null;
	}
	
}
