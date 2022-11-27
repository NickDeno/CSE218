package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import backend.Post;
import frontend.GUIBackend;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PostController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private Label topicLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
	
	private Post post;
	private boolean isClickable;
	private LandingController landingController;
	
	//Initializer
	public PostController() {}	
	
	@FXML public void postOnClicked(MouseEvent event) {
		if(isClickable) {
			PostRepliesController postReplies =  GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.PostRepliesScene);
			postReplies.setPostData(post);
			postReplies.setLandingController(landingController);
		}
    }
	
	public void setPostData(Post post) {
		this.post = post;
		profilePic.setImage(new Image(new ByteArrayInputStream(post.getPoster().getProfilePic().returnBytes())));
		usernameLabel.setText(post.getPoster().getUsername());
		titleLabel.setText(post.getTitle());
		topicLabel.setText("Topic: " + post.getTopic());
		descriptionField.setText(post.getDescription());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		dateLabel.setText(df.format(post.getPostDate()));	
	}
	
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	 
	 public void setClickable(boolean isClickable) {
		 this.isClickable = isClickable;
	 }
}
