package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import backend.Post;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PostRepliesController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
    @FXML private TilePane tilePane;
    @FXML private ImageView replyButton;
    @FXML private ImageView likeButton;
    @FXML private ImageView viewImagesBtn;
    @FXML private Label likeCounter;
    
    @FXML private ImageView postImg1;
    @FXML private ImageView postImg2;
    @FXML private ImageView postImg3;
    
    @FXML private Rectangle postImgBorder1;
    @FXML private Rectangle postImgBorder2;
    @FXML private Rectangle postImgBorder3;
    
    private Post post;
    private LandingController landingController;
    private boolean imagesVisible = true;
    
	//Initializer
	public PostRepliesController() {}
	
	public void initialize() {
		Platform.runLater(() -> {
			GUIBackend.displayPosts(post.getPostReplies(), tilePane, landingController);
			likeCounter.setText(String.valueOf(post.getLikes()));
			
			if(post.getPostImages() == null || post.getPostImages().isEmpty()) {
				return;
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
	
	@FXML public void replyButtonOnClicked(MouseEvent event) {
		ReplyController replyController = GUIBackend.loadNewWindow(GUIBackend.ReplyScene);
		replyController.passData(post, tilePane);	
		replyController.setLandingController(landingController);
	}
	
	@FXML public void likeButtonOnPressed(MouseEvent event) {
		if(post.getPoster().getUsername().equals(SignInController.currentUser.getUsername())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("You cannot like your own posts.");
			alert.showAndWait();
		} else {
			post.like();
			SignInController.globalUsers.getUser(post.getPoster().getUsername()).getUserPosts().getPost(post.getUuid()).like();
			
			likeCounter.setText(String.valueOf(post.getLikes()));	
			ColorAdjust colorAdjust = new ColorAdjust();
			colorAdjust.setHue(Color.PINK.getHue());
			likeButton.setEffect(colorAdjust);
		}	
	}
	
	@FXML public void likeButtonOnReleased(MouseEvent event) {
		likeButton.setEffect(null);
	}
	
	@FXML public void viewImagesBtnOnPressed(MouseEvent event) {
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
	
	@FXML public void viewImagesBtnOnHover(MouseEvent event) {
		
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
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		dateLabel.setText(df.format(post.getPostDate()));
	}
	
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	
}
