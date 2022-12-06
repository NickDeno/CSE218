package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.Post;
import util.GUIBackend;
import util.Utilities;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	private LandingController landingController;
	
	//Initializer
	public PostController() {}	
	
	@FXML public void postOnClicked(MouseEvent event) {
		PostWithRepliesController postReplies =  GUIBackend.loadPane(landingController.getContentPane(), GUIBackend.PostWithRepliesScene);
		postReplies.setPostData(post);
		postReplies.setLandingController(landingController);	
    }
	
	public void setPostData(Post post) {
		this.post = post;
		profilePic.setImage(Utilities.byteArrToImage(post.getPoster().getProfilePic().returnBytes()));
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
}
