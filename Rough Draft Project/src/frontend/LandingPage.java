package frontend;

import backend.User;
import backend.UserCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LandingPage {
	
	@FXML private Label userLabel;
	@FXML private Label usernameLabel;
	@FXML private Button profileButton;
	@FXML private BorderPane mainPane;
	@FXML private AnchorPane contentPane;
	
	private static User currentUser;
	private static UserCenter users;
	
	public void initialize() {
		currentUser = SignInSignUpPage.currentUser;
		users = SignInSignUpPage.users;
		usernameLabel.setText(currentUser.getUsername());
		userLabel.setText("Welcome back " + currentUser.getUsername() + " !");
		System.out.println("Initialized Landing Page!");
	}
	
	@FXML public void homeButtonOnAction(ActionEvent event) {
		GUIBackend.loadContentPane(contentPane, "HomeFeedPage.fxml");
	}
	
	@FXML public void profileButtonOnAction(ActionEvent event) {
		GUIBackend.loadContentPane(contentPane, "ProfilePage.fxml");
	}
	
	@FXML public void settingsButtonOnAction(ActionEvent event) {
		GUIBackend.loadContentPane(contentPane, "SettingsPage.fxml");
	}
	
	@FXML public void createPostButtonOnAction(ActionEvent event) {
		GUIBackend.loadContentPane(contentPane, "HomeFeedPage.fxml");
		GUIBackend.loadNewWindow("CreatePostPage.fxml");	
		mainPane.setEffect(new GaussianBlur(10));
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		GUIBackend.loadNewScene(stage, "SignInPage.fxml");	
	}
}
