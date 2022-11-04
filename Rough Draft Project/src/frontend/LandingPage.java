package frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LandingPage {
	
	@FXML private Label userLabel;
	@FXML private Button profileButton;
	@FXML private BorderPane mainPane;
	@FXML private AnchorPane contentPane;
	@FXML private ImageView profilePic;
	
	public void initialize() {
		userLabel.setText(SignInPage.currentUser.getUsername());
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
		GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		GUIBackend.loadNewWindow(GUIBackend.CreatePostScene);	
//		mainPane.setEffect(new GaussianBlur(10));
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);	
	}
}
