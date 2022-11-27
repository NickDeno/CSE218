package frontend.fxmlsControllers;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import backend.Post;
import backend.PostCenter;
import backend.User;
import backend.UserCenter;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SettingsController {
	@FXML private AnchorPane anchorPane;
	@FXML private ListView<String> blockedUsersList;
	@FXML private CheckBox editFieldsBox;
	@FXML private TextField emailField;
	@FXML private Line emailLine;
	@FXML private Line passwordLine;
	@FXML private TextField passwordField;
	@FXML private Hyperlink deleteAccount;
	@FXML private Button blockUserBtn;
	@FXML private Button removeBlockedUserBtn;
	@FXML private Button backBtn;
	@FXML private Button saveChangesBtn;
	
	private LandingController landingController;
	
	//Initializer
	public SettingsController() {}
	
	public void initialize() {
		emailField.setText(UserCenter.getInstance().getCurrentUser().getEmail());
		passwordField.setText(UserCenter.getInstance().getCurrentUser().getPassword());
		for(User u: UserCenter.getInstance().getCurrentUser().getBlockedUsers().values()) {
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
			UserCenter.getInstance().getCurrentUser().getBlockedUsers().remove(blockedUsersList.getSelectionModel().getSelectedItem());
			blockedUsersList.getItems().remove(blockedUsersList.getSelectionModel().getSelectedIndex());
			//Refresh HomeFeed of posts so removed blocked user posts will be displayed
			landingController.homeFeedNode.getFxmlController().getTilePane().getChildren().clear();
			GUIBackend.displayPostsNewToOld(PostCenter.getInstance().getPosts(), landingController.homeFeedNode.getFxmlController().getTilePane(), landingController, true);
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
			Iterator<Entry<UUID, Post>> itr = UserCenter.getInstance().getCurrentUser().getUserPosts().entrySet().iterator();
			while(itr.hasNext()) {
				Post temp = itr.next().getValue();
				PostCenter.getInstance().removePost(temp.getUuid());
			}
			//Removes user from UserCenter
			UserCenter.getInstance().removeUser(UserCenter.getInstance().getCurrentUser().getUsername());
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
		UserCenter.getInstance().getCurrentUser().setEmail(emailField.getText());
		UserCenter.getInstance().getCurrentUser().setPassword(passwordField.getText());
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
		emailField.setText(UserCenter.getInstance().getCurrentUser().getEmail());
		passwordField.setText(UserCenter.getInstance().getCurrentUser().getPassword());
		emailLine.setStyle("-fx-stroke: #3b93ff;");
		passwordLine.setStyle("-fx-stroke: #3b93ff;");
		blockedUsersList.getSelectionModel().clearSelection();
		editFieldsBox.setSelected(false);
		setFieldsVisibility(false);
	}
	
	private void setFieldsVisibility(boolean bool) {
		if(bool == true) {
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
