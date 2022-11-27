package frontend.fxmlsControllers;

import java.util.Collection;

import backend.PostCenter;
import backend.User;
import backend.UserCenter;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BlockUserController {
	@FXML private Button blockSelectedUserBtn;
	@FXML private Button cancelBtn;
	@FXML private ListView<String> userList;
	
	private LandingController landingController;
	
	public void initialize() {
		Platform.runLater(() -> {
			((Stage)cancelBtn.getScene().getWindow()).setOnCloseRequest(e -> {
				landingController.settingsNode.getFxmlController().getPane().setEffect(null);
				landingController.getPane().setEffect(null);
			});
		});
		Collection<User> allUsers = UserCenter.getInstance().getAllUsers();
		for(User u: allUsers) {
			userList.getItems().add(u.getUsername());
		}
	}
	
	@FXML public void blockSelectedUserBtnOnAction(ActionEvent event) {
		if(userList.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Selection");
			alert.setHeaderText(null);
			alert.setContentText("Please select a user to block and try again.");
			alert.showAndWait();
			return;
		}
		
		//If selected user is the current user
		if(userList.getSelectionModel().getSelectedItem().equals(UserCenter.getInstance().getCurrentUser().getUsername())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Selection");
			alert.setHeaderText(null);
			alert.setContentText("Sorry. You cannot block yourself");
			alert.showAndWait();
			userList.getSelectionModel().clearSelection();
			return; 
		}
		//If selected user is already present in current users blockedUsers
		if(UserCenter.getInstance().getCurrentUser().getBlockedUsers().get(userList.getSelectionModel().getSelectedItem()) != null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Selection");
			alert.setHeaderText(null);
			alert.setContentText("This user is already blocked.");
			alert.showAndWait();
			userList.getSelectionModel().clearSelection();
		} else {
			User blockedUser = UserCenter.getInstance().getUser(userList.getSelectionModel().getSelectedItem());
			UserCenter.getInstance().getCurrentUser().getBlockedUsers().put(blockedUser.getUsername(), blockedUser);
			landingController.settingsNode.getFxmlController().getBlockedUsersList().getItems().add(blockedUser.getUsername());
			//Refresh HomeFeed of posts so new blocked user posts wont be displyed
			landingController.homeFeedNode.getFxmlController().getTilePane().getChildren().clear();
			GUIBackend.displayPostsNewToOld(PostCenter.getInstance().getPosts(), landingController.homeFeedNode.getFxmlController().getTilePane(), landingController, true);
			landingController.settingsNode.getFxmlController().getPane().setEffect(null);
			landingController.getPane().setEffect(null);
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
		}
		
	}
	
	@FXML public void cancelBtnOnAction(ActionEvent event) {
		landingController.settingsNode.getFxmlController().getPane().setEffect(null);
		landingController.getPane().setEffect(null);
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	}
	
	public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	
}
