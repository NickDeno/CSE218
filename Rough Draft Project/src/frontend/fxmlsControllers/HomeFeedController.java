package frontend.fxmlsControllers;

import backend.Post;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class HomeFeedController {
	@FXML private Label userLabel;
	@FXML private TilePane tilePane;
	
	private LandingController landingController;
	
	//Initializer
	public HomeFeedController() {}
	
	public void initialize() {
		userLabel.setText("Welcome Back " + SignInController.currentUser.getUsername() + "!");
		Platform.runLater(() ->{
			GUIBackend.displayPosts(SignInController.allPosts, tilePane, landingController);
		});
	}
	
	public void displayNewPost(Post post) {
		GUIBackend.displayNewPost(post, tilePane, landingController);
	}
	
	public void setLandingController(LandingController landingController) {
		this.landingController = landingController;
	}
}
