package frontend.fxmlsControllers;

import backend.User;
import backend.UserCenter;
import backend.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SignUpController {
	@FXML private Label msgLabel;
	@FXML private TextField emailField;
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button signUpBtn;
	@FXML private Button backBtn;
	@FXML private Line emailLine;
	@FXML private Line usernameLine;
	@FXML private Line passwordLine;
	
	public void initialize() {
		System.out.println("Initialized Sign Up Page!");
	}

	@FXML public void signUpBtnOnAction(ActionEvent event) {
		if (!SignInController.users.usernameIsUnique(usernameField.getText()) || !UserCenter.isValidPassword(passwordField.getText()) || !UserCenter.isValidEmail(emailField.getText())) {
			msgLabel.setText("Failed, please try again.");
			msgLabel.setVisible(true);
			resetFields();
		} else {
			SignInController.users.insert(new User(usernameField.getText(), passwordField.getText(), emailField.getText()));
			msgLabel.setText("Success, account was created!");
			msgLabel.setVisible(true);
			Utilities.backupUsers(SignInController.users);	
			resetFields();	
		}
	}

	@FXML public void backBtnOnAction(ActionEvent event) {
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML public void checkFieldIsValid(MouseEvent event) {
		if (event.getSource().equals(emailField)) {
			String tempStyle = emailLine.getStyle();
			emailField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (UserCenter.isValidEmail(newValue)) emailLine.setStyle(tempStyle + "-fx-stroke: #38ff13;");
				else emailLine.setStyle(tempStyle + "-fx-stroke: #ff0000;");
			});
		} else if (event.getSource().equals(usernameField)) {
			String tempStyle = usernameField.getStyle();
			usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (SignInController.users.usernameIsUnique(newValue)) usernameLine.setStyle(tempStyle + "-fx-stroke: #38ff13;");
				else usernameLine.setStyle(tempStyle + "-fx-stroke: #ff0000;");
			});

		} else if (event.getSource().equals(passwordField)) {
			String tempStyle = passwordField.getStyle();
			passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (UserCenter.isValidPassword(newValue)) passwordLine.setStyle(tempStyle + "-fx-stroke: #38ff13;");
				else passwordLine.setStyle(tempStyle + "-fx-stroke: #ff0000;");
			});
		}
	}

	private void resetFields() {
		usernameField.clear();
		passwordField.clear();
		emailField.clear();
		emailLine.setStyle("-fx-stroke: #3b93ff;");
		usernameLine.setStyle("-fx-stroke: #3b93ff;");
		passwordLine.setStyle("-fx-stroke: #3b93ff;");
	}
	
}
