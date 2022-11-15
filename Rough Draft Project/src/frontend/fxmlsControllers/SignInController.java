package frontend.fxmlsControllers;

import java.io.File;

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
	
	protected static UserCenter users;
	protected static User currentUser;
	protected static PostList allPosts;
	
	private Stage stage;
	
	public void initialize() {
		users = new File("backupData/Users.dat").isFile() ? Utilities.restoreUsers() : new UserCenter(50);;
		allPosts =  new File("backupData/AllPosts.dat").isFile() ? Utilities.restorePosts() : new PostList();;	
		users.display();
		allPosts.display();	
		//Runs after fxml page is initialized. At any time, if window is closed, all data will be backed up.
		Platform.runLater(() -> {
			stage = (Stage)signInBtn.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				Utilities.backupUsers(users);
				Utilities.backupPosts(allPosts);
			});
		});
	}
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		User tempUser = users.userSearch(usernameField.getText(), passwordField.getText());
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
    	Utilities.backupUsers(users);
		Utilities.backupPosts(allPosts);
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
    	GUIBackend.loadNewScene(stage, GUIBackend.SignUpScene);
	}
    
}
