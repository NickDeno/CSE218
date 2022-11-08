package frontend.fxmlsControllers;

import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LandingController {
	
	@FXML private Label userLabel;
	@FXML private Button profileButton;
	@FXML private BorderPane mainPane;
	@FXML private AnchorPane contentPane;
	@FXML private ImageView profilePic;
	
	public void initialize() {
		userLabel.setText(SignInController.currentUser.getUsername());
		GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		System.out.println("Initialized Landing Page!");
	}
	
	@FXML public void homeButtonOnAction(ActionEvent event) {
		GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
	}
	
	@FXML public void profileButtonOnAction(ActionEvent event) {
		GUIBackend.loadPane(contentPane, GUIBackend.ProfileScene);
	}
	
	@FXML public void settingsButtonOnAction(ActionEvent event) {
		GUIBackend.loadPane(contentPane, GUIBackend.SettingsScene);
	}
	
	@FXML public void createPostButtonOnAction(ActionEvent event) {
		HomeFeedController homeFeed = GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		CreatePostController newPost = GUIBackend.loadNewWindow(GUIBackend.CreatePostScene);
		newPost.setHomeFeed(homeFeed); // This is done so when new post is created in the seperate CreatePost window, the home feed will automatically display new post
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);	
	}
	
	
}
