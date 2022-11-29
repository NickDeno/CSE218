package controllers;

import java.io.ByteArrayInputStream;

import model.User;
import model.UserCenter;
import util.Utilities;
import util.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LandingController {
	@FXML private BorderPane borderPane;
	@FXML private Label userLabel;
	@FXML private Label numFollowingLabel;
	@FXML private Label numFollowersLabel;
	@FXML private Button homeBtn;
	@FXML private Button profileBtn;
	@FXML private Button settingsBtn;
	@FXML private Button postBtn;
	@FXML private Circle profilePic;
	@FXML private AnchorPane contentPane;
	
	private User currentUser;
	
	//Initializer
	public LandingController() {}
	
	public void initialize() {
		currentUser = UserCenter.getInstance().getCurrentUser();
		userLabel.setText(currentUser.getUsername());
		profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(currentUser.getProfilePic().returnBytes()))));
		numFollowingLabel.setText(String.valueOf(currentUser.getFollowing().size()));
		numFollowersLabel.setText(String.valueOf(currentUser.getFollowers().size()));
		userLabel.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
		profilePic.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
		
		HomeFeedController homeFeed = GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		homeFeed.setLandingController(this);
	}
	
	@FXML public void homeBtnOnAction(ActionEvent event) {
		HomeFeedController homeFeed = GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
		homeFeed.setLandingController(this);
	}
	
	@FXML public void profileBtnOnAction(ActionEvent event) {
		CurrentUserProfileController currentUserProfile = GUIBackend.loadPane(contentPane, GUIBackend.CurrentUserProfileScene);
		currentUserProfile.setLandingController(this);
	}
	
	@FXML public void settingsBtnOnAction(ActionEvent event) {
		SettingsController settings = GUIBackend.loadPane(contentPane, GUIBackend.SettingsScene);
		settings.setLandingController(this);
	}
	
	@FXML public void createPostBtnOnAction(ActionEvent event) {
		CreatePostController createPost = GUIBackend.loadPane(contentPane, GUIBackend.CreatePostScene);
		createPost.setLandingController(this);
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		Utilities.backupUserCenter();
		Utilities.backupPostCenter();
		Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
		GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);	
	}

	public AnchorPane getContentPane() {
		return contentPane;
	}
	
	public BorderPane getPane() {
		return borderPane;
	}
	
	public void setNumFollowingLabel(int numFollowing) {
		numFollowingLabel.setText(String.valueOf(numFollowing));
	}
	
	public void setProfilePic(Image image) {
		profilePic.setFill(new ImagePattern(image));
	}
	
}
