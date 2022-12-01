package controllers;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.UUID;

import model.Post;
import model.User;
import model.UserCenter;
import util.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class PostRepliesController {
	@FXML private ImageView profilePic;
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private Label dateLabel;
	@FXML private Label topicLabel;
	@FXML private Label likeCounter;
	@FXML private TextArea descriptionField;
    @FXML private TilePane tilePane;
    
    @FXML private Button likeBtn;
    @FXML private Button viewImagesBtn;
    @FXML private Button replyBtn;
    @FXML private Button viewProfileBtn;
    @FXML private Button backBtn;
    @FXML private Button editPostBtn;
    
    private User currentUser;
    private Post post;
    private LandingController landingController;
    
	//Initializer
	public PostRepliesController() {}
	
	public void initialize() {
		currentUser = UserCenter.getInstance().getCurrentUser();
		Platform.runLater(() -> {
			displayPosts(post.getPostReplies());
			likeCounter.setText(String.valueOf(post.getLikes()));
			if(currentUser.getUsername().equals(post.getPoster().getUsername())) {
				editPostBtn.setVisible(true);
			}
		});
	}
	
	@FXML public void likeBtnOnAction(ActionEvent event) {
		if(post.getPoster().getUsername().equals(currentUser.getUsername())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("You cannot like your own posts.");
			alert.showAndWait();
		} else {
			post.like(post.getPoster());	
			likeCounter.setText(String.valueOf(post.getLikes()));	
		}	
	}
	
	@FXML public void viewImagesBtnOnAction(ActionEvent event) {
		if(post.getPostImages() == null || post.getPostImages().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Images");
			alert.setHeaderText(null);
			alert.setContentText("There are no pictures linked to this post.");
			alert.showAndWait();
		} else {
			landingController.getPane().setEffect(new GaussianBlur(15));
			PostImagesController postImagesController = GUIBackend.loadNewUndecoratedWindow(GUIBackend.PostImagesScene);
			postImagesController.setPostImages(post.getPostImages());
			postImagesController.setLandingController(landingController);
		}	
	}
	
	@FXML public void replyBtnOnAction(ActionEvent event) {
		landingController.getPane().setEffect(new GaussianBlur(15));
		ReplyController replyController = GUIBackend.loadNewUndecoratedWindow(GUIBackend.ReplyScene);
		replyController.setPost(post);	
		replyController.setLandingController(landingController);
		replyController.setPostRepliesController(this);
	}
	
	@FXML public void viewProfileBtnOnAction(ActionEvent event) {
		if(post.getPoster().getUsername().equals(currentUser.getUsername())) {
			CurrentUserProfileController currentUserProfile = GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.CurrentUserProfileScene);
			currentUserProfile.setLandingController(landingController);
		} else {
			UserProfileController userProfile =  GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.UserProfileScene);
			userProfile.setUser(post.getPoster());
			userProfile.setLandingController(landingController);
		}
	}
	
	@FXML public void backBtnOnAction(ActionEvent event) {
		HomeFeedController homeFeed = GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.HomeFeedScene);
		homeFeed.setLandingController(landingController);
	}
	
	@FXML public void editPostBtnOnAction(ActionEvent event) {
		EditPostController editPost = GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.EditPostScene);
		editPost.setLandingController(landingController);
		editPost.setPostData(post);
	}
	
	public void setPostData(Post post) {
		this.post = post;
		profilePic.setImage(new Image(new ByteArrayInputStream(post.getPoster().getProfilePic().returnBytes())));
		usernameLabel.setText(post.getPoster().getUsername());
		titleLabel.setText(post.getTitle());
		descriptionField.setText(post.getDescription());
		topicLabel.setText("Topic: " + post.getTopic());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		dateLabel.setText(df.format(post.getPostDate()));
	}
	
	public void displayPosts(LinkedHashMap<UUID, Post> posts) {
		GUIBackend.displayPostsOldToNew(post.getPostReplies(), tilePane, landingController);
	}
	
	public void displayPost(Post post) {
		GUIBackend.displayPostOnBottom(post, tilePane, landingController);
	}
	
	public void setLandingController(LandingController landingController) {
		this.landingController = landingController;
	}
	
}
