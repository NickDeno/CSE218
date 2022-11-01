package frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUIBackend {
	public static final String SignInScene = "/frontend/fxmls/SignInPage.fxml";
	public static final String SignUpScene = "/frontend/fxmls/SignUpPage.fxml";
	public static final String LandingScene = "/frontend/fxmls/LandingPage.fxml";
	public static final String HomeFeedScene = "/frontend/fxmls/HomeFeedPage.fxml";
	public static final String ProfileScene = "/frontend/fxmls/ProfilePage.fxml";
	public static final String SettingsScene = "/frontend/fxmls/SettingsPage.fxml";
	public static final String CreatePostScene = "/frontend/fxmls/CreatePostPage.fxml";
	
	//Loads new window with specified fxml file
	public static void loadNewWindow(String fxmlFileName){
		try{
			Parent root = FXMLLoader.load(GUIBackend.class.getResource(fxmlFileName));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
//			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		}catch (IOException e) {
			System.out.println("Unable to load new window with " + fxmlFileName + ".fxml");
			e.printStackTrace();
		}
	}
	
	//Loads a specified AnchorPane with a specified fxml file
	public static void loadPane(AnchorPane ap, String fxmlFileName) {
		try {
			AnchorPane pane = FXMLLoader.load(GUIBackend.class.getResource(fxmlFileName));
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
			Parent root = FXMLLoader.load(GUIBackend.class.getResource(fxmlFileName));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			System.out.println("Unable to load scene with " + fxmlFileName);
			e.printStackTrace();
		}
	}
}
