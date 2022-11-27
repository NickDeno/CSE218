package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.UUID;

import backend.Post;
import backend.StackPaneNode;
import backend.UserCenter;
import frontend.GUIBackend;
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
    @FXML private Button followBtn;
    @FXML private Button viewProfileBtn;
    @FXML private Button backBtn;
    
    private Post post;
    private LandingController landingController;
    
	//Initializer
	public PostRepliesController() {}
	
	public void initialize() {
		Platform.runLater(() -> {
			displayPosts(post.getPostReplies());
			likeCounter.setText(String.valueOf(post.getLikes()));
		});
	}
	
	@FXML public void likeBtnOnAction(ActionEvent event) {
		if(post.getPoster().getUsername().equals(UserCenter.getInstance().getCurrentUser().getUsername())) {
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
		//Ensures there is only ever one UserProfile pane loaded into contentPane
		landingController.getContentPane().getChildren().removeIf(currentPane -> currentPane.getId().equals("UserProfilePane"));
		//No need to search contentPane for this pane since it will already be at the top
		StackPaneNode<UserProfileController> userProfile =  GUIBackend.loadStackPane(landingController.getContentPane(), 
				GUIBackend.UserProfileScene,"UserProfilePane");
		userProfile.getFxmlController().setUser(post.getPoster());
		userProfile.getFxmlController().setLandingController(landingController);
		System.out.println(landingController.getContentPane().getChildren().size());
	}
	
	@FXML public void followBtnOnAction(ActionEvent event) {
		if(post.getPoster().getUsername().equals(UserCenter.getInstance().getCurrentUser().getUsername())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("I know your lonely, but you cannot follow yourself. Make new friends.");
			alert.showAndWait();
		} else if(UserCenter.getInstance().getCurrentUser().getFollowing().get(post.getPoster().getUsername()) != null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Already Following");
			alert.setHeaderText(null);
			alert.setContentText("Sorry, but you are already following this user. Make new friends.");
			alert.showAndWait();		
		} else {
			UserCenter.getInstance().getCurrentUser().getFollowing().put(post.getPoster().getUsername(), post.getPoster());
			UserCenter.getInstance().getUser(post.getPoster().getUsername()).getFollowers().put(UserCenter.getInstance().getCurrentUser().getUsername(), UserCenter.getInstance().getCurrentUser());
		}
	}
	
	@FXML public void backBtnOnAction(ActionEvent event) {
		landingController.showPane(landingController.homeFeedNode.getPaneId());
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
		GUIBackend.displayPostsOldToNew(post.getPostReplies(), tilePane, landingController, true);
	}
	
	public void displayPost(Post post) {
		GUIBackend.displayPostOnBottom(post, tilePane, landingController, true);
	}
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	
}
