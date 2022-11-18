package frontend.fxmlsControllers;

import java.io.File;

import backend.PostCenter;
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
	protected static PostCenter globalPosts;
	private Stage stage;
	
	public void initialize() {
		globalUsers = new File("backupData/Users.dat").isFile() ? Utilities.restoreUsers() : UserCenter.getInstance();
		globalPosts = new File("backupData/Posts.dat").isFile() ? Utilities.restorePosts() : PostCenter.getInstance();
//		globalUsers.display();
//		globalPosts.display();	
		PostCenter.getInstance().display();
		Platform.runLater(() -> {
			stage = (Stage)signInBtn.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				Utilities.backupUsers(globalUsers);
				Utilities.backupPosts();
			});
		});
	}
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		User tempUser = globalUsers.getUser(usernameField.getText());
		if(tempUser != null && tempUser.getPassword().equals(passwordField.getText())) {
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
    	Utilities.backupUsers(globalUsers);
    	Utilities.backupPosts();
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
    	GUIBackend.loadNewScene(stage, GUIBackend.SignUpScene);
	}
    
}
