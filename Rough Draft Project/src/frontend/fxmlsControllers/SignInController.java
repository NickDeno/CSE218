package frontend.fxmlsControllers;

import java.io.File;

import backend.AppState;
import backend.PostList;
import backend.User;
import backend.UserCenter;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
	@FXML private PasswordField passwordField;
	@FXML private TextField usernameField;
	@FXML private Label msgLabel;
	@FXML private Button signInBtn;
	@FXML private Button cancelBtn; 
	@FXML private Hyperlink clickHereText;
	
	protected static UserCenter globalUsers;
	protected static User currentUser;
	protected static PostList globalPosts;
	protected static AppState appState;
	private Stage stage;
	
	public void initialize() {
//		//"Protected" instance of the current users and posts. Can be called by any class in fxmlsControllers package.
		appState = new File("backupData/AppState.dat").isFile() ? Utilities.restoreAppState() : new AppState(new UserCenter(50), new PostList());;
		globalUsers = appState.getAllUsers();
		globalPosts = appState.getAllPosts();
		globalUsers.display();
		globalPosts.display();	
		Platform.runLater(() -> {
			stage = (Stage)signInBtn.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				Utilities.backupAppState(appState);
			});
		});

	}
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		User tempUser = globalUsers.userSearch(usernameField.getText(), passwordField.getText());
		if(tempUser != null) {
			currentUser = tempUser;
			GUIBackend.loadNewScene(stage, GUIBackend.LandingScene);
		} else {
			msgLabel.setText("Account not found.");
			msgLabel.setVisible(true);
			usernameField.clear();
			passwordField.clear();
		}
	}
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	Utilities.backupAppState(appState);
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
    	GUIBackend.loadNewScene(stage, GUIBackend.SignUpScene);
	}
    
}
