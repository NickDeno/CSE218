package p5_with_SceneBuilder;

import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInSignUpController {
	@FXML private PasswordField signInPasswordField;
	@FXML private TextField signInUsernameField;
	@FXML private Label signInMessageLabel;
	@FXML private Button signInBtn;
	@FXML private Button cancelBtn; 
	@FXML private Hyperlink clickHereText;
	
	@FXML private TextField signUpEmailField;
	@FXML private PasswordField signUpPasswordField;
	@FXML private TextField signUpUsernameField;
	@FXML private Label signUpMessageLabel;
	@FXML private Button signUpBtn;
	@FXML private Button backBtn;
	
	static UserBag userBag = new UserBag(10);
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		if(userBag.getSize() == 0) {
			signInMessageLabel.setText("Account not found. Please create account.");
			signInMessageLabel.setVisible(true);
			signInUsernameField.clear();
			signInPasswordField.clear();
			return;
		}
		for(int i = 0; i < userBag.getSize(); i++) {
			if(signInUsernameField.getText().equals(userBag.peekUser(i).getUsername()) && (signInPasswordField.getText().equals(userBag.peekUser(i).getPassword()))) {
				signInMessageLabel.setText("Successfully Signed in!");
				signInMessageLabel.setVisible(true);
				signInUsernameField.clear();
				signInPasswordField.clear();
				break;
			} else {
				signInMessageLabel.setText("Account not found.");
				signInMessageLabel.setVisible(true);
				signInUsernameField.clear();
				signInPasswordField.clear();
			}
		}
	}
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) cancelBtn.getScene().getWindow();
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/p5_with_SceneBuilder/fxmls/SignUpScene.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Sign Up!");
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    @FXML public void signUpBtnOnAction(ActionEvent event) {
		if(usernameIsUnique()) {
			userBag.insert(new User(signUpUsernameField.getText(), signUpEmailField.getText(), signUpPasswordField.getText()));
			signUpUsernameField.clear();
			signUpEmailField.clear();
			signUpPasswordField.clear();
			signUpMessageLabel.setText("Success! Account was created.");
			signUpMessageLabel.setVisible(true);
		} else {
			signUpMessageLabel.setText("Failed. Make sure username is unique and try again.");
			signUpMessageLabel.setVisible(true);
			signUpUsernameField.clear();
		}
	}
    
    @FXML public void backBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) backBtn.getScene().getWindow();
    	stage.close();
    	System.out.println(userBag.getSize());
    }
    
    private boolean usernameIsUnique() {
		if(userBag.getSize() == 0) {
			return true;
		}
    	for(int i = 0; i <userBag.getSize(); i++) {
			if(signUpUsernameField.getText().equals(userBag.peekUser(i).getUsername())) {
				return false;
			}
		}
		return true;
	}
}
