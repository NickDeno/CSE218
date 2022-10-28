package frontend;

import backend.User;
import backend.UserCenter;
import backend.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignUpPage {
	@FXML private Label msgLabel;
	@FXML private TextField emailField;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button signUpBtn;
	@FXML private Button backBtn;
	@FXML private BorderPane signUpPane;
	
	public void initialize() {
		System.out.println("Initialized Sign Up Page!");
	}

	@FXML public void signUpBtnOnAction(ActionEvent event) {
		if (!SignInPage.users.usernameIsUnique(usernameField.getText()) || !UserCenter.isValidPassword(passwordField.getText()) || !UserCenter.isValidEmail(emailField.getText())) {
			msgLabel.setText("Failed, please try again.");
			msgLabel.setVisible(true);
			resetTextFields();
		} else {
			SignInPage.users.insert(new User(usernameField.getText(), passwordField.getText(), emailField.getText()));
			msgLabel.setText("Success, account was created!");
			msgLabel.setVisible(true);
			SignInPage.users.display();
			Utilities.backupUsers(SignInPage.users);	
			resetTextFields();	
		}
	}

	@FXML public void backBtnOnAction(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
		SignInPage.users.display();
	}
	
	@FXML public void checkFieldIsValid(MouseEvent event) {
		if (event.getSource().equals(emailField)) {
			String tempStyle = emailField.getStyle();
			emailField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (UserCenter.isValidEmail(newValue)) emailField.setStyle(tempStyle + "-fx-border-color:#38ff13;");
				else emailField.setStyle(tempStyle + "-fx-border-color:#ff0000;");
			});
		} else if (event.getSource().equals(usernameField)) {
			String tempStyle = usernameField.getStyle();
			usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (SignInPage.users.usernameIsUnique(newValue)) usernameField.setStyle(tempStyle + "-fx-border-color:#38ff13;");
				else usernameField.setStyle(tempStyle + "-fx-border-color:#ff0000;");
			});

		} else if (event.getSource().equals(passwordField)) {
			String tempStyle = passwordField.getStyle();
			passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (UserCenter.isValidPassword(newValue)) passwordField.setStyle(tempStyle + "-fx-border-color:#38ff13;");
				else passwordField.setStyle(tempStyle + "-fx-border-color:#ff0000;");	
			});
		}
	}

	private void resetTextFields() {
		usernameField.clear();
		passwordField.clear();
		emailField.clear();
		emailField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25; -fx-border-insets: -3; -fx-border-width: 3;");
		usernameField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25; -fx-border-insets: -3; -fx-border-width: 3;");
		passwordField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25; -fx-border-insets: -3; -fx-border-width: 3;");
	}
	
}
