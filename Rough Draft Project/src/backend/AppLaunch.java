package backend;

import java.io.IOException;

import frontend.GUIBackend;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppLaunch extends Application {
	
	public static void main(String[] args) {
	Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Chadder!");
		primaryStage.getIcons().add(new Image("/frontend/assets/ChadderIcon.png"));
		primaryStage.setResizable(false);
		GUIBackend.loadNewScene(primaryStage, GUIBackend.SignInScene);
	}
	
}
