package frontend.fxmlsControllers;

import frontend.GUIBackend;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;

public class HomeFeedController {
	@FXML private Label userLabel;
	@FXML private ScrollPane scroll;
	@FXML private TilePane tilePane;

	public HomeFeedController() {
		
	}
	
	public void initialize() {
		userLabel.setText("Welcome Back " + SignInController.currentUser.getUsername() + " !");
		GUIBackend.displayPosts(SignInController.allPosts, tilePane);
	}
	
	
	public TilePane getTilePane() {
		return tilePane;
	}
	
}
