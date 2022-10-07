package p3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppDemo extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AppView appView = new AppView();
		Scene scene = new Scene(appView.getPane(), 800, 700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("P3 HW02");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
