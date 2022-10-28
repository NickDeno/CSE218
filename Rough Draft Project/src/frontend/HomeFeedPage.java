package frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeFeedPage {
	@FXML private Label userLabel;
	
	public void initialize() {
		userLabel.setText("Welcome Back " + SignInPage.currentUser.getUsername() + " !");
	}
}
