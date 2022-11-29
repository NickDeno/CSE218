package controllers;

import java.io.ByteArrayInputStream;
import java.util.LinkedHashMap;
import java.util.UUID;

import model.Post;
import model.User;
import model.UserCenter;
import util.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class UserProfileController {
	@FXML private Rectangle bannerPic;
	@FXML private Circle profilePic;
	@FXML private Label usernameLabel;
	@FXML private Label nicknameLabel;
    @FXML private TextArea bioField;
    @FXML private Button followBtn;
    @FXML private Button unfollowBtn;
    @FXML private Button backBtn;
    
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
    
    
    private User user;
    private User currentUser;
    private LandingController landingController;
    
    //Initializer
    public UserProfileController() {}
    
    public void initialize() {
    	currentUser = UserCenter.getInstance().getCurrentUser();
    	Platform.runLater(() -> {
    		bannerPic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(user.getBannerPic().returnBytes()))));
    		profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(user.getProfilePic().returnBytes()))));
    		usernameLabel.setText(user.getUsername());
    		nicknameLabel.setText(user.getNickName());
    		bioField.setText(user.getBio());
    		numPosts.setText(String.valueOf(user.getUserPosts().size()));
    		numFollowers.setText(String.valueOf(user.getFollowers().size()));
    		numFollowing.setText(String.valueOf(user.getFollowing().size())); 	
        	followersList.getItems().clear();
    		for(User u: user.getFollowers().values()) {
    			followersList.getItems().add(u.getUsername());
    		}
    		followingList.getItems().clear();
        	for(User u: user.getFollowing().values()) {
    			followingList.getItems().add(u.getUsername());
    		}
    		
    		if(currentUser.getUsername().equals(user.getUsername())) {
    			followBtn.setVisible(false);
    			followBtn.setDisable(true);
    			unfollowBtn.setVisible(false);
    			unfollowBtn.setDisable(true);
    		} else if(currentUser.getFollowing().get(user.getUsername()) != null) {
    			followBtn.setVisible(false);
    			followBtn.setDisable(true);
    		} else {
    			unfollowBtn.setVisible(false);
    			unfollowBtn.setVisible(true);
    		}	
    		
    		displayPosts(user.getUserPosts());
    		
    	});
    }
    
    @FXML public void backBtnOnAction(ActionEvent event) {
    	HomeFeedController homeFeed = GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.HomeFeedScene);
    	homeFeed.setLandingController(landingController);
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

    @FXML public void followBtnOnAction(ActionEvent event) {
    	user.getFollowers().put(currentUser.getUsername(), currentUser);
    	currentUser.getFollowing().put(user.getUsername(), user);
    	followersList.getItems().add(currentUser.getUsername());
    	numFollowers.setText(String.valueOf(user.getFollowers().size()));
    	landingController.setNumFollowingLabel(currentUser.getFollowing().size());
    	
    	followBtn.setVisible(false);
    	followBtn.setDisable(true);
    	unfollowBtn.setVisible(true);
    	unfollowBtn.setDisable(false);
    	
    }

    @FXML public void unfollowBtnOnAction(ActionEvent event) {
    	user.getFollowers().remove(currentUser.getUsername());
    	currentUser.getFollowing().remove(user.getUsername());
    	followersList.getItems().remove(currentUser.getUsername());
    	numFollowers.setText(String.valueOf(user.getFollowers().size()));
    	landingController.setNumFollowingLabel(currentUser.getFollowing().size());
    	
    	unfollowBtn.setVisible(false);
    	unfollowBtn.setDisable(true);
    	followBtn.setVisible(true);
    	followBtn.setDisable(false);
    }
    
    public void displayPosts(LinkedHashMap<UUID, Post> posts) {
    	tilePane.getChildren().clear();
    	GUIBackend.displayPostsNewToOld(posts, tilePane, landingController, false);
    }
    
    public void setUser(User user) {
    	this.user = UserCenter.getInstance().getUser(user.getUsername());
    }
    
    public void setLandingController(LandingController landingController) {
    	this.landingController = landingController;
    }
}
