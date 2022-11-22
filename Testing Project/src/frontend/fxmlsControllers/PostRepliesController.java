package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import backend.Post;
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
import javafx.scene.shape.Rectangle;

public class PostRepliesController {
	@FXML private ImageView profilePic;
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private Label dateLabel;
	@FXML private Label topicLabel;
	@FXML private Label likeCounter;
	@FXML private TextArea descriptionField;
    @FXML private TilePane tilePane;
    
    @FXML private ImageView postImg1;
    @FXML private ImageView postImg2;
    @FXML private ImageView postImg3;  
    @FXML private Rectangle postImgBorder1;
    @FXML private Rectangle postImgBorder2;
    @FXML private Rectangle postImgBorder3;
    
    @FXML private Button likeBtn;
    @FXML private Button viewImagesBtn;
    @FXML private Button replyBtn;
    @FXML private Button followBtn;
    @FXML private Button backBtn;
    
    private Post post;
    private LandingController landingController;
    private boolean imagesVisible = true;
    
	//Initializer
	public PostRepliesController() {}
	
	public void initialize() {
		Platform.runLater(() -> {
			GUIBackend.displayPosts(post.getPostReplies(), SignInController.currentUser, tilePane, landingController);
			likeCounter.setText(String.valueOf(post.getLikes()));
			if(post.getPostImages() == null || post.getPostImages().isEmpty()) {
				postImg1.setDisable(true);
				postImg2.setDisable(true);
				postImg3.setDisable(true);
				postImgBorder1.setOpacity(0);
				postImgBorder2.setOpacity(0);
				postImgBorder3.setOpacity(0);
			} else if (post.getPostImages().size() == 1) {
				postImg1.setImage(new Image(new ByteArrayInputStream(post.getPostImages().get(0).returnBytes())));
				postImg2.setDisable(true);
				postImg3.setDisable(true);
				postImgBorder2.setOpacity(0);
				postImgBorder3.setOpacity(0);
			} else if(post.getPostImages().size() == 2) {
				postImg1.setImage(new Image(new ByteArrayInputStream(post.getPostImages().get(0).returnBytes())));
				postImg2.setImage(new Image(new ByteArrayInputStream(post.getPostImages().get(1).returnBytes())));
				postImg3.setDisable(true);
				postImgBorder3.setOpacity(0);	
			} else {
				postImg1.setImage(new Image(new ByteArrayInputStream(post.getPostImages().get(0).returnBytes())));
				postImg2.setImage(new Image(new ByteArrayInputStream(post.getPostImages().get(1).returnBytes())));
				postImg3.setImage(new Image(new ByteArrayInputStream(post.getPostImages().get(2).returnBytes())));
			}
		});
	}
	
	@FXML public void likeBtnOnAction(ActionEvent event) {
		if(post.getPoster().getUsername().equals(SignInController.currentUser.getUsername())) {
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
		} else if(imagesVisible == true) {
			imagesVisible = false;
			setImagesVisible(imagesVisible);
		} else {
			imagesVisible = true;
			setImagesVisible(imagesVisible);
		}
	}
	
	@FXML public void replyBtnOnAction(ActionEvent event) {
		landingController.getPane().setEffect(new GaussianBlur(15));
		ReplyController replyController = GUIBackend.loadNewUndecoratedWindow(GUIBackend.ReplyScene);
		replyController.passData(post, tilePane);	
		replyController.setLandingController(landingController);
	}
	
	@FXML public void followBtnOnAction(ActionEvent event) {
		if(post.getPoster().getUsername().equals(SignInController.currentUser.getUsername())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("I know your lonely, but you cannot follow yourself. Make new friends.");
			alert.showAndWait();
		} else {
			
		}
	}
	
	@FXML public void backBtnOnAction(ActionEvent event) {
		landingController.showPane(landingController.homeFeedNode.getPaneId());
	}
	
	private void setImagesVisible(boolean bool) {
		postImg1.setVisible(bool);
		postImg2.setVisible(bool);
		postImg3.setVisible(bool);
		postImgBorder1.setVisible(bool);
		postImgBorder2.setVisible(bool);
		postImgBorder3.setVisible(bool);
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
	
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	
}
