package frontend.fxmlsControllers;

import backend.PostCenter;
import backend.User;
import backend.UserCenter;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
	@FXML private PasswordField passwordField;
	@FXML private TextField visiblePasswordField;
	@FXML private CheckBox showPasswordBox;
	@FXML private TextField usernameField;
	@FXML private Label msgLabel;
	@FXML private Button signInBtn;
	@FXML private Button cancelBtn; 
	@FXML private Hyperlink clickHereText;
	
	private Stage stage;
	
	public void initialize() {	
		UserCenter.getInstance();
		PostCenter.getInstance();
		if(UserCenter.getInstance() != null) UserCenter.getInstance().display();
		if(PostCenter.getInstance() != null) PostCenter.getInstance().display();
		Platform.runLater(() -> {
			stage = (Stage)signInBtn.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				Utilities.backupUserCenter();
				Utilities.backupPostCenter();
			});
		});
	}
	
	@FXML public void showPasswordBoxOnAction(ActionEvent event) {
		if(showPasswordBox.isSelected()) {
			visiblePasswordField.setText(passwordField.getText());
			visiblePasswordField.setVisible(true);
			passwordField.setVisible(false);
		} else {
			passwordField.setText(visiblePasswordField.getText());
			passwordField.setVisible(true);
			visiblePasswordField.setVisible(false);
		}
	}
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		String chosenPassword;
		if(showPasswordBox.isSelected()) chosenPassword = visiblePasswordField.getText();
		else chosenPassword = passwordField.getText();
		
		User tempUser = UserCenter.getInstance().getUser(usernameField.getText());
		if(tempUser != null && tempUser.getPassword().equals(chosenPassword)) {
			UserCenter.getInstance().setCurrentUser(tempUser);
			GUIBackend.loadNewScene(stage, GUIBackend.LandingScene);
		} else {
			msgLabel.setText("Account not found.");
			msgLabel.setVisible(true);
			usernameField.clear();
			passwordField.clear();
			visiblePasswordField.clear();
			showPasswordBox.setSelected(false);
		}
	}
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	Utilities.backupUserCenter();
		Utilities.backupPostCenter();
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
    	GUIBackend.loadNewScene(stage, GUIBackend.SignUpScene);
	}
    
}
