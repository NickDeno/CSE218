package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.io.File;

import backend.FXImage;
import backend.User;
import backend.UserCenter;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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
	@FXML private Button browseBtn;
	@FXML private ImageView previewProfilePic;
	
	private File chosenImage;
	private byte[] chosenImageBytes;
	
	public void initialize() {
		//Sets default profile picture of user
		chosenImage = new File("src/frontend/assets/TempProfilePic.png");
		chosenImageBytes = GUIBackend.fileToByteArr(chosenImage);
		previewProfilePic.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
	}
	
	@FXML public void browseBtnOnAction(ActionEvent event) {
		FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    	chosenImage = fc.showOpenDialog(null);
    	if(chosenImage == null) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid File");
			alert.setHeaderText(null);
			alert.setContentText("Either chosen image was invalid, or no image was chosen. Please try again.");
			alert.showAndWait();
    	} else {
    		chosenImageBytes = GUIBackend.fileToByteArr(chosenImage);
    		previewProfilePic.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
    	}
	}

	@FXML public void signUpBtnOnAction(ActionEvent event) {
		if (!SignInController.globalUsers.usernameIsUnique(usernameField.getText())|| !UserCenter.isValidPassword(passwordField.getText()) || !UserCenter.isValidEmail(emailField.getText())) {
			msgLabel.setText("Failed, please try again.");
			msgLabel.setVisible(true);
			resetFields();
		} else {
			SignInController.globalUsers.insert(new User(usernameField.getText(), passwordField.getText(), emailField.getText(), new FXImage(chosenImageBytes)));
			Utilities.backupAppState(SignInController.appState);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Account Sucessfully Created!");
			alert.setHeaderText(null);
			alert.setContentText("Your account has been successfully created!");
			alert.showAndWait();
			Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
			GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);	
		}
	}

	@FXML public void backBtnOnAction(ActionEvent event) {
		Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
		GUIBackend.loadNewScene(stage, GUIBackend.SignInScene);
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
				if (SignInController.globalUsers.usernameIsUnique(newValue)) usernameLine.setStyle(tempStyle + "-fx-stroke: #38ff13;");
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
		chosenImage = new File("src/frontend/assets/TempProfilePic.png");
		chosenImageBytes = GUIBackend.fileToByteArr(chosenImage);
		previewProfilePic.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
		emailLine.setStyle("-fx-stroke: #3b93ff;");
		usernameLine.setStyle("-fx-stroke: #3b93ff;");
		passwordLine.setStyle("-fx-stroke: #3b93ff;");
	}
}
