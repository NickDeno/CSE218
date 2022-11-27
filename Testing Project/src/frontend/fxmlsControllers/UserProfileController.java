package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;

import backend.User;
import backend.UserCenter;
import frontend.GUIBackend;
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
    private LandingController landingController;
    
    //Initializer
    public UserProfileController() {}
    
    public void initialize() {
    	Platform.runLater(() -> {
    		if(user.getBannerPic() != null) bannerPic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(user.getBannerPic().returnBytes()))));
    		if(user.getNickName() != null) nicknameLabel.setText(user.getNickName());
    		if(user.getBio() != null) bioField.setText(user.getBio());
    		
    		usernameLabel.setText(user.getUsername());
    		profilePic.setFill(new ImagePattern(new Image(new ByteArrayInputStream(user.getProfilePic().returnBytes()))));
    		numPosts.setText(String.valueOf(user.getUserPosts().size()));
    		numFollowers.setText(String.valueOf(user.getFollowers().size()));
    		numFollowing.setText(String.valueOf(user.getFollowing().size()));
    		
    		if(UserCenter.getInstance().getCurrentUser().getUsername().equals(user.getUsername())) {
    			followBtn.setVisible(false);
    			followBtn.setDisable(true);
    			unfollowBtn.setVisible(false);
    			unfollowBtn.setDisable(true);
    		} else if(UserCenter.getInstance().getCurrentUser().getFollowing().get(user.getUsername()) != null) {
    			followBtn.setVisible(false);
    			followBtn.setDisable(true);
    		} else {
    			unfollowBtn.setVisible(false);
    			unfollowBtn.setVisible(true);
    		}
    		
    		GUIBackend.displayPostsNewToOld(user.getUserPosts(), tilePane, landingController, false);
        	followersList.getItems().clear();
    		for(User u: user.getFollowers().values()) {
    			followersList.getItems().add(u.getUsername());
    		}
    		followingList.getItems().clear();
        	for(User u: user.getFollowing().values()) {
    			followingList.getItems().add(u.getUsername());
    		}
    	});
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
    	user.getFollowers().put(UserCenter.getInstance().getCurrentUser().getUsername(), UserCenter.getInstance().getCurrentUser());
    	UserCenter.getInstance().getCurrentUser().getFollowing().put(user.getUsername(), user);
    	followersList.getItems().add(UserCenter.getInstance().getCurrentUser().getUsername());
    	numFollowers.setText(String.valueOf(user.getFollowers().size()));
    	landingController.setNumFollowingLabel(UserCenter.getInstance().getCurrentUser().getFollowing().size());
    	
    	followBtn.setVisible(false);
    	followBtn.setDisable(true);
    	unfollowBtn.setVisible(true);
    	unfollowBtn.setDisable(false);
    	
    }

    @FXML public void unfollowBtnOnAction(ActionEvent event) {
    	user.getFollowers().remove(UserCenter.getInstance().getCurrentUser().getUsername());
    	UserCenter.getInstance().getCurrentUser().getFollowing().remove(user.getUsername());
    	followersList.getItems().remove(UserCenter.getInstance().getCurrentUser().getUsername());
    	numFollowers.setText(String.valueOf(user.getFollowers().size()));
    	landingController.setNumFollowingLabel(UserCenter.getInstance().getCurrentUser().getFollowing().size());
    	
    	unfollowBtn.setVisible(false);
    	unfollowBtn.setDisable(true);
    	followBtn.setVisible(true);
    	followBtn.setDisable(false);
    }
    
    public void setUser(User user) {
    	this.user = UserCenter.getInstance().getUser(user.getUsername());
    }
    
    public void setLandingController(LandingController landingController) {
    	this.landingController = landingController;
    }
}
