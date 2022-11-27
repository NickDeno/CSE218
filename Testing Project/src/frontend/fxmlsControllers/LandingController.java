package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;

import backend.StackPaneNode;
import backend.UserCenter;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
	@FXML private StackPane contentPane;
	
	//StackPaneNode contains FXMLController of each pane, and id of each pane. The id of each pane is used to swap between panes in showPane method.
	//The FXMLController of each pane can be passed into another FXMLController so the 2 scenes can communicate.
	protected StackPaneNode<HomeFeedController> homeFeedNode;
	protected StackPaneNode<CurrentUserProfileController> profileNode;
	protected StackPaneNode<SettingsController> settingsNode;
	protected StackPaneNode<CreatePostController> createPostNode;
	
	//Initializer
	public LandingController() {}
	
	public void initialize() {
		userLabel.setText(UserCenter.getInstance().getCurrentUser().getUsername());
		profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(UserCenter.getInstance().getCurrentUser().getProfilePic().returnBytes()))));
		numFollowingLabel.setText(String.valueOf(UserCenter.getInstance().getCurrentUser().getFollowing().size()));
		numFollowersLabel.setText(String.valueOf(UserCenter.getInstance().getCurrentUser().getFollowers().size()));
		userLabel.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
		profilePic.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
		
		homeFeedNode = GUIBackend.loadStackPane(contentPane, GUIBackend.HomeFeedScene, "HomeFeedPane");
		profileNode = GUIBackend.loadStackPane(contentPane, GUIBackend.ProfileScene, "ProfilePane");
		settingsNode = GUIBackend.loadStackPane(contentPane, GUIBackend.SettingsScene, "SettingsPane");
		createPostNode = GUIBackend.loadStackPane(contentPane, GUIBackend.CreatePostScene, "CreatePostPane");
		homeFeedNode.getFxmlController().setLandingController(this);
		profileNode.getFxmlController().setLandingController(this);
		settingsNode.getFxmlController().setLandingController(this);
		createPostNode.getFxmlController().setLandingController(this);
		showPane(homeFeedNode.getPaneId());
	}
	
	@FXML public void homeBtnOnAction(ActionEvent event) {
		showPane(homeFeedNode.getPaneId());
		settingsNode.getFxmlController().resetFields();
	}
	
	@FXML public void profileBtnOnAction(ActionEvent event) {
		showPane(profileNode.getPaneId());
	}
	
	@FXML public void settingsBtnOnAction(ActionEvent event) {
		showPane(settingsNode.getPaneId());
	}
	
	@FXML public void createPostBtnOnAction(ActionEvent event) {
		showPane(createPostNode.getPaneId());;
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		Utilities.backupUserCenter();
		Utilities.backupPostCenter();
		Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
		GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);	
	}
	
	public void showPane(String paneId) {
		for(int i = 0; i < contentPane.getChildren().size(); i++) {
			if(contentPane.getChildren().get(i).getId().equals(paneId)) {
				contentPane.getChildren().get(i).toFront();
			}
		}
	}

	public StackPane getContentPane() {
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
