package frontend.fxmlsControllers;

import java.io.File;

import backend.PostList;
import backend.User;
import backend.UserCenter;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignInController {
	@FXML private PasswordField passwordField;
	@FXML private TextField usernameField;
	@FXML private Label msgLabel;
	@FXML private Button signInBtn;
	@FXML private Button cancelBtn; 
	@FXML private Hyperlink clickHereText;
	@FXML private BorderPane signInPane;
	
	protected static UserCenter users;
	protected static User currentUser;
	protected static PostList allPosts;
	
	public void initialize() {
		users = new File("backupData/Users.dat").isFile() ? Utilities.restoreUsers() : new UserCenter(50);;
		allPosts =  new File("backupData/AllUserPosts.dat").isFile() ? Utilities.restorePosts() : new PostList();;
		
		System.out.println("All Users:");
		users.display();
		System.out.println("All Posts:");
		allPosts.display();
		System.out.println("Initialized Sign In Page!");
		
	}
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		User tempUser = users.userSearch(usernameField.getText(), passwordField.getText());
		if(tempUser != null) {
			currentUser = users.userSearch(usernameField.getText(), passwordField.getText());
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			GUIBackend.loadNewScene(stage, GUIBackend.LandingScene);
		} else {
			msgLabel.setText("Account not found.");
			msgLabel.setVisible(true);
			usernameField.clear();
			passwordField.clear();
		}
	}
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) cancelBtn.getScene().getWindow();
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
		GUIBackend.loadNewWindow(GUIBackend.SignUpScene);
		msgLabel.setVisible(false);
	}
}
