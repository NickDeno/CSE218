package frontend.fxmlsControllers;

import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class HomeFeedController {
	@FXML private Label userLabel;
	@FXML private TilePane tilePane;
	
	private AnchorPane contentPane;
	
	//Initializer
	public HomeFeedController() {	
	}
	
	public void initialize() {
		userLabel.setText("Welcome Back " + SignInController.currentUser.getUsername() + "!");
		Platform.runLater(() ->{
			GUIBackend.displayPosts(SignInController.allPosts, tilePane, contentPane);
		});
	}
	
	public void setContentPane(AnchorPane contentPane) {
		this.contentPane = contentPane;
	}
}
