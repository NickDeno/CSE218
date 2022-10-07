package frontend;

import backend.UserCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LandingPage {
	private static UserCenter users;
	
	@FXML private Label userLabel;
	
	public void initialize() {
		users = SignInSignUpPage.getUsers();
	}
	
	public void displayUsername(String username) {
		userLabel.setText("Welcome back " + username + "!");
	}
}
