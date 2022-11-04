package backend;

import java.io.IOException;

import frontend.GUIBackend;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLaunch extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Sign In!");
		primaryStage.setResizable(false);
		GUIBackend.loadNewScene(primaryStage, GUIBackend.SignInScene);
	}
	
}
