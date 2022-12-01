package controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import model.FXImage;
import model.PostCenter;
import model.User;
import model.UserCenter;
import util.Utilities;
import util.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CurrentUserProfileController {
	@FXML private Rectangle bannerPic;
	@FXML private Circle profilePic;
	@FXML private Label usernameLabel;
	@FXML private TextField nicknameField;
    @FXML private TextArea bioField;
    
    @FXML private Label viewingLabel;
    @FXML private Button postsBtn;
    @FXML private Button followersBtn;
    @FXML private Button followingBtn;
    @FXML private Label numPosts;
    @FXML private Label numFollowers;
    @FXML private Label numFollowing;
    @FXML private Line postsBtnLine;
    @FXML private Line followersBtnLine;
    @FXML private Line followingBtnLine;
    
    @FXML private ScrollPane scrollPane;
    @FXML private TilePane tilePane;
    @FXML private ListView<String> followersList;
    @FXML private ListView<String> followingList;
    
    @FXML private Button changeBannerPicBtn;
    @FXML private Button changeProfilePicBtn;
    @FXML private Button backBtn;
    @FXML private Button resetBtn;
    @FXML private Button saveBtn;
    @FXML private CheckBox editFieldsBox;
    
    private LandingController landingController;
    private User currentUser;
    private byte[] chosenProfilePicBytes;
    private byte[] chosenBannerPicBytes;
    
    //Initializer
    public CurrentUserProfileController() {}
    
    
    public void initialize() {
    	currentUser = UserCenter.getInstance().getCurrentUser();
    	bannerPic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(currentUser.getBannerPic().returnBytes()))));	
		profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(currentUser.getProfilePic().returnBytes()))));
		usernameLabel.setText(currentUser.getUsername());
		nicknameField.setText(currentUser.getNickName());
		bioField.setText(currentUser.getBio());
		numPosts.setText(String.valueOf(currentUser.getUserPosts().size()));
		numFollowers.setText(String.valueOf(currentUser.getFollowers().size()));
		numFollowing.setText(String.valueOf(currentUser.getFollowing().size()));
    	followersList.getItems().clear();
		for(User u: currentUser.getFollowers()) {
			followersList.getItems().add(u.getUsername());
		}
		followingList.getItems().clear();
    	for(User u: currentUser.getFollowing().values()) {
			followingList.getItems().add(u.getUsername());
		}
    	
    	Platform.runLater(() -> {	
    		displayPosts(currentUser.getUserPosts());
    	});
    }

    @FXML public void changeBannerPicBtnOnAction(ActionEvent event) {
    	FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid File");
			alert.setHeaderText(null);
			alert.setContentText("Either chosen image was invalid, or no image was chosen. Please try again.");
			alert.showAndWait();
		} else {
			chosenBannerPicBytes = Utilities.fileToByteArr(selectedFile);
			bannerPic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(chosenBannerPicBytes))));		
		}
    }

    @FXML public void changeProfilePicBtnOnAction(ActionEvent event) {
    	FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid File");
			alert.setHeaderText(null);
			alert.setContentText("Either chosen image was invalid, or no image was chosen. Please try again.");
			alert.showAndWait();
		} else {
			chosenProfilePicBytes = Utilities.fileToByteArr(selectedFile);
			profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(chosenProfilePicBytes))));			
		}
    }
    
    @FXML public void postsBtnOnAction(ActionEvent event) {
    	viewingLabel.setText("Posts");
    	scrollPane.setVisible(true);
    	followersList.setVisible(false);
    	followingList.setVisible(false);
    	postsBtnLine.setVisible(true);
    	followersBtnLine.setVisible(false);
    	followingBtnLine.setVisible(false);
    }

    @FXML public void followersBtnOnAction(ActionEvent event) {
    	viewingLabel.setText("Followers");
    	scrollPane.setVisible(false);
    	followersList.setVisible(true);
    	followingList.setVisible(false);
    	postsBtnLine.setVisible(false);
    	followersBtnLine.setVisible(true);
    	followingBtnLine.setVisible(false);
    }

    @FXML public void followingBtnOnAction(ActionEvent event) {
    	viewingLabel.setText("Following");
    	scrollPane.setVisible(false);
    	followersList.setVisible(false);
    	followingList.setVisible(true);
    	postsBtnLine.setVisible(false);
    	followersBtnLine.setVisible(false);
    	followingBtnLine.setVisible(true);
    }
    
    @FXML public void resetBtnOnAction(ActionEvent event) {
    	resetFields();
    	
    }

    @FXML public void saveBtnOnAction(ActionEvent event) {	
    	if(currentUser.getBannerPic().returnBytes() != chosenBannerPicBytes && chosenBannerPicBytes != null) {
        	currentUser.setBannerPic(new FXImage(chosenBannerPicBytes));
    	}
    	//This if statement is true when user changes profile pic, thus all post objects made by user must be updated with new profile pic
    	if(currentUser.getProfilePic().returnBytes() != chosenProfilePicBytes && chosenProfilePicBytes != null){
			currentUser.setProfilePic(new FXImage(chosenProfilePicBytes));
			landingController.setProfilePic(new Image(new ByteArrayInputStream(chosenProfilePicBytes)));
			Iterator<UUID> itr = currentUser.getUserPosts().iterator();
			while (itr.hasNext()) {
				UUID tempUUID = itr.next();
				PostCenter.getInstance().getPost(tempUUID).getPoster().setProfilePic(new FXImage(chosenProfilePicBytes));
			}
			//Reloads Posts displayed with new profile picture
			displayPosts(currentUser.getUserPosts());
			
		} 
    	currentUser.setNickName(nicknameField.getText());
    	currentUser.setBio(bioField.getText());
		resetFields();
    }
    
    @FXML public void editFieldsBoxOnAction(ActionEvent event) {
    	if(editFieldsBox.isSelected()) {
			setFieldsVisibility(true);
		} else {
			setFieldsVisibility(false);
		}
    }
    
    public void resetFields() {
    	bannerPic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(currentUser.getBannerPic().returnBytes()))));
    	profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(currentUser.getProfilePic().returnBytes()))));	
    	nicknameField.setText(currentUser.getNickName());
    	bioField.setText(currentUser.getBio());
		editFieldsBox.setSelected(false);
		setFieldsVisibility(false);
    }
    
    private void setFieldsVisibility(boolean visibility) {
    	if(visibility == true) {
    		nicknameField.setEditable(true);
    		bioField.setEditable(true);
    		changeBannerPicBtn.setVisible(true);
    		changeBannerPicBtn.setDisable(false);
    		changeProfilePicBtn.setVisible(true);
    		changeProfilePicBtn.setDisable(false);
		} else {
			nicknameField.setEditable(false);
    		bioField.setEditable(false);
    		changeBannerPicBtn.setVisible(false);
    		changeBannerPicBtn.setDisable(true);
    		changeProfilePicBtn.setVisible(false);
    		changeProfilePicBtn.setDisable(true);
		}
    }
    
    public void displayPosts(LinkedList<UUID> posts) {
    	tilePane.getChildren().clear();
    	GUIBackend.displayUserPosts(posts, tilePane, landingController);
    }
	
	public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	}
}
