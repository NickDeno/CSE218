package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import backend.FXImage;
import backend.Post;
import backend.PostCenter;
import backend.User;
import backend.UserCenter;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class SettingsController {
	@FXML private AnchorPane anchorPane;
	@FXML private ListView<String> blockedUsersList;
	@FXML private CheckBox editFieldsBox;
	@FXML private TextField emailField;
	@FXML private Line emailLine;
	@FXML private Line passwordLine;
	@FXML private TextField passwordField;
	@FXML private ImageView previewProfilePic;
	@FXML private Hyperlink deleteAccount;
	@FXML private Button browseBtn;
	@FXML private Button blockUserBtn;
	@FXML private Button removeBlockedUserBtn;
	@FXML private Button backBtn;
	@FXML private Button saveChangesBtn;
	
	private LandingController landingController;
	private byte[] chosenImageBytes;
	
	//Initializer
	public SettingsController() {}
	
	public void initialize() {
		emailField.setText(SignInController.currentUser.getEmail());
		passwordField.setText(SignInController.currentUser.getPassword());
		chosenImageBytes = SignInController.currentUser.getProfilePic().returnBytes();
		previewProfilePic.setImage(new Image(new ByteArrayInputStream(SignInController.currentUser.getProfilePic().returnBytes())));
		for(User u: SignInController.currentUser.getBlockedUsers().values()) {
			blockedUsersList.getItems().add(u.getUsername());
		}
	}
	
	@FXML public void editFieldsBoxOnAction(ActionEvent event) {
		if(editFieldsBox.isSelected()) {
			setFieldsVisibility(true);
		} else {
			setFieldsVisibility(false);
		}
	}
	
	@FXML public void browseBtnOnAction(ActionEvent event) {
		FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    	File chosenImage = fc.showOpenDialog(null);
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
	
	@FXML public void blockUserBtnOnAction(ActionEvent event) {
		anchorPane.setEffect(new GaussianBlur(15));
		landingController.getPane().setEffect(new GaussianBlur(15));
		BlockUserController blockUserController = GUIBackend.loadNewUndecoratedWindow(GUIBackend.BlockUserScene);
		blockUserController.setLandingController(landingController);
	}
	
	@FXML public void removeBlockedUserBtnOnAction(ActionEvent event) {
		if(blockedUsersList.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No User Selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a user first and try again.");
			alert.showAndWait();
		} else {
			SignInController.currentUser.getBlockedUsers().remove(blockedUsersList.getSelectionModel().getSelectedItem());
			blockedUsersList.getItems().remove(blockedUsersList.getSelectionModel().getSelectedIndex());
			//Refresh HomeFeed of posts so removed blocked user posts will be displayed
			landingController.homeFeedNode.getFxmlController().getTilePane().getChildren().clear();
			GUIBackend.displayPosts(PostCenter.getInstance(), SignInController.currentUser, 
					landingController.homeFeedNode.getFxmlController().getTilePane(), landingController);
		}
	}
	
	@FXML public void deleteAccountOnAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete your account?");
		Optional<ButtonType> confirm = alert.showAndWait();
		if(confirm.isPresent() && confirm.get() == ButtonType.OK) {
			//Removes every post instance that was made by this user from PostCenter
			Iterator<Entry<UUID, Post>> itr = SignInController.currentUser.getUserPosts().entrySet().iterator();
			while(itr.hasNext()) {
				Post temp = itr.next().getValue();
				PostCenter.getInstance().removePost(temp.getUuid());
			}
			//Removes user from UserCenter
			UserCenter.getInstance().removeUser(SignInController.currentUser.getUsername());
			Utilities.backupUserCenter();
			Utilities.backupPostCenter();
			GUIBackend.loadNewScene(((Stage)((Node)event.getSource()).getScene().getWindow()) , GUIBackend.SignInScene);
		}
	}
	
	@FXML public void backBtnOnAction(ActionEvent event) {
		resetFields();
		landingController.showPane(landingController.homeFeedNode.getPaneId());
	}
	
	@FXML public void saveChangesBtnOnAction(ActionEvent event) {
		if (!UserCenter.isValidEmail(emailField.getText()) || !UserCenter.isValidPassword(passwordField.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText(null);
			alert.setContentText("One or more of entered fields was invalid. Please try again.");
			alert.showAndWait();
			resetFields();
			return;
		}
		//This if statement is true when user changes profile pic, thus all post objects made by user must be updated with new profile pic
		if(SignInController.currentUser.getProfilePic().returnBytes() != chosenImageBytes){
			SignInController.currentUser.setProfilePic(new FXImage(chosenImageBytes));
			landingController.setProfilePic(new Image(new ByteArrayInputStream(chosenImageBytes)));
			Iterator<Entry<UUID, Post>> itr = SignInController.currentUser.getUserPosts().entrySet().iterator();
			while (itr.hasNext()) {
				Post temp = itr.next().getValue();
				temp.getPoster().setProfilePic(new FXImage(chosenImageBytes));
				PostCenter.getInstance().getPost(temp.getUuid()).getPoster().setProfilePic(new FXImage(chosenImageBytes));
			}
			//Reloads Posts in HomeFeed
			landingController.homeFeedNode.getFxmlController().getTilePane().getChildren().clear();
			GUIBackend.displayPosts(PostCenter.getInstance(), SignInController.currentUser, 
					landingController.homeFeedNode.getFxmlController().getTilePane(), landingController);
		} 
		SignInController.currentUser.setEmail(emailField.getText());
		SignInController.currentUser.setPassword(passwordField.getText());
		resetFields();
	}
	
	@FXML public void checkFieldIsValid(MouseEvent event) {
		if (event.getSource().equals(emailField)) {
			String tempStyle = emailLine.getStyle();
			emailField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (UserCenter.isValidEmail(newValue)) emailLine.setStyle(tempStyle + "-fx-stroke: #38ff13;");
				else emailLine.setStyle(tempStyle + "-fx-stroke: #ff0000;");
			});
		} else if (event.getSource().equals(passwordField)) {
			String tempStyle = passwordField.getStyle();
			passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
				if (UserCenter.isValidPassword(newValue)) passwordLine.setStyle(tempStyle + "-fx-stroke: #38ff13;");
				else passwordLine.setStyle(tempStyle + "-fx-stroke: #ff0000;");
			});
		}
	}
	
	public void resetFields() {
		emailField.setText(SignInController.currentUser.getEmail());
		passwordField.setText(SignInController.currentUser.getPassword());
		emailLine.setStyle("-fx-stroke: #3b93ff;");
		passwordLine.setStyle("-fx-stroke: #3b93ff;");
		chosenImageBytes = SignInController.currentUser.getProfilePic().returnBytes();
		previewProfilePic.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
		blockedUsersList.getSelectionModel().clearSelection();
		editFieldsBox.setSelected(false);
		setFieldsVisibility(false);
	}
	
	private void setFieldsVisibility(boolean bool) {
		if(bool == true) {
			browseBtn.setVisible(true);
			browseBtn.setDisable(false);
			blockUserBtn.setVisible(true);
			blockUserBtn.setDisable(false);
			removeBlockedUserBtn.setVisible(true);
			removeBlockedUserBtn.setDisable(false);
			emailField.setDisable(false);
			emailField.setEditable(true);
			passwordField.setDisable(false);
			passwordField.setEditable(true);
			blockedUsersList.setEditable(true);
			blockedUsersList.setFocusTraversable(true);
		} else {
			browseBtn.setVisible(false);
			browseBtn.setDisable(true);
			blockUserBtn.setVisible(false);
			blockUserBtn.setDisable(true);
			removeBlockedUserBtn.setVisible(false);
			removeBlockedUserBtn.setDisable(true);
			emailField.setDisable(true);
			emailField.setEditable(false);
			passwordField.setDisable(true);
			passwordField.setEditable(false);
			blockedUsersList.setEditable(false);
			blockedUsersList.setFocusTraversable(false);
			blockedUsersList.getSelectionModel().clearSelection();
		}
	}
	
	public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	
	public ListView<String> getBlockedUsersList(){
		return blockedUsersList;
	}
	
	public AnchorPane getPane() {
		return anchorPane;
	}
}
