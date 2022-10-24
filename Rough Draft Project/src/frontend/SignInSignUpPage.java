package frontend;

import java.io.File;
import java.io.IOException;

import backend.User;
import backend.UserCenter;
import backend.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignInSignUpPage {
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
	
	private static UserCenter users;
	private static User currentUser;
	
	public SignInSignUpPage() {
		
	}
	
	public void initialize() {
		users = new File("backupData/Users.dat").isFile() ? Utilities.restoreUsers() : new UserCenter(50);;
		users.display();
		System.out.println("Initialized Sign In/Up Page!");
	}
	
	@FXML public void signInBtnOnAction(ActionEvent event) {
		if(users.userExists(signInUsernameField.getText(), signInPasswordField.getText())) {
			currentUser = users.userSearch(signInUsernameField.getText());
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/frontend/fxmls/LandingPage.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Main Page!");
				stage.show();		
			} catch (IOException e) {
				System.out.println("Unable to load LandingPage scene.");
				e.printStackTrace();
			}
		} else {
			signInMessageLabel.setText("Account not found.");
			signInMessageLabel.setVisible(true);
			signInUsernameField.clear();
			signInPasswordField.clear();
		}
	}
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) cancelBtn.getScene().getWindow();
    	stage.close();
    }
    
    @FXML public void clickHereOnAction(ActionEvent event) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/frontend/fxmls/SignUpPage.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Sign Up!");
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				System.out.println("Unable to load SignUpPage scene.");
				e.printStackTrace();
			}
	}
    
    @FXML public void checkFieldIsValid(MouseEvent event) {
    	if(event.getSource().equals(signUpEmailField)) {
    		String tempStyle = signUpEmailField.getStyle();
        	signUpEmailField.textProperty().addListener((observable, oldValue, newValue) -> {
            	if(UserCenter.validEmail(newValue))
            		signUpEmailField.setStyle(tempStyle + "-fx-border-color:#38ff13;");
            	else if(!UserCenter.validEmail(newValue))
            		signUpEmailField.setStyle(tempStyle + "-fx-border-color:#ff0000;"); 
        	});
    	}else if(event.getSource().equals(signUpUsernameField)) {
    		String tempStyle = signUpUsernameField.getStyle();
        	signUpUsernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            	if(users.usernameIsUnique(newValue)) 
            		signUpUsernameField.setStyle(tempStyle + "-fx-border-color:#38ff13;");
            	else if(!users.usernameIsUnique(newValue)) 
            		signUpUsernameField.setStyle(tempStyle + "-fx-border-color:#ff0000;"); 
        	});
    		
    	}else if(event.getSource().equals(signUpPasswordField)) {
        	String tempStyle = signUpPasswordField.getStyle();
        	signUpPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            	if(UserCenter.validPassword(newValue))
            		signUpPasswordField.setStyle(tempStyle + "-fx-border-color:#38ff13;");
            	else if(!UserCenter.validPassword(newValue))
            		signUpPasswordField.setStyle(tempStyle + "-fx-border-color:#ff0000;");
        	});
    	}
    }
    
    @FXML public void signUpBtnOnAction(ActionEvent event) {
    	if(!users.usernameIsUnique(signUpUsernameField.getText()) || !UserCenter.validPassword(signUpPasswordField.getText())|| !UserCenter.validEmail(signUpEmailField.getText())){
    		signUpMessageLabel.setText("Failed, please try again.");
			signUpMessageLabel.setVisible(true);
			resetTextFields();
			return;
    	}
    	users.insert(new User(signUpUsernameField.getText(), signUpPasswordField.getText(), signUpEmailField.getText()));
    	signUpMessageLabel.setText("Success, account was created!");
    	signUpMessageLabel.setVisible(true);
    	resetTextFields();
		Utilities.backupUsers(users);
	}
    
    @FXML public void backBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) backBtn.getScene().getWindow();
    	stage.close();
    	users.display();
    }
    
    public static UserCenter getUsers() {
    	return users;
    }
    
    public static User getCurrentUser() {
    	return currentUser;
    }
    
    private void resetTextFields() {
    	signUpUsernameField.clear();
		signUpPasswordField.clear();
		signUpEmailField.clear();
		signUpEmailField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25; -fx-border-insets: -3; -fx-border-width: 3;");
		signUpUsernameField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25; -fx-border-insets: -3; -fx-border-width: 3;");
		signUpPasswordField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25; -fx-border-insets: -3; -fx-border-width: 3;");
    }
}
