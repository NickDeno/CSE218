package frontend;

import java.io.IOException;

import backend.User;
import backend.UserCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LandingPage {
	private static UserCenter users;
	private static User currentUser;
	
	@FXML private Label userLabel;
	@FXML private Label usernameLabel;
	@FXML private Button profileButton;
	@FXML private AnchorPane contentPane;
	
	public void initialize() {
		users = SignInSignUpPage.getUsers();
		currentUser = SignInSignUpPage.getCurrentUser();
		usernameLabel.setText(currentUser.getUsername());
		userLabel.setText("Welcome back " + currentUser.getUsername() + " !");
		
		
		System.out.println("Initialized Landing Page!");
		users.display();
	}
	
	@FXML public void homeButtonOnAction(ActionEvent event) {
		loadContentPane("HomeFeedPage");
	}
	
	@FXML public void profileButtonOnAction(ActionEvent event) {
		loadContentPane("ProfilePage");
	}
	
	@FXML public void settingsButtonOnAction(ActionEvent event) {
		loadContentPane("SettingsPage");
	}
	
	@FXML public void createPostButtonOnAction(ActionEvent event) {
		loadContentPane("HomeFeedPage");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/frontend/fxmls/CreatePostPage.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Create New Post!");
			stage.setResizable(false);
			stage.show();
			
		} catch (IOException e) {
			System.out.println("Unable to load fxml file.");
			e.printStackTrace();
		}	
	}
	
	@FXML public void logoutButtonOnAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/frontend/fxmls/SignInPage.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Main Page!");
			stage.show();
		} catch (IOException e) {
			System.out.println("Unable to laod SignInPage fxml file.");
			e.printStackTrace();
		}		
	}
	
	private void loadContentPane(String page) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/frontend/fxmls/" + page + ".fxml"));
			contentPane.getChildren().clear();
			contentPane.getChildren().setAll(pane);
		} catch (IOException e) {
			System.out.println("Unable to load content page fxml file");
			e.printStackTrace();
		}
	}
}
