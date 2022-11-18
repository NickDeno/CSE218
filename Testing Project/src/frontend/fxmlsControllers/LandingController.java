package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;

import backend.StackPaneNode;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LandingController {
	@FXML private Label userLabel;
	@FXML private Button homeBtn;
	@FXML private Button profileBtn;
	@FXML private Button settingsBtn;
	@FXML private Button postBtn;
	@FXML private ImageView profilePic;
	@FXML private StackPane contentPane;
	
	//StackPaneNode contains FXMLController of each pane, and id of each pane. The id of each pane is used to swap between panes in showPane method.
	//The FXMLController of each pane can be passed into another FXMLController so the 2 scenes can communicate.
	protected StackPaneNode<HomeFeedController> homeFeedNode;
	protected StackPaneNode<ProfileController> profileNode;
	protected StackPaneNode<SettingsController> settingsNode;
	protected StackPaneNode<CreatePostController> createPostNode;
	
	//Initializer
	public LandingController() {}
	
	public void initialize() {
		userLabel.setText(SignInController.currentUser.getUsername());
		profilePic.setImage(new Image(new ByteArrayInputStream(SignInController.currentUser.getProfilePic().returnBytes())));
		
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
		Utilities.backupUsers(SignInController.globalUsers);
		Utilities.backupPosts();
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
	
	public StackPaneNode<HomeFeedController> getHomeFeedNode(){
		return homeFeedNode;
	}
	
	public StackPaneNode<CreatePostController> getCreatePostNode() {
		return createPostNode;
	}

	public StackPane getContentPane() {
		return contentPane;
	}
	
}