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
	
	public LandingController() {	
	}
	
	public void initialize() {
		userLabel.setText(SignInController.currentUser.getUsername());			
		HomeFeedController homeFeed = GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		homeFeed.setContentPane(contentPane);
	}
	
	@FXML public void homeButtonOnAction(ActionEvent event) {
		HomeFeedController homeFeed = GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		homeFeed.setContentPane(contentPane);
	}
	
	@FXML public void profileButtonOnAction(ActionEvent event) {
		GUIBackend.loadPane(contentPane, GUIBackend.ProfileScene);
	}
	
	@FXML public void settingsButtonOnAction(ActionEvent event) {
		GUIBackend.loadPane(contentPane, GUIBackend.SettingsScene);
	}
	
	@FXML public void createPostButtonOnAction(ActionEvent event) {
		CreatePostController createPostController = GUIBackend.loadPane(contentPane, GUIBackend.CreatePostScene);	
		createPostController.setContentPane(contentPane);
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);	
	}
	
	public AnchorPane getContentPane() {
		return contentPane;
	}
	
}
